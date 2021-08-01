package com.som.library.librarySystem.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString(of = {"itemId", "itemName", "itemType", "authorName", "overdueCharges"})
public class ItemDto {
    private long itemId;
    private String itemName;
    private String itemType;
    private String authorName;
    private long totalNo;
    private long availableNo;
    private long overdueCharges;

}
