package com.bradley.joel.kinokotlin.models

data class SearchCriteria(
        val title: String,
        val actor: String,
        val year: String,
        val genre: ArrayList<String>
)