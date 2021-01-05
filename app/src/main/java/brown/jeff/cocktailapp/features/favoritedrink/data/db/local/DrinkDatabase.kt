package brown.jeff.cocktailapp.features.favoritedrink.data.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.dao.DrinkDao
import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.model.DrinkORM

@Database(entities = [DrinkORM::class], version = 3, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao



}