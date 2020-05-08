package brown.jeff.cocktailapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import brown.jeff.cocktailapp.model.Drink

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drink: Drink)

    @Delete
    suspend fun deleteDrink(drink: Drink)

    @Query("SELECT * FROM drinkTable")
    fun getAllDrinks(): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE drink LIKE :drinkName")
    fun getDrinkByName(drinkName: String): LiveData<List<Drink>>

    @Query("SELECT * FROM drinkTable WHERE idDrink LIKE :drinkId")
    fun getDrinkById(drinkId: String): LiveData<Drink>

    @Query("Delete FROM drinkTable WHERE idDrink Like :drinkId")
    suspend fun deleteDrinkById(drinkId: String)

    @Query("Delete FROM drinkTable")
    fun deleteAllDrinks()


}