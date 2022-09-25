package fr.lacombe.drinks;

import fr.lacombe.CoffeeType;
import fr.lacombe.Drink;

public class Tea extends AbstractDrink  implements Drink {

    public Tea(double price) {
        super(price, CoffeeType.TEA);
    }

    @Override
    public String drinkCommand(int sugar) {
        return super.getDrinkType()+"h:"+super.sugarAndStickCommand(sugar);
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
