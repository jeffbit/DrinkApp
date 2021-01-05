package brown.jeff.cocktailapp.features.favoritedrink.model

import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.model.DrinkORM

data class DrinkFavoriteListModel(
    val id: String,
    val imgURL: String,
    val title: String
)


fun DrinkORM.toDrinkFavoriteListModel() =
    DrinkFavoriteListModel(
        id = idDrink,
        imgURL = drinkImg,
        title = drink
    )

fun List<DrinkORM>.toDrinkFavoriteListModel() =
    this.map { it.toDrinkFavoriteListModel() }




