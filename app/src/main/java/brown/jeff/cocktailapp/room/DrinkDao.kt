package brown.jeff.cocktailapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import brown.jeff.cocktailapp.model.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDrink(drink: Drink)

    @Delete
    fun deleteDrink(drink: Drink)

    @Query("SELECT * FROM drinkTable")
    fun getAllDrinks(): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE drink LIKE :drinkName")
    fun getDrinkByName(drinkName: String): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE idDrink LIKE :drinkId")
    fun getDrinkById(drinkId: String): LiveData<Drink>

    @Query("Delete FROM drinkTable WHERE idDrink Like :drinkId")
    fun deleteDrinkById(drinkId: String)


}