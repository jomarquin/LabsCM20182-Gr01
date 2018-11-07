package co.edu.udea.compumovil.gr01_20182.lab4;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Food;
import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Plate;

public interface IComunicaFragments {
    public void sendPlate(Plate plate);
    public void sendFood(Food food);
    public void sendDrink(Drink drink);
}
