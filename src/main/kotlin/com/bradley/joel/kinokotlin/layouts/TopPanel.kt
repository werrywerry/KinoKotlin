package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.SearchCriteria
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

class TopPanel: HBox(10.0) {

    val title = TextField()
    val actor = TextField()
    val year = TextField()
    val genreButton = Button("Genre")
    val clearButton = Button("Clear Options")
    val goButton = Button("Go")

    init {

        style = "-fx-background-color: #333"
        padding = Insets(10.0)
        title.promptText = "Film Title"
        actor.promptText = "Actor"
        year.promptText = "Year"
        goButton.isDefaultButton = true

        setHgrow(title, Priority.ALWAYS)
        setHgrow(actor, Priority.ALWAYS)

        children.addAll(title, actor, year, genreButton, clearButton, goButton)

    }

    fun gatherSearchCritirea(genres: ArrayList<String>): SearchCriteria {
        return SearchCriteria(title.text, actor.text, year.text, genres)
    }

}