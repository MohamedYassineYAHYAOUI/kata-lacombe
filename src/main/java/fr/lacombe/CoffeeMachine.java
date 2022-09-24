package fr.lacombe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CoffeeMachine {

    private final Map<CoffeeType, BigDecimal> coffeePrices; // map for the coffee prices by type

    private CoffeeMachine(Map<CoffeeType, BigDecimal> coffeePrices){
        this.coffeePrices = coffeePrices;
    }

    /**
     * factory method for the coffee Machine class
     * @return new instance of CoffeeMachine
     */
    public static CoffeeMachine CoffeeMachineFactory(){
        var coffeePrices = new HashMap<CoffeeType, BigDecimal>();
        coffeePrices.put(CoffeeType.COFFEE, new BigDecimal(0.6));
        coffeePrices.put(CoffeeType.TEA, new BigDecimal(0.4));
        coffeePrices.put(CoffeeType.CHOCOLATE, new BigDecimal(0.5));
        return new CoffeeMachine(coffeePrices);
    }

    private String coffeeCommand(CoffeeType coffeeType, int sugar){
        var strBuilder = new StringBuilder();
        strBuilder.append(coffeeType.getTypeCode()).append(":");
        if(sugar > 0){
            strBuilder.append(sugar).append(":0");
        }else{
            strBuilder.append(":");
        }
        return strBuilder.toString();
    }

    public String makeCoffeeWithMoney(CoffeeType coffeeType, int sugar, double money){
        var drinkValue = coffeePrices.get(coffeeType);
        if(drinkValue == null){
            return "M: Drink type missing";
        }
        var diff = drinkValue.subtract(new BigDecimal(money));
        System.out.println(diff.floatValue());
        if( diff.floatValue() > 0){
            return "M:not enough money, missing "+ diff.floatValue();
        }
        return coffeeCommand(coffeeType, sugar);
    }
}
