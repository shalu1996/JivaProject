package com.example.jivaproject.jiva.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villageTable")
data class VillageTable(
    @PrimaryKey
    val name: String,
    val perKgCost: Double
)