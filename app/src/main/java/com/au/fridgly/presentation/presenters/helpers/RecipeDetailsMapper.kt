package com.au.fridgly.presentation.presenters.helpers

import com.au.fridgly.domain.models.recipeDetails.Equipment
import com.au.fridgly.domain.models.recipeDetails.Ingredient
import com.au.fridgly.domain.models.recipeDetails.Step
import com.au.fridgly.presentation.models.recipeDetails.EquipmentUI
import com.au.fridgly.presentation.models.recipeDetails.IngredientUI
import com.au.fridgly.presentation.models.recipeDetails.StepUI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeDetailsMapper @Inject constructor() {
    fun transform(elt: Step) : StepUI {
        val equipments = elt.equipments?.map(this::transform)
        val ingredients = elt.ingredients?.map(this::transform)
        return StepUI(number = elt.number, description = elt.description,
            ingredients = ingredients, equipments = equipments)
    }

    private fun transform(elt: Ingredient): IngredientUI {
        return IngredientUI(id = elt.id, name = elt.name, image = elt.image)
    }

    private fun transform(elt: Equipment): EquipmentUI {
        return EquipmentUI(id = elt.id, name = elt.name, image = elt.image)
    }
}