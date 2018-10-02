package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import com.bradley.joel.kinokotlin.models.SearchCriteria
import org.apache.commons.lang3.StringUtils


fun performSearch(filmList: ArrayList<Film>, criteria: SearchCriteria): ArrayList<Film> {

    if (criteria.title != "") {
        searchByTitle(filmList, criteria.title)
    }

    if (criteria.actor != "") {
        searchByActor(filmList, criteria.actor)
    }

    if (criteria.year != "") {
        searchByYear(filmList, criteria.year)
    }

    if (criteria.genre.size != 0) {
        searchByGenre(filmList, criteria.genre)
    }

    return filmList

}

fun searchByTitle(filmList: ArrayList<Film>, title: String) {
    val itr = filmList.iterator()
    while (itr.hasNext()) {
        if (!StringUtils.containsIgnoreCase(itr.next().Title, title)) {
            itr.remove()
        }
    }
}

fun searchByActor(filmList: ArrayList<Film>, actor: String) {
    val itr = filmList.iterator()
    while (itr.hasNext()) {
        if (!StringUtils.containsIgnoreCase(itr.next().Actors, actor)) {
            itr.remove()
        }
    }

}

fun searchByYear(filmList: ArrayList<Film>, year: String) {
    val itr = filmList.iterator()
    while (itr.hasNext()) {
        if (!StringUtils.containsIgnoreCase(itr.next().Year, year)) {
            itr.remove()
        }
    }

}

fun searchByGenre(filmList: ArrayList<Film>, genres: ArrayList<String>) {
    val itr = filmList.iterator()
    while (itr.hasNext()) {
        var remove = false
        val film = itr.next()
        for (genre in genres) {
            if (!film.Genre.contains(genre)) {
                remove = true
            }
        }
        if (remove) {
            itr.remove()
        }
    }

}

