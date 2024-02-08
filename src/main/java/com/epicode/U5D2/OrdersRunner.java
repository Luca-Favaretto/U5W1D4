package com.epicode.U5D2;

import com.epicode.U5D2.dao.ItemService;
import com.epicode.U5D2.entities.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
@org.springframework.core.annotation.Order(1)
@Slf4j
public class OrdersRunner implements CommandLineRunner {
    @Autowired
    ItemService itemService;

    @Override

    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(U5D2Application.class);

        try {
            // Save Toppings
            itemService.save((Item) ctx.getBean("toppings_tomato"));
            itemService.save((Item) ctx.getBean("toppings_cheese"));
            itemService.save((Item) ctx.getBean("toppings_ham"));
            itemService.save((Item) ctx.getBean("toppings_pineapple"));
            itemService.save((Item) ctx.getBean("toppings_salami"));

            // Save Pizzas
            itemService.save((Item) ctx.getBean("pizza_margherita"));
            itemService.save((Item) ctx.getBean("hawaiian_pizza"));
            itemService.save((Item) ctx.getBean("salami_pizza"));
            itemService.save((Item) ctx.getBean("salami_pizza_xl"));

            // Save Drinks
            itemService.save((Item) ctx.getBean("lemonade"));
            itemService.save((Item) ctx.getBean("water"));
            itemService.save((Item) ctx.getBean("wine"));

        } catch (Exception e) {
            log.info("error on save on DB" + e);
        }


        try {

            Menu m = (Menu) ctx.getBean("menu");
            m.printMenu();

            Table t1 = (Table) ctx.getBean("Tavolo1");

            Order o1 = new Order(4, t1);

            o1.addItem(ctx.getBean("pizza_margherita", Pizza.class));
            o1.addItem(ctx.getBean("pizza_margherita", Pizza.class));
            o1.addItem(ctx.getBean("hawaiian_pizza", Pizza.class));
            o1.addItem(ctx.getBean("salami_pizza_xl", Pizza.class));
            o1.addItem(ctx.getBean("lemonade", Drink.class));
            o1.addItem(ctx.getBean("lemonade", Drink.class));
            o1.addItem(ctx.getBean("wine", Drink.class));

            System.out.println("DETTAGLI TAVOLO 1:");
            o1.print();

            System.out.println("CONTO TAVOLO 1");
            System.out.println(o1.getTotal());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            ctx.close();
        }
    }
}
