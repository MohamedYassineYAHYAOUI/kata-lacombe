package fr.lacombe;

import fr.lacombe.drinkmaker.BeverageQuantityChecker;
import fr.lacombe.drinkmaker.EmailNotifier;
import fr.lacombe.drinks.Chocolate;
import fr.lacombe.drinks.Coffee;
import fr.lacombe.drinks.Orange;
import fr.lacombe.drinks.Tea;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private final Map<CoffeeType, Drink> drinks; // map for the coffee by type
    private final DrinksDataRepo drinksRepo;
    private final BeverageQuantityChecker beverageQuantityChecker;
    private final EmailNotifier emailNotifier;


    private CoffeeMachine(Map<CoffeeType, Drink> drinks, DrinksDataRepo drinksRepo,  EmailNotifier emailNotifier, BeverageQuantityChecker beverageQuantityChecker ){
        this.drinks = drinks;
        this.drinksRepo = drinksRepo;
        this.emailNotifier= emailNotifier;
        this.beverageQuantityChecker = beverageQuantityChecker;
    }

    /**
     * factory method for the coffee Machine class
     * @return new instance of CoffeeMachine
     */
    public static CoffeeMachine CoffeeMachineFactory(DrinksDataRepo drinksRepo, EmailNotifier emailNotifier, BeverageQuantityChecker beverageQuantityChecker ){
        var coffeePrices = new HashMap<CoffeeType, Drink>();
        coffeePrices.put(CoffeeType.COFFEE, new Coffee(0.6));
        coffeePrices.put(CoffeeType.TEA, new Tea(0.4));
        coffeePrices.put(CoffeeType.CHOCOLATE,new Chocolate(0.5));
        coffeePrices.put(CoffeeType.ORANGE,new Orange(0.6));
        return new CoffeeMachine(coffeePrices, drinksRepo, emailNotifier, beverageQuantityChecker);
    }


    public String makeCoffeeWithMoney(CoffeeType coffeeType, int sugar, float money){
        var drink = drinks.get(coffeeType);
        if(drink == null){
            return "M: Drink type missing";
        }
        var diff = drink.subtractFromDrinkValue(money);
        if( diff  > 0){
            return "M:not enough money, missing "+ diff;
        }
        if(beverageQuantityChecker.isEmpty(coffeeType.getDrinkName())){
            emailNotifier.notifyMissingDrink(coffeeType.getDrinkName());
            return "M: ingredients missing, notification has been sent";
        }
        drinksRepo.orderedDrink(coffeeType, drink.price());
        return drink.drinkCommand(sugar);
    }
}
