package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film

fun resetFilmsToDisplay(filmList: ArrayList<Film>): ArrayList<Film> {
    val filmsToDisplay = ArrayList<Film>()

    for (film in filmList) {
        filmsToDisplay.add(film)
    }

    return filmsToDisplay
}