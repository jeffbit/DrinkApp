package brown.jeff.cocktailapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import brown.jeff.cocktailapp.model.Drink

@Database(entities = [Drink::class], version = 3, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao



}