package fr.lacombe;

import fr.lacombe.drinks.Chocolate;
import fr.lacombe.drinks.Coffee;
import fr.lacombe.drinks.Orange;
import fr.lacombe.drinks.Tea;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeMachine {

    private final Map<CoffeeType, Drink> drinks; // map for the coffee by type

    private CoffeeMachine(Map<CoffeeType, Drink> drinks){
        this.drinks = drinks;
    }

    /**
     * factory method for the coffee Machine class
     * @return new instance of CoffeeMachine
     */
    public static CoffeeMachine CoffeeMachineFactory(){
        var coffeePrices = new HashMap<CoffeeType, Drink>();
        coffeePrices.put(CoffeeType.COFFEE, new Coffee(0.6));
        coffeePrices.put(CoffeeType.TEA, new Tea(0.4));
        coffeePrices.put(CoffeeType.CHOCOLATE,new Chocolate(0.5));
        coffeePrices.put(CoffeeType.ORANGE,new Orange(0.6));
        return new CoffeeMachine(coffeePrices);
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
        return drink.drinkCommand(sugar);
    }
}
