package me.dio.vinicius.businesscard.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var email: String,
    var business: String,
    var phone: String,
    var backgroundColor: String
)