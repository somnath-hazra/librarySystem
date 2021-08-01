package com.som.library.librarySystem.service;

import com.som.library.librarySystem.exception.NotFoundException;
import com.som.library.librarySystem.model.Customer;
import com.som.library.librarySystem.model.Item;
import com.som.library.librarySystem.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public Long onboardItem(final Item item) {
        return itemRepository.save(item).getItemId();
    }

    public Item getItemById(final Long itemId) {

        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(String.format("Item not found with ID %d", itemId)));
    }

    public void saveUpdatedItem(final Item item) {
        itemRepository.save(item);
    }

    //Used for initial bulk data creation
    public void saveAllItems(List<Item> items) {
        itemRepository.saveAll(items);
    }
}
