package com.udacity.shoestore.utils

import androidx.databinding.InverseMethod

object Converters {

    @InverseMethod("stringToDouble")
    @JvmStatic
    fun doubleToString(value: Double?): String {
        return value?.toString() ?: ""
    }

    @Suppress("RedundantNullableReturnType")
    @JvmStatic
    fun stringToDouble(value: String): Double? {
        return try {
            value.toDouble()
        } catch (_: Exception) {
            0.0
        }
    }
}