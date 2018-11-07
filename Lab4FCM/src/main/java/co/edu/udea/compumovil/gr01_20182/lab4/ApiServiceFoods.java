package co.edu.udea.compumovil.gr01_20182.lab4;

import co.edu.udea.compumovil.gr01_20182.lab4.Entities.Foods;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceFoods {

    @GET("/foods")
    Call<Foods> getFoods(
            @Query("name") String name,
            @Query("description") String description,
            @Query("image") String image,
            @Query("type") String type,
            @Query("time") String time,
            @Query("price") String price);
}
