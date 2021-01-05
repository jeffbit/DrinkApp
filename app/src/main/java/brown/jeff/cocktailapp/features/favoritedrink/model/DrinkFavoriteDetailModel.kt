package brown.jeff.cocktailapp.features.favoritedrink.model

import brown.jeff.cocktailapp.features.favoritedrink.data.db.local.model.DrinkORM

data class DrinkFavoriteDetailModel(
    val id: String,
    val imgURL: String,
    val title: String,
    val instructions: String,
    val ingredients: List<String>
)

fun DrinkORM.toDrinkFavoriteDetailModel(): DrinkFavoriteDetailModel {
    val ingredientList = listOf<String?>(
        strIngredient1,
        strIngredient2,
        strIngredient3,
        strIngredient4,
        strIngredient5,
        strIngredient6,
        strIngredient7,
        strIngredient8,
        strIngredient9,
        strIngredient10,
        strIngredient11,
        strIngredient12,
        strIngredient13,
        strIngredient14,
        strIngredient15
    )

    val list = mutableListOf<String>()
    for (item in ingredientList) {
        if (item != null)
            list.add(item)
    }

    return DrinkFavoriteDetailModel(
        id = idDrink,
        imgURL = drinkImg,
        title = drink,
        instructions = strInstructions,
        ingredients = list
    )
}