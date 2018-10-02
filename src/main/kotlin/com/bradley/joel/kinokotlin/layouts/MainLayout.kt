package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import com.bradley.joel.kinokotlin.models.SearchCriteria
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.ScrollPane
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority

class MainLayout(films: ArrayList<Film>, images: HashMap<String, ImageView>, columnCount: Int): BorderPane() {

    val title = TextField()
    val actor = TextField()
    val year = TextField()
    val genreButton = Button("Genre")
    val clearButton = Button("Clear Options")
    val rescanButton = Button("Rescan Films")
    val goButton = Button("Go")
    val collapseButton = Button("<->")
    private val buttonPanel = HBox(10.0)
    val filmGrid = GridLayout()
    private val scrollPane = ScrollPane(filmGrid)

    init {

        style = "-fx-background-color: #333"
        buttonPanel.padding = Insets(10.0)
        title.promptText = "Film Title"
        actor.promptText = "Actor"
        year.promptText = "Year"
        goButton.isDefaultButton = true
        HBox.setHgrow(title, Priority.ALWAYS)
        HBox.setHgrow(actor, Priority.ALWAYS)
        buttonPanel.children.addAll(collapseButton, title, actor, year, genreButton, clearButton, rescanButton, goButton)

        filmGrid.populateGrid(films, images, columnCount)
        scrollPane.hbarPolicy = ScrollPane.ScrollBarPolicy.NEVER

        top = buttonPanel
        center = scrollPane

    }
    fun gatherSearchCriteria(genres: ArrayList<String>): SearchCriteria {
        return SearchCriteria(title.text, actor.text, year.text, genres)
    }
}