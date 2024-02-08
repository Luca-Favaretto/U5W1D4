package com.epicode.U5D2.dao;

import com.epicode.U5D2.entities.Drink;
import com.epicode.U5D2.entities.Item;
import com.epicode.U5D2.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemDAO extends JpaRepository<Item, Long> {

    List<Item> findByPriceGreaterThan(double price);

    Optional<Pizza> findFirstByIsXl(boolean isXl);


    @Query("SELECT d FROM Drink d WHERE d.name=:name")
    List<Drink> findDrinkByName(String name);
}

