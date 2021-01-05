package brown.jeff.cocktailapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DrinkORM::class], version = 3, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao



}