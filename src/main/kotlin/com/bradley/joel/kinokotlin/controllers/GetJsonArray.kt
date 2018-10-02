package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.layouts.ConfirmBox
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.stream.JsonReader
import java.io.File
import java.io.FileReader

fun getJsonArray(path: String, slash: String): JsonArray {

    if (!File("$path" + slash + "FilmDetails.json").isFile) {
        if (ConfirmBox().display(path)) {
            GenerateJsonArray().generateJsonArray(arrayOf(path, slash))
        } else {
            System.exit(0)
        }
    }

    val gson = Gson()
    var reader = JsonReader(FileReader(path + slash + "FilmDetails.json"))
    return gson.fromJson(reader, JsonArray::class.java)

}

