package com.bradley.joel.kinokotlin.controllers

import com.bradley.joel.kinokotlin.layouts.DetailedLayout
import com.bradley.joel.kinokotlin.layouts.MainLayout
import com.bradley.joel.kinokotlin.models.Film
import javafx.scene.Scene
import javafx.scene.control.SplitPane
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane

class GetDetailedLayout(film: Film, path: String, image: ImageView, slash: String, splitPane: SplitPane, mainLayout: MainLayout) {

    val detailedLayout = DetailedLayout(film, image)

    init {
        detailedLayout.watchButton.setOnAction { watchFilm(film, path, slash) }
        detailedLayout.backButton.setOnAction { splitPane.items[1] = mainLayout }
        splitPane.items[1] = detailedLayout
    }

    fun returnDetailedLayout(): DetailedLayout {
        return detailedLayout
    }
}
