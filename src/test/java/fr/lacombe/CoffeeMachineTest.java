package fr.lacombe;

import fr.lacombe.drinkmaker.BeverageQuantityChecker;
import fr.lacombe.drinkmaker.EmailNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static fr.lacombe.CoffeeMachine.CoffeeMachineFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoffeeMachineTest {

    private CoffeeMachine coffeeMachine;
    private DrinksDataRepo drinksReport;

    @Mock
    private EmailNotifier emailNotifierMock;

    @Mock
    private BeverageQuantityChecker beverageQuantityCheckerMock;


    @BeforeEach
    void setUp() {
        drinksReport = DrinksDataRepo.drinksRepoFactory();
        coffeeMachine = CoffeeMachineFactory(drinksReport, emailNotifierMock, beverageQuantityCheckerMock);
    }

    @Test
    void should_return_correct_commands() {
        when(beverageQuantityCheckerMock.isEmpty(anyString())).thenReturn(false);
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
        when(beverageQuantityCheckerMock.isEmpty(anyString())).thenReturn(false);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 1, Coffee: 2, Orange: 0, Tea: 0, Money: 1,7");
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 2, Coffee: 2, Orange: 0, Tea: 0, Money: 2,2");
    }

    @Test
    void should_get_correct_report_2(){
        when(beverageQuantityCheckerMock.isEmpty(anyString())).thenReturn(false);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.CHOCOLATE, 2,3f);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.COFFEE, 2,3f );
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.TEA, 2,3f );
        assertThat(drinksReport.getReport()).isEqualToIgnoringNewLines("Chocolate: 1, Coffee: 1, Orange: 1, Tea: 1, Money: 2,1");
    }

    @Test
    void should_notify_missing_ingredients(){
        when(beverageQuantityCheckerMock.isEmpty(anyString())).thenReturn(true);
        coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 2,3f );
        verify(emailNotifierMock, times(1)).notifyMissingDrink(CoffeeType.ORANGE.getDrinkName());
    }

    @Test
    void should_alert_after_sending_notification(){
        when(beverageQuantityCheckerMock.isEmpty(anyString())).thenReturn(true);
        var message = coffeeMachine.makeCoffeeWithMoney(CoffeeType.ORANGE, 2,3f );
        assertThat(message).isEqualTo("M: ingredients missing, notification has been sent");
    }
}