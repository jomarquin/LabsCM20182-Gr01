package co.edu.udea.compumovil.gr01_20182.lab2;

import co.edu.udea.compumovil.gr01_20182.lab2.Entities.Drink;
import co.edu.udea.compumovil.gr01_20182.lab2.Entities.Plate;

public interface IComunicaFragments {
    public void sendPlate(Plate plate);

    public void sendDrink(Drink drink);
}
