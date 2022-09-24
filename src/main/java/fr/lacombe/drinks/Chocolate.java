package fr.lacombe.drinks;

import fr.lacombe.CoffeeType;
import fr.lacombe.Drink;

public class Chocolate extends AbstractDrink implements Drink {

    public Chocolate(double price) {
        super(price, CoffeeType.CHOCOLATE);
    }

    @Override
    public String drinkCommand(int sugar) {
        return super.getDrinkType()+"h:"+super.sugarAndStickCommand(sugar);
    }

    @Override
    public float subtractFromDrinkValue(float money) {
        return super.subFromDrinkValue(money);
    }
}
