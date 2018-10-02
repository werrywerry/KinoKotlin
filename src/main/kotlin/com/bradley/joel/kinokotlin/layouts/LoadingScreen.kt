package com.bradley.joel.kinokotlin.layouts

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.layout.StackPane

class LoadingScreen: StackPane() {

    init {

        val label = Label("Gathering film data...")
        label.id = "title-label"
        style =  "-fx-background-color: #333"
        StackPane.setAlignment(label, Pos.CENTER)

    }

}