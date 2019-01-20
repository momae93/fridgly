package com.au.fridgly.presentation.models.recipeDetails

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StepUI(val number: Int,
                  val description: String,
                  val ingredients: List<IngredientUI>?,
                  val equipments: List<EquipmentUI>?) : Parcelable
