package com.example.navigithubpr.data.source.db

import androidx.room.TypeConverter
import com.example.navigithubpr.data.response.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class UserConverter {
    @TypeConverter
    fun storedStringToMyObjects(data: String?): User {
        val gson = Gson()
        val listType: Type = object : TypeToken<User?>() {}.getType()
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: User): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}