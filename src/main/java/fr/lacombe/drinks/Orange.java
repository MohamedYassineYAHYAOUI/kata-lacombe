package fr.lacombe.drinks;

import fr.lacombe.CoffeeType;
import fr.lacombe.Drink;

public class Orange extends AbstractDrink  implements Drink {

    public Orange(double price) {
        super(price, CoffeeType.ORANGE);
    }

    @Override
    public String drinkCommand(int sugar) {
        return super.getDrinkType()+"::";
    }

    @Override
    public float subtractFromDrinkValue(float money) {
        return super.subFromDrinkValue(money);
    }

    @Override
    public double price() {
        return super.getPrice();
    }
}
