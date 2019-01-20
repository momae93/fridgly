package com.au.fridgly.presentation.models.recipeDetails

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EquipmentUI(val id: Int,
                       val name: String,
                       val image: String) : Parcelable