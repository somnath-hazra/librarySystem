package com.som.library.librarySystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customers_items")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customers_Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    // additional fields
    private Date issueDate;

    public Customers_Items(Item item, Customer customer, Date issueDate) {
        this.item = item;
        this.customer = customer;
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers_Items)) return false;
        Customers_Items that = (Customers_Items) o;
        return Objects.equals(customer.getName(), that.customer.getName()) &&
                Objects.equals(item.getItemName(), that.item.getItemName()) &&
                Objects.equals(item.getItemType(), that.item.getItemType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer.getName(), item.getItemName());
    }
}
