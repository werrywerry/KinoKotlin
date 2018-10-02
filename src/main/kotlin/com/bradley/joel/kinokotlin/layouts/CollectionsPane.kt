package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import com.bradley.joel.kinokotlin.models.FilmCollection
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import java.util.*

class CollectionsPane(collections: TreeMap<String, ArrayList<Film>>): VBox(20.0) {

    val removeButton = Button("Remove")
    val newButton = Button("New")
    val hbox = HBox(10.0)
    var treeView = CollectionsTreeView(collections)

    init {

        removeButton.id = "standard-button"
        newButton.id = "standard-button"
        hbox.padding = Insets(10.0)
        hbox.alignment = Pos.CENTER
        hbox.children.addAll(newButton, removeButton)

        VBox.setVgrow(treeView, Priority.ALWAYS)

        maxWidth = 0.0
        minWidth = 0.0

        children.addAll(hbox, treeView)
    }

    fun hideCollections() {
        maxWidth = 0.0
        minWidth = 0.0
    }

    fun showCollections() {
        maxWidth = 200.0
        minWidth = 200.0
    }

}