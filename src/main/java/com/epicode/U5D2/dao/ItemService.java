package com.epicode.U5D2.dao;

import com.epicode.U5D2.entities.Drink;
import com.epicode.U5D2.entities.Item;
import com.epicode.U5D2.exeption.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;

    public void save(Item i) {
        itemDAO.save(i);
        log.info("Item " + i + " save!");
    }

    public Item findById(long itemId) throws ItemNotFoundException {
        return itemDAO.findById(itemId).orElseThrow(ItemNotFoundException::new);
    }

    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    public void findByIdAndUpdate(long itemID, Item updatedUser) {
        Item found = this.findById(itemID);
        found.setCalories(updatedUser.getCalories());
        found.setPrice(updatedUser.getPrice());
        itemDAO.save(found);
        log.info("L'item with id " + itemID + " update with success!");
    }


    public void findByIdAndDelete(long userId) {
        Item found = this.findById(userId);
        itemDAO.delete(found);
        log.info("L'item with id \" + itemID + \" delete with success!");
    }

    public long count() {
        return itemDAO.count();
    }

    public List<Item> findByPriceGreaterThan(double price) {
        return itemDAO.findByPriceGreaterThan(price);
    }

    public Item findFirstByIsXl(Boolean isXl) {
        return itemDAO.findFirstByIsXl(isXl).orElseThrow(() -> new RuntimeException("Pizza not found with isXl set to null"));
    }

    public List<Drink> findDrinkByName(String name) {
        return itemDAO.findDrinkByName(name);
    }
}
