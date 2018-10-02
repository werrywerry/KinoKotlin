package com.bradley.joel.kinokotlin.layouts

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Modality
import javafx.stage.Stage

class GenreSelectBox: Stage() {

    val checkBoxes = ArrayList<CheckBox>()

    init {
        title = "Select Genres"
        initModality(Modality.APPLICATION_MODAL)
        val layout = BorderPane()
        val checkBoxLayout = VBox(10.0)
        val buttonLayout = HBox(20.0)
        val insets = Insets(20.0)
        val accept = Button("Accept")
        val cancel = Button("Cancel")
        val genres = arrayOf(
                "Action",
                "Adventure",
                "Animation",
                "Comedy",
                "Crime",
                "Drama",
                "Fantasy",
                "Horror",
                "Mystery",
                "Romance",
                "Sci-Fi",
                "Thriller"
        )

        layout.padding = insets
        buttonLayout.padding = insets
        checkBoxLayout.padding = insets

        for (genre in genres) {
            val checkBox = CheckBox(genre)
            checkBoxes.add(checkBox)
            checkBoxLayout.children.add(checkBox)
        }

        accept.setOnAction { close() }
        cancel.setOnAction { close() }
        buttonLayout.children.addAll(accept, cancel)

        layout.center = checkBoxLayout
        layout.bottom = buttonLayout

        val scene = Scene(layout)
        setScene(scene)
    }

    fun gatherGenres(): ArrayList<String> {
        val gatheredGenres = ArrayList<String>()
        for (checkBox in checkBoxes) {
            if (checkBox.isSelected) {
                gatheredGenres.add(checkBox.text)
            }
        }
        return gatheredGenres
    }

/*
    override fun showAndWait() {
        super.showAndWait()
    }
*/
}