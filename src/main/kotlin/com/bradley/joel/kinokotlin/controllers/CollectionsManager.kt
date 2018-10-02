package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import java.util.*
import kotlin.collections.ArrayList

class CollectionsManager {

    fun getCollections(): TreeMap<String, ArrayList<Film>> {
        val collections = TreeMap<String, ArrayList<Film>>()
        return collections
    }

    fun addCollection(name: String, collections: TreeMap<String, ArrayList<Film>>): TreeMap<String, ArrayList<Film>> {
        collections[name] = ArrayList<Film>()
        return collections
    }

    fun removeCollection(name: String, collections: TreeMap<String, ArrayList<Film>>): TreeMap<String, ArrayList<Film>> {

        collections.remove(name)
        return collections
    }

    fun addFilmToCollection(collections: TreeMap<String, ArrayList<Film>>, name: String, film: Film): TreeMap<String, ArrayList<Film>> {
        val list = collections[name]
        list?.add(film)
        var sortedList = list?.sortedWith(compareBy({ it.Title }))
        collections[name] = ArrayList<Film>()
        for (film in sortedList!!) {
            collections[name]?.add(film)
        }
        return collections
    }

    fun removeFilmFromCollection(collections: TreeMap<String, ArrayList<Film>>, name: String, collection: String): TreeMap<String, ArrayList<Film>> {
        val filmList = collections.get(collection)!!
        val itr = filmList.iterator()
        while (itr.hasNext()) {
            if (itr.next().Title == name) {
                itr.remove()
            }
        }
        return collections
    }

}