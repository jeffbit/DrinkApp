package brown.jeff.cocktailapp.features.drinklist.model

import brown.jeff.cocktailapp.data.model.DrinkEntity

data class DrinkListModel(
     val id: String,
     val imgURL: String,
     val title: String


)


fun DrinkEntity.toDrinkListModel() =
    DrinkListModel(
        id = idDrink,
        imgURL = drinkImg,
        title = drink
    )


fun List<DrinkEntity>.toDrinkListModel() =
    this.map { it.toDrinkListModel() }