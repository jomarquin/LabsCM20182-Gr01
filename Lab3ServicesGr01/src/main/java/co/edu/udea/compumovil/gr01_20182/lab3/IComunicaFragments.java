package co.edu.udea.compumovil.gr01_20182.lab3;

import co.edu.udea.compumovil.gr01_20182.lab3.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab3.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab3.Entities.Plate;

public interface IComunicaFragments {
    public void sendPlate(Plate plate);
    public void sendFood(Food food);
    public void sendDrink(Drink drink);
}
