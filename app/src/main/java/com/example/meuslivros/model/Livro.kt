package com.example.meuslivros.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livro(
    var nome: String,
    var autor: String,
    var ano: Int,
    var nota: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}