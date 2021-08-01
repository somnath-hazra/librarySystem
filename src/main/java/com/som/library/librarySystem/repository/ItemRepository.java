package com.som.library.librarySystem.repository;

import com.som.library.librarySystem.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository< Item, Long> {
}
