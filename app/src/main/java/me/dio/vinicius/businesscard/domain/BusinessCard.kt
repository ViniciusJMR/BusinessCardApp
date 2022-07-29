package me.dio.vinicius.businesscard.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String,
    val business: String,
    val phone: String,
    val backgroundColor: String
)