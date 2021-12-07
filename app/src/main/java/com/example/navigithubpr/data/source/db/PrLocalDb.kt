package com.example.truecreditslist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.navigithubpr.data.response.GithubIssuesResponse
import com.example.navigithubpr.data.source.db.UserConverter


@Database(entities = [GithubIssuesResponse::class], version = 1, exportSchema = false)
@TypeConverters(UserConverter::class)
abstract class PrLocalDb : RoomDatabase() {

    abstract fun prDao(): PrDao

}