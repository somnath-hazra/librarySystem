package com.som.library.librarySystem.controller;

import com.som.library.librarySystem.dto.CustomerDto;
import com.som.library.librarySystem.dto.ItemDto;
import com.som.library.librarySystem.dto.User;
import com.som.library.librarySystem.exception.NotFoundException;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Item;
import com.som.library.librarySystem.security.component.TokenGeneration;
import com.som.library.librarySystem.service.CustomerService;
import com.som.library.librarySystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;
    @Autowired
    TokenGeneration tokenGeneration;

    @PostMapping(value = "/registerCustomer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> registerCustomer(@RequestBody CustomerDto newCustomer) {
        return new ResponseEntity<>(
                customerService.onboardNewCustomerCustomer(newCustomer.getCustomer()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/allCustomers", produces = "application/json")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(),
                HttpStatus.OK);
    }

    //TODO:Handle Not Found
    @GetMapping(value = "/customerDetails/{id}", produces = "application/json")
    public ResponseEntity<CustomerDto> getCustomerDetails(@PathVariable Long id) {
        return new ResponseEntity<>(
                customerService.getCustomerByIdWithItems(id),
                HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}/items", produces = "application/json")
    public ResponseEntity<List<ItemDto>> getAllLendingItemsByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(
                customerService.getAllLendingItemByCustomerId(id),
                HttpStatus.OK);
    }

    @GetMapping(value = "/customer/{id}/overdueItems", produces = "application/json")
    public ResponseEntity<Pair<List<ItemDto>, String>> getAllOverdueLendingItemsByCustomerId(@PathVariable Long id) {
        return new ResponseEntity<>(
                customerService.getAllOverdueLendingItemByCustomerId(id),
                HttpStatus.OK);
    }

    @PostMapping(value = "/customer/{customerId}/issueItems", consumes = "application/json", produces = "application/json")
    public void issueItemToCustomer(@PathVariable Long customerId, @RequestBody List<Long> itemIds) {
        for(Long id : itemIds) {
            Item item = itemService.getItemById(id);
            if(item.getAvailableNo() <= 0l)
                throw new NotFoundException(String.format("Item not found with ID %d", id));
            customerService.issueItemToCustomerById(item, customerId);
            item.setAvailableNo(item.getAvailableNo() - 1);
            itemService.saveUpdatedItem(item);
        }
    }

    @PostMapping(value = "/customer/{customerId}/returnItems", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> returnItemForCustomer(@PathVariable Long customerId, @RequestBody List<Long> itemIds) {
        for(Long id : itemIds) {
            Item item = itemService.getItemById(id);
            customerService.returnItemByCustomerById(item, customerId);
            item.setAvailableNo(item.getAvailableNo() + 1);
            itemService.saveUpdatedItem(item);
        }
        return new ResponseEntity<>(
                "Successfully Returned",
                HttpStatus.OK);
    }

    @PostMapping(value = "/onboardItem", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> onboardItem(@RequestBody Item item) {
        return new ResponseEntity<>(
                itemService.onboardItem(item),
                HttpStatus.OK);
    }

    @GetMapping(value = "/item/{itemId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Item> getItem(@PathVariable Long itemId) {
        return new ResponseEntity<>(
                itemService.getItemById(itemId),
                HttpStatus.OK);
    }

    //Token Generation
    @PostMapping("/getToken")
    public User getUserToken(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String token = tokenGeneration.getToken(username);
        User user = new User();
        user.setUserName(username);
        user.setToken(token);
        return user;
    }
}
