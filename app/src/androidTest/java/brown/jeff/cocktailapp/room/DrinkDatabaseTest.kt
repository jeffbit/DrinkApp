package brown.jeff.cocktailapp.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import brown.jeff.cocktailapp.model.Drink
import brown.jeff.cocktailapp.util.DRINK
import kotlinx.coroutines.runBlocking
import org.junit.*
import java.io.IOException

class DrinkDatabaseTest {
    private lateinit var drinkDao: DrinkDao
    private lateinit var database: DrinkDatabase


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun createDatabase() {
        val context = InstrumentationRegistry.getInstrumentation().context

        database = Room.inMemoryDatabaseBuilder(context, DrinkDatabase::class.java)
            .build()

        drinkDao = database.drinkDao()

    }

    @Test
    fun insertDrink() = runBlocking {
        //given
        val writeDrink = DRINK
        val writtenDrinkId = writeDrink.idDrink

        //when
        drinkDao.insertDrink(writeDrink)
        val readDrink = drinkDao.getDrinkById(writtenDrinkId).getValueBlocking()

        //then
        Assert.assertEquals(readDrink?.drink, writeDrink.drink)
    }

    @Test
    fun getAllDrinks() = runBlocking {
        //given
        val drink = DRINK
        val drinkList = drink
        //when
        drinkDao.insertDrink(drink)
        //then
        val getDrinks = drinkDao.getAllDrinks().getValueBlocking()
        Assert.assertEquals(drinkList, getDrinks)
    }

    @Test
    fun deleteDrink() = runBlocking {
        //given
        val writeDrink = DRINK
        drinkDao.insertDrink(writeDrink)
        //when
        drinkDao.deleteDrink(writeDrink)
        val result = drinkDao.getDrinkById(writeDrink.idDrink).getValueBlocking()
        //then
        Assert.assertNull(result)


    }


    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }


}
