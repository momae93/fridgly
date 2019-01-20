package com.au.fridgly.data.remote.mappers

import com.au.fridgly.data.remote.models.recipeDetails.*
import com.au.fridgly.domain.models.recipeDetails.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailsMapper @Inject constructor() {

    fun transform(elt: RecipeDetailsAPI): RecipeDetails {
        val ingredients = elt.ingredients.map(this::transform)
        val steps = elt.analyzedInstructions?.first()?.steps?.map(this::transform)

        return RecipeDetails(
            id = elt.id, name = elt.name, servings = elt.servings,
            image = elt.image, instructions = elt.instructions, preparation = elt.preparation,
            cooking = elt.cooking, ready = elt.ready, likes = elt.likes, ingredients = ingredients,
            steps = steps
        )
    }

    private fun transform(elt: ExtendedIngredientAPI) : ExtendedIngredient {
        return ExtendedIngredient(
            id = elt.id, name = elt.name, image = elt.image,
            amount = elt.amount, unit = elt.unit
        )
    }

    private fun transform(elt: StepAPI) : Step {
        val equipments = elt.equipments?.map(this::transform)
        val ingredients = elt.ingredients?.map(this::transform)
        return Step(number = elt.number, description = elt.description,
            ingredients = ingredients, equipments = equipments)
    }

    private fun transform(elt: EquipmentAPI) : Equipment{
        return Equipment(id = elt.id, name = elt.name, image = elt.image)
    }

    private fun transform(elt: IngredientAPI) : Ingredient{
        return Ingredient(id = elt.id, name = elt.name, image = elt.image)
    }
}