package com.som.library.librarySystem;

import com.som.library.librarySystem.enums.Types;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Item;
import com.som.library.librarySystem.service.CustomerService;
import com.som.library.librarySystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InitialDataCreation {
    @Autowired
    CustomerService customerService;
    @Autowired
    ItemService itemService;

    private List<Item> items = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void createItems() {
           Item headFirstJava =  Item.builder()
                .itemId(1)
                .itemType(Types.ItemFormat.BOOK.getItemFormat())
                .itemName("Head First Java")
                .availableNo(10l)
                .totalNo(10l)
                .authorName("Robert").build();
        Item letUsC =  Item.builder()
                .itemId(2)
                .itemType(Types.ItemFormat.BOOK.getItemFormat())
                .itemName("Let us C")
                .availableNo(20l)
                .totalNo(20l)
                .authorName("Yeswant Kanetkar").build();
        Item dvdAlgo =  Item.builder()
                .itemId(3)
                .itemType(Types.ItemFormat.DVD.getItemFormat())
                .itemName("Try Algo design")
                .availableNo(30l)
                .totalNo(30l)
                .authorName("Gopal Manchandani").build();
        Item dvdEvent =  Item.builder()
                .itemId(4)
                .itemType(Types.ItemFormat.DVD.getItemFormat())
                .itemName("Event Redefined")
                .availableNo(100l)
                .totalNo(100l)
                .authorName("Richard Williamson").build();
        items.add(headFirstJava);
        items.add(letUsC);
        items.add(dvdAlgo);
        items.add(dvdEvent);
        itemService.saveAllItems(items);
    }

    public void createDefaultCustomers() {
        Customer suresh = Customer.builder().id(100).email("suresh@gmail.com")
                .name("Suresh").phone("9999999988").accountStatus(Types.AccountStatus.ACTIVE.getAccountStatus())
                .build();
        Customer mahesh = Customer.builder().id(101).email("mahesh@gmail.com")
                .name("Mahesh").phone("9999999977").accountStatus(Types.AccountStatus.CLOSED.getAccountStatus())
                .build();
        Customer naresh = Customer.builder().id(102).email("naresh@gmail.com")
                .name("Naresh").phone("9999999977").accountStatus(Types.AccountStatus.BLACKLISTED.getAccountStatus())
                .build();
        Customer ramesh = Customer.builder().id(103).email("ramesh@gmail.com")
                .name("Ramesh").phone("9999999966").accountStatus(Types.AccountStatus.ACTIVE.getAccountStatus())
                .build();
        customers.add(suresh);
        customers.add(mahesh);
        customers.add(naresh);
        customers.add(ramesh);
        customerService.saveAllCustomers(customers);
    }
}
