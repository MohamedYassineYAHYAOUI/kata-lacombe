package fr.lacombe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static fr.lacombe.CoffeeMachine.CoffeeMachineFactory;
import static org.assertj.core.api.Assertions.assertThat;

class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;

    @BeforeEach
    void setUp() {
        coffeeMachine = CoffeeMachineFactory();
    }

    @Test
    void should_return_correct_commands() {
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 1, 5f)).isEqualTo("Ch:1:0");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.TEA, 5, 5f)).isEqualTo("Th:5:0");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 0, 5f)).isEqualTo("Hh::");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 4, 5f)).isEqualTo("Hh:4:0");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 4, 5f)).isEqualTo("O::");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 5, 8f)).isEqualTo("O::");

    }


    @Test
    void should_return_message_when_money_not_enough() {
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 1, 0.1f)).isEqualTo("M:not enough money, missing 0.5");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 1, 0.5f)).isEqualTo("M:not enough money, missing 0.1");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.TEA, 1, 0.1f)).isEqualTo("M:not enough money, missing 0.3");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 1, 0.1f)).isEqualTo("M:not enough money, missing 0.4");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 1, 0.1f)).isEqualTo("M:not enough money, missing 0.5");
        assertThat(coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 1, 0.5f)).isEqualTo("M:not enough money, missing 0.1");

    }


}