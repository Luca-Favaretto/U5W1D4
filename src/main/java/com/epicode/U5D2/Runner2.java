package com.epicode.U5D2;

import com.epicode.U5D2.dao.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test2")
@Component
@org.springframework.core.annotation.Order(2)
@Slf4j
public class Runner2 implements CommandLineRunner {
    @Autowired
    ItemService itemService;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("------------------------------------------------------------------------------------------------");
        itemService.findAll().forEach(System.out::println);
        System.out.println(itemService.findById(1));
        itemService.findByIdAndDelete(1);
        System.out.println(itemService.count());
        itemService.findByPriceGreaterThan(5).forEach(System.out::println);
        System.out.println(itemService.findFirstByIsXl(true));
        System.out.println(itemService.findDrinkByName("Lemonade"));
    }
}
