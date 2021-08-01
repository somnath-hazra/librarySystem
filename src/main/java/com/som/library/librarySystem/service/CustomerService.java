package com.som.library.librarySystem.service;

import com.som.library.librarySystem.Constants;
import com.som.library.librarySystem.dto.CustomerDto;
import com.som.library.librarySystem.dto.ItemDto;
import com.som.library.librarySystem.enums.Types;
import com.som.library.librarySystem.exception.NotFoundException;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Customers_Items;
import com.som.library.librarySystem.model.Item;
import com.som.library.librarySystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void issueItemToCustomerById(final Item item, final Long custId) {
        Customer customer = this.getCustomerById(custId);
        if(!customer.getAccountStatus().equals(Types.AccountStatus.ACTIVE.getAccountStatus()))
            throw new NotFoundException(String.format("Customer with ID %d is not allowed to lend an item", custId));
        Customers_Items customers_items = new Customers_Items(item, customer, new Date());
        customer.getCustomers_Items().add(customers_items);
        customerRepository.save(customer);
    }

    public void returnItemByCustomerById(final Item item, final Long custId) {
        Customer customer = this.getCustomerById(custId);
        List<Customers_Items> customers_items = customer.getCustomers_Items().stream().filter(ci -> ci.getItem().getItemId() != item.getItemId())
                .collect(Collectors.toList());
        customer.setCustomers_Items(customers_items);
        customerRepository.save(customer);
    }

    public Customer getCustomerById(final Long custId) {
         return this.customerRepository.findById(custId)
                 .orElseThrow(() -> new NotFoundException(String.format("Customer not found with ID %d", custId)));
    }

    public CustomerDto getCustomerByIdWithItems(final Long custId) {
        Customer customer =  this.getCustomerById(custId);
        CustomerDto customerDto = new CustomerDto(customer);
        return customerDto;
    }

    public List<ItemDto> getAllLendingItemByCustomerId(final Long custId) {
        Customer customer = this.getCustomerById(custId);
        CustomerDto customerDto = new CustomerDto(customer);
        return customerDto.getItemDtos();
    }

    public Pair<List<ItemDto>, String> getAllOverdueLendingItemByCustomerId(final Long custId) {
        Customer customer = this.getCustomerById(custId);
        AtomicInteger totalCharges = new AtomicInteger();
        AtomicInteger charges = new AtomicInteger();
        List<ItemDto> items =  customer.getCustomers_Items().stream()
                .filter(ci -> (ChronoUnit.DAYS.between(ci.getIssueDate().toInstant(), new Date().toInstant()) > Constants.MAX_LENDING_DAYS))
                .map(cis -> {
                        charges.set(((int)ChronoUnit.DAYS.between(cis.getIssueDate().toInstant(), new Date().toInstant()) - Constants.MAX_LENDING_DAYS) * Constants.CHARGE_PER_DAY);
                        totalCharges.set(totalCharges.get() + charges.get());
                        return ItemDto.builder().itemId(cis.getItem().getItemId())
                                .itemName(cis.getItem().getItemName())
                                .itemType(cis.getItem().getItemType())
                                .overdueCharges(charges.get())
                                .build();
                                }).collect(Collectors.toList());
        return Pair.of(items, "TotalCharge: " + totalCharges.get());
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(c -> new CustomerDto(c)).collect(Collectors.toList());
    }

    public Long onboardNewCustomerCustomer(final Customer customer) {
        return customerRepository.save(customer).getId();
    }

    //Used for initial bulk data creation
    public void saveAllCustomers(List<Customer> customers) {
        customerRepository.saveAll(customers);
    }
}
