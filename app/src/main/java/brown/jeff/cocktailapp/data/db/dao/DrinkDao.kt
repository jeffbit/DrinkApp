package brown.jeff.cocktailapp.data.db.dao

import androidx.room.*
import brown.jeff.cocktailapp.data.entities.DrinkEntity

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrink(drinkEntity: DrinkEntity)

    @Delete
    suspend fun deleteDrink(drinkEntity: DrinkEntity)

    @Query("SELECT * FROM drinkTable")
    fun getAllDrinks(): List<DrinkEntity>

    @Query("SELECT * FROM drinkTable WHERE drink LIKE :drinkName")
    fun getDrinkByName(drinkName: String): List<DrinkEntity>

    @Query("SELECT * FROM drinkTable WHERE idDrink LIKE :drinkId")
    fun getDrinkById(drinkId: String): DrinkEntity

    @Query("Delete FROM drinkTable WHERE idDrink Like :drinkId")
    suspend fun deleteDrinkById(drinkId: String)

    @Query("Delete FROM drinkTable")
    fun deleteAllDrinks()


}