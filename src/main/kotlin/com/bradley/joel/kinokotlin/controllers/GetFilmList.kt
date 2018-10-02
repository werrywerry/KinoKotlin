package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import com.google.gson.Gson
import com.google.gson.JsonArray
import kotlin.collections.ArrayList

fun getFilmList(json: JsonArray): ArrayList<Film> {
    var films = ArrayList<Film>()
    val gson = Gson()

    for (film in json) {
        films.add(gson.fromJson<Film>(film, Film::class.java))
    }

    return films

}