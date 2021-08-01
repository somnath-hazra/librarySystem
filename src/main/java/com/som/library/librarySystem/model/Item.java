package com.som.library.librarySystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class Item {
    @Id
    @ToString.Include(name = "itemId", rank=1)
    private long itemId;
    @ToString.Include(name = "itemName", rank=2)
    private String itemName;
    @ToString.Include(name = "itemType", rank=3)
    private String itemType;
    @ToString.Include(name = "authorName", rank=4)
    private String authorName;
    private long totalNo;
    private long availableNo;

    //@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE , CascadeType.REMOVE}, mappedBy = "items")
    //@JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Customers_Items> customers_Items = new ArrayList<>();


}
