package com.example.jivaproject.jiva.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sellerTable", primaryKeys = ["name", "loyaltyCardNumber"])
data class SellerTable(
    val name: String,
    val loyaltyCardNumber: String,
    val villageName:String
)