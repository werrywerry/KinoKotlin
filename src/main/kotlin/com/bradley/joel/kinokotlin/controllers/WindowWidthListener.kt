package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.layouts.MainLayout
import com.bradley.joel.kinokotlin.models.Film
import javafx.scene.image.ImageView
import javafx.stage.Stage

fun setWindowWidthListener(window: Stage, columnCount: Int, mainLayout: MainLayout, filmsToDisplay: ArrayList<Film>, images: HashMap<String, ImageView>, collectionsWidth: Double) {
    var width: Double
    var oldColumnCount = columnCount
    var newColumnCount: Int
    window.widthProperty().addListener { _, _, newValue ->
        width = newValue as Double
        newColumnCount = getColumnCount(width, collectionsWidth)
        if (newColumnCount != oldColumnCount) {
            oldColumnCount = newColumnCount
            mainLayout.filmGrid.populateGrid(filmsToDisplay, images, newColumnCount)
            println(filmsToDisplay)
        }
    }

}