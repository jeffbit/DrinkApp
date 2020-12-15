package brown.jeff.cocktailapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import brown.jeff.cocktailapp.data.db.dao.DrinkDao
import brown.jeff.cocktailapp.data.entities.DrinkEntity

@Database(entities = [DrinkEntity::class], version = 2, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao



}