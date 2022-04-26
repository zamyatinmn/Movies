package com.example.movies.data

import androidx.room.TypeConverter


class GenreConverters {

    @TypeConverter
    fun listToString(values: List<Int>): String {
        val strList = mutableListOf<String>()
        values.forEach {
            strList.add(it.toString())
        }
        return strList.joinToString(",")
    }

    @TypeConverter
    fun stringToList(value: String): List<Int> {
        val intList = mutableListOf<Int>()
        value.split(",").forEach {
            if (it.isNotBlank())
            intList.add(it.toInt())
        }
        return intList
    }

}