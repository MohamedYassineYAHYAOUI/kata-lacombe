package fr.lacombe.drinks;

import fr.lacombe.CoffeeType;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * protected abstract class
 */
class AbstractDrink {

    private final BigDecimal price;
    private final CoffeeType drinkType;

    AbstractDrink(double price, CoffeeType drinkType){
        this.price = new BigDecimal(price);
        this.drinkType = drinkType;
    }


    String sugarAndStickCommand(int sugar){
        return sugar > 0 ? sugar+":0" : ":";
    }

    String getDrinkType() {
        return drinkType.getTypeCode();
    }

     double getPrice() {
        return price.doubleValue();
    }

    float subFromDrinkValue(double money){
        return price.subtract(new BigDecimal(money)).floatValue();
    }
}
