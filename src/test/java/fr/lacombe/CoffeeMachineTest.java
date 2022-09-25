package fr.lacombe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static fr.lacombe.CoffeeMachine.CoffeeMachineFactory;
import static org.assertj.core.api.Assertions.assertThat;

class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;
    private DrinksDataRepo drinksReport;

    @BeforeEach
    void setUp() {
        drinksReport = DrinksDataRepo.drinksRepoFactory();
        coffeeMachine = CoffeeMachineFactory(drinksReport);
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


    @Test
    void should_get_correct_report_1(){
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 1, Coffee: 2, Orange: 0, Tea: 0, Money: 1,7");
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 2, Coffee: 2, Orange: 0, Tea: 0, Money: 2,2");
    }

    @Test
    void should_get_correct_report_2(){
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.TEA, 2,3f );
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 1, Coffee: 1, Orange: 1, Tea: 1, Money: 2,1");
    }


}