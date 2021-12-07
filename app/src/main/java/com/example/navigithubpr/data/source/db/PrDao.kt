package com.example.truecreditslist.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.navigithubpr.data.response.GithubIssuesResponse

@Dao
interface PrDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<GithubIssuesResponse>)

    @Query("SELECT * FROM issues_table")
    fun allPosts(): LiveData<List<GithubIssuesResponse>>

    @Query("DELETE FROM issues_table")
    suspend fun deleteSms()

}
