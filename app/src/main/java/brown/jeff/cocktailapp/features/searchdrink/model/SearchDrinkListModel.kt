package brown.jeff.cocktailapp.features.searchdrink.model

import brown.jeff.cocktailapp.data.model.DrinkEntity
import brown.jeff.cocktailapp.features.drinklist.model.DrinkListModel

data class SearchDrinkListModel(
    val id: String,
    val imgURL: String,
    val title: String


)


fun DrinkEntity.toSearchDrinkListModel() =
    SearchDrinkListModel(
        id = idDrink,
        imgURL = drinkImg,
        title = drink
    )


fun List<DrinkEntity>.toSearchDrinkListModel() =
    this.map { it.toSearchDrinkListModel() }


