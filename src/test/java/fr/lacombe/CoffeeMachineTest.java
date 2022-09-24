package fr.lacombe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;

    @BeforeEach
    void setUp() {
        coffeeMachine = new CoffeeMachine();
    }

    @Test
    void should_return_correct_commands() {
        assertThat(coffeeMachine.coffeeCommand(CoffeeType.COFFEE, 1)).isEqualTo("C:1:0");
        assertThat(coffeeMachine.coffeeCommand(CoffeeType.TEA, 5)).isEqualTo("T:5:0");
        assertThat(coffeeMachine.coffeeCommand(CoffeeType.CHOCOLATE, 0)).isEqualTo("H::");
        assertThat(coffeeMachine.coffeeCommand(CoffeeType.CHOCOLATE, 4)).isEqualTo("H:4:0");
    }





}