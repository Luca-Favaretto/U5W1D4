package com.epicode.U5D2;

import com.epicode.U5D2.entities.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
class Esercizio30102023ApplicationTests {
	private static AnnotationConfigApplicationContext ctx;

	@BeforeAll
	public static void startUp() {
		ctx = new AnnotationConfigApplicationContext(U5D2Application.class);

	}

	@Test
	public void testWaterPrice() {
		assertEquals(1.29, ctx.getBean("water", Drink.class).getPrice());
	}

	@Test
	public void testPizzasList() {
		assertEquals(4, ((List<Pizza>) ctx.getBean("pizzas")).size());
	}

	@Test
	public void testApplicationProperties() {
		assertEquals(2.00, ctx.getBean("Tavolo1", Table.class).getCostoCoperto());
	}

	@Test
	public void testOrderTotal() {
		List<Pizza> allPizzas = (List<Pizza>) ctx.getBean("pizzas");
		Table t1 = (Table) ctx.getBean("Tavolo1");
		Order o1 = new Order(4, t1);
		allPizzas.forEach(o1::addItem);

		assertEquals(31.72, o1.getTotal());
	}

	@ParameterizedTest
	@CsvSource({"salami_pizza_xl, 878", "pizza_margherita, 792"})
	public void testPizzeParam(String nomePizza, int expectedCalories) {
		Pizza pizza = ctx.getBean(nomePizza, Pizza.class);
		assertEquals(expectedCalories, pizza.getCalories());
	}

	@ParameterizedTest
	@CsvSource({"toppings_tomato, 0", "toppings_ham, 35", "toppings_pineapple, 24"})
	void testToppingsCalories(String nomeTopping, int caloriePreviste) {
		Topping topping = ctx.getBean(nomeTopping, Topping.class);
		assertEquals(caloriePreviste, topping.getCalories());
	}

}
