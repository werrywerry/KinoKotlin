package com.bradley.joel.kinokotlin.layouts

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

class NewCollectionDialog(): Stage() {

    val collectionName = TextField()
    val acceptButton = Button("Accept")
    val cancelButton = Button("Cancel")

    init {

        width = 300.0
        val vbox = VBox(30.0)
        val buttonBox = HBox(20.0)
        vbox.padding = Insets(40.0, 10.0, 10.0, 10.0)
        buttonBox.alignment = Pos.CENTER
        buttonBox.padding = Insets(10.0)
        acceptButton.style = null
        cancelButton.style = null
        cancelButton.setOnAction { close() }
        acceptButton.isDefaultButton = true
        buttonBox.children.addAll(acceptButton, cancelButton)
        vbox.children.addAll(collectionName, buttonBox)
        title = "Enter FilmCollection Name"
        val windowScene = Scene(vbox)
        scene = windowScene

    }

    fun getNewCollectionName(): String {
        return collectionName.text
    }

}