package brown.jeff.cocktailapp.features.randomdrink.model

import brown.jeff.cocktailapp.data.model.DrinkEntity
import brown.jeff.cocktailapp.features.drinkdetail.model.DrinkDetailModel

data class RandomDrinkDetailModel(  val id: String,
                                    val imgURL: String,
                                    val title: String,
                                    val instructions: String,
                                    val ingredients: List<String>)


fun DrinkEntity.toRandomDrinkDetailModel(): RandomDrinkDetailModel {
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

    return RandomDrinkDetailModel(
        id = idDrink,
        imgURL = drinkImg,
        title = drink,
        instructions = strInstructions,
        ingredients = list
    )
}
