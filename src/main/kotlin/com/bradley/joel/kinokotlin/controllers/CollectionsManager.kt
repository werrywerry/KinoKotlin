package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import java.util.*
import kotlin.collections.ArrayList

class CollectionsManager {

    fun getCollections(): TreeMap<String, TreeMap<String, Film>> {
        val collections = TreeMap<String, TreeMap<String, Film>>()
        return collections
    }

    fun addCollection(name: String, collections: TreeMap<String, TreeMap<String, Film>>): TreeMap<String, TreeMap<String, Film>> {
        collections[name] = TreeMap<String, Film>()
        println(collections[name])
        return collections
    }

    fun removeCollectionItem(name: String, parent: String, collections: TreeMap<String, TreeMap<String, Film>>): TreeMap<String, TreeMap<String, Film>> {
        if (parent == "") {
            collections.remove(name)
        } else {
            collections[parent]!!.remove(name)
        }
        return collections
    }

    fun addFilmToCollection(collections: TreeMap<String, TreeMap<String, Film>>, name: String, film: Film): TreeMap<String, TreeMap<String, Film>> {
        collections[name]!![film.Title] = film
        return collections
    }

}