package fr.lacombe;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DrinksDataRepo {

    private final Map<CoffeeType, Integer> drinkRepo ;
    private double moneyCollected = 0;

    private DrinksDataRepo(Map<CoffeeType, Integer> drinkRepo ){
        this.drinkRepo = drinkRepo;
    }

    public static DrinksDataRepo drinksRepoFactory(){
        var drinkRepo = new HashMap<CoffeeType, Integer>();
        for (CoffeeType value : CoffeeType.values()) {
            drinkRepo.put(value, 0);
        }
        return new DrinksDataRepo(drinkRepo);
    }

    String getReport(){
        var report = new StringBuilder();
        drinkRepo.keySet().stream()
                .sorted(Comparator.comparing(CoffeeType::getDrinkName))
                .forEach(k->report.append(k.getDrinkName()).append(": ").append(drinkRepo.get(k)).append(", "));
        report.append("Money: ").append(String.format("%.1f", moneyCollected));
        return report.toString();
    }


    void orderedDrink(CoffeeType drinkType, double price){
        moneyCollected+= price;
        drinkRepo.compute(drinkType, (k, v) -> (v == null) ? 1 : v+1);
    }

}
