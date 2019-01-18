package com.au.fridgly.data.remote.mappers

import com.au.fridgly.data.remote.models.recipeDetails.ExtendedIngredientAPI
import com.au.fridgly.data.remote.models.recipeDetails.RecipeDetailsAPI
import com.au.fridgly.domain.models.ExtendedIngredient
import com.au.fridgly.domain.models.RecipeDetails
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailsMapper @Inject constructor() {
    fun transform(elt: RecipeDetailsAPI): RecipeDetails {
        val ingredients = elt.ingredients.map(this::transform)

        return RecipeDetails(id = elt.id, name = elt.name, servings = elt.servings,
            image = elt.image, instructions = elt.instructions, preparation = elt.preparation,
            cooking = elt.cooking, ready = elt.ready, likes = elt.likes, ingredients = ingredients)
    }

    private fun transform(elt: ExtendedIngredientAPI) : ExtendedIngredient {
        return ExtendedIngredient(id = elt.id, name = elt.name, image = elt.image,
            amount = elt.amount, unit = elt.unit)
    }
}