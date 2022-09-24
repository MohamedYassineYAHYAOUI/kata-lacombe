package fr.lacombe;

public class CoffeeMachine {


    public String coffeeCommand(CoffeeType coffeeType, int sugar){
        var strBuilder = new StringBuilder();
        strBuilder.append(coffeeType.getTypeCode()).append(":");
        if(sugar > 0){
            strBuilder.append(sugar).append(":0");
        }else{
            strBuilder.append(":");
        }
        return strBuilder.toString();
    }
}
