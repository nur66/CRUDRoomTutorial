package com.example.crudroomtutorial.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)
    val idn: Int,
    val title: String,
    val note: String
    )
