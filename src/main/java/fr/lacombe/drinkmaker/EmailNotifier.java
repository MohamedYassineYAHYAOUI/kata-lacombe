package fr.lacombe.drinkmaker;

public interface EmailNotifier {
    void notifyMissingDrink(String drink);
}