package brown.jeff.cocktailapp.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import brown.jeff.cocktailapp.model.Drink

@Dao
interface DrinkDao {

    @Insert
    fun insertDrink(drink: Drink)

    @Delete
    fun deleteDrink(drink: Drink)

    @Query("SELECT * FROM drinkTable")
    fun getAllDrinks(): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE drink LIKE :drinkName")
    fun getDrinkByName(drinkName: String): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE idDrink LIKE :drinkId")
    fun getDrinkById(drinkId: String): LiveData<Drink>


}