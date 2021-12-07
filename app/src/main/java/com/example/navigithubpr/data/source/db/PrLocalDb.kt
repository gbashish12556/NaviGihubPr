package com.example.truecreditslist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigithubpr.data.response.GithubIssuesResponse


@Database(entities = [GithubIssuesResponse::class], version = 1, exportSchema = false)
abstract class PrLocalDb : RoomDatabase() {

    abstract fun prDao(): PrDao

}