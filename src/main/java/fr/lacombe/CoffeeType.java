package fr.lacombe;

public enum CoffeeType {
    TEA("T"), COFFEE("C"), CHOCOLATE("H"), ORANGE("O");

    private final String typeCode;

    CoffeeType(String typeCode){
        this.typeCode = typeCode;
    }

    public String getTypeCode() {
        return typeCode;
    }
}
