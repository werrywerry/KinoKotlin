package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.models.Film
import com.google.gson.*
import com.google.gson.stream.JsonReader
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.nio.file.Paths
import java.nio.file.Files

class GenerateJsonArray {


    var filmPath = ""
    private val omdbUrl = "http://www.omdbapi.com/"
    private val apiKey = "?i=tt3896198&apikey=e2763071"
    private val searchByTitle = "&t="
    private var oldFilmTitles = ArrayList<String>()
    private val filmTitles = ArrayList<String>()
    private val titlesToSearch = ArrayList<String>()
    private val films = ArrayList<Film>()
    var filmsAsJson: JsonArray = JsonArray()
    var slash = ""
    var sortedJsonList: JsonArray = JsonArray()


    fun generateJsonArray(args: Array<String>) {

        println("Application started")

        filmPath = args[0]
        slash = args[1]

        getFilmTitles()
        getJson()
        getOldFilmTitles()
        getTitlesToSearch()
        getFullJson(titlesToSearch)
        instantiateFilms(filmsAsJson)
        sortList(films)
        getPosters(films)
        saveFilmTitles(filmTitles)
        saveToJsonFile(sortedJsonList)
    }

    private fun getFilmTitles() {
        var fileName: String
        File(filmPath).walkTopDown().forEach {
            fileName = it.toString()
            fileName = fileName.substringAfterLast(slash)
            if (fileName != "Movies" && !fileName.contains(".") && !fileName.contains("Studio")) {
                filmTitles.add(fileName)
            }
            if (fileName == "L.A. Confidential") {
                filmTitles.add(fileName)
            }
            if (fileName == "Mr. & Mrs. Smith") {
                filmTitles.add(fileName)
            }
        }
    }

    private fun getJson() {
        if (File(filmPath + slash + "FilmDetails.json").isFile) {
            val reader = JsonReader(FileReader(filmPath + slash + "FilmDetails.json"))
            filmsAsJson = Gson().fromJson(reader, JsonArray::class.java)
        }
    }

    private fun getOldFilmTitles() {
        if (File(filmPath + slash + "FilmTitles.sav").isFile) {
            val fis = FileInputStream(filmPath + slash + "FilmTitles.sav")
            val ois = ObjectInputStream(fis)
            oldFilmTitles = ois.readObject() as ArrayList<String>
            println("OFT = $oldFilmTitles")
        }
    }

    private fun getTitlesToSearch() {
        filmTitles.stream().filter { x -> !oldFilmTitles.contains(x) }.forEach { x -> titlesToSearch.add(x) }
    }

    private fun getFullJson(titles: ArrayList<String>) {
        for (i in titles) {
            var year = ""
            var searchByYear = ""
            var title = removeSpaces(i)
            if (title.contains("(")) {
                year = getYear(title)
                title = removeYear(title)
                searchByYear = "&y="
            }
            filmsAsJson.add(getMovieAsJson(title, searchByYear, year))
        }
    }

    private fun instantiateFilms(filmsAsJson: JsonArray) {
        for (i in 0 until filmsAsJson.size()) {
            films.add(getMovieAsFilm(filmsAsJson[i]))
        }
    }

    private fun sortList(list: ArrayList<Film>) {
        var sortedList = list.sortedWith(compareBy({ it.Title }))
        for (film in sortedList) {
            val response = Gson().toJson(film, Film::class.java)
            val json = JsonParser().parse(response)
            sortedJsonList.add(json)
        }
    }

    private fun getPosters(films: ArrayList<Film>) {
        for (i in films) {
            var title = i.Title
            var year = i.Year
            title = title.replace(":", "")
            if (File("$filmPath$slash$title").isDirectory && !File("$filmPath$slash$title$slash$title.jpg").exists()) {
                println("$filmPath$slash$title is a directory")
                URL(i.Poster).openStream().use { `in` -> Files.copy(`in`, Paths.get("$filmPath$slash$title$slash$title.jpg")) }
            }
            if (File("$filmPath$slash$title($year)").isDirectory && !File("$filmPath$slash$title($year)$slash$title($year).jpg").exists()) {
                println("Exists")
                URL(i.Poster).openStream().use { `in` -> Files.copy(`in`, Paths.get("$filmPath$slash$title($year)$slash$title($year).jpg")) }
            }
        }
    }

    private fun saveFilmTitles(filmTitles: MutableList<String>) {
        val fos = FileOutputStream(filmPath + slash + "FilmTitles.sav")
        val oos = ObjectOutputStream(fos)
        oos.writeObject(filmTitles)
    }

    private fun saveToJsonFile(filmsAsJson: JsonArray) {
        File(filmPath + slash + "FilmDetails.json").printWriter().use { out ->
            out.println(filmsAsJson)
        }
    }


    private fun getMovieAsFilm(filmAsJson: JsonElement): Film {
        return Gson().fromJson(filmAsJson, Film::class.java)
    }


    private fun getMovieAsJson(title: String, searchByYear: String, year: String): JsonObject {
        val searchTitle = title.replace("&", "%26")
        val connection = URL(omdbUrl + apiKey + searchByTitle + searchTitle + searchByYear + year).openConnection() as HttpURLConnection
        connection.inputStream.bufferedReader().use {
            return Gson().fromJson(it.readText(), JsonObject::class.java)
        }
    }

    private fun removeSpaces(title: String): String {
        return title.replace(" ", "+")
    }


    private fun getYear(title: String): String {
        var newTitle = title.replace("(", "")
        newTitle = newTitle.replace(")", "")
        return newTitle.substring(title.length - 6)
    }


    private fun removeYear(title: String): String {
        return title.substring(0, title.length - 6)
    }

}

