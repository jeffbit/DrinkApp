package brown.jeff.cocktailapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import brown.jeff.cocktailapp.model.Drink

@Database(entities = [Drink::class], version = 2, exportSchema = false)
abstract class DrinkDatabase : RoomDatabase() {
    abstract fun drinkDao(): DrinkDao



}