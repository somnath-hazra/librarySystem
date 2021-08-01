package com.som.library.librarySystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String email;
    private String phone;
    private String accountStatus;

    /*@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @JoinTable(name = "customers_items", joinColumns = { @JoinColumn(name = "customer_id") }, inverseJoinColumns = {
            @JoinColumn(name = "item_id") }) */
    //@JsonManagedReference
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customers_Items> customers_Items = new ArrayList<>();
    //private Map<Long, LocalDate> itemIssueDateMap = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer that = (Customer) o;
        return Objects.equals(this.name, that.getName()) &&
                Objects.equals(this.email, that.getEmail()) &&
                Objects.equals(this.phone, that.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.email, this.phone);
    }
}
