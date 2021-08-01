package com.som.library.librarySystem.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Item;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
public class CustomerDto  {
    private Customer customer;
    private long id;
    private String name;
    private String email;
    private String phone;
    private String accountStatus;
    @JsonProperty("items")
    private List<ItemDto> itemDtos;

    public CustomerDto(final Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.phone =  customer.getPhone();
        this.accountStatus = customer.getAccountStatus();
        this.itemDtos = customer.getCustomers_Items().stream()
                .map(ci -> ci.getItem())
                .map(item -> ItemDto.builder()
                        .itemId(item.getItemId())
                        .authorName(item.getAuthorName())
                        .itemName(item.getItemName())
                        .itemType(item.getItemType()).build()).collect(Collectors.toList());
    }
}
