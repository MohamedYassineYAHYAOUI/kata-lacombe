package fr.lacombe;

public enum CoffeeType {
    TEA("T", "Tea"), COFFEE("C", "Coffee"), CHOCOLATE("H", "Chocolate"), ORANGE("O", "Orange");

    private final String typeCode;
    private final String drinkName;

    CoffeeType(String typeCode, String drinkName){
        this.typeCode = typeCode;
        this.drinkName = drinkName;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public String getTypeCode() {
        return typeCode;
    }

}
