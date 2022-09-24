package fr.lacombe.drinks;

import fr.lacombe.CoffeeType;
import fr.lacombe.Drink;

public class Coffee extends AbstractDrink implements Drink{

    public Coffee(double price){
        super(price, CoffeeType.COFFEE);
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
