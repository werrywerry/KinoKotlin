package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment

class GridElement(film: Film, image: ImageView?) : VBox() {

    init {

        spacing = 10.0

        val label = Label(film.Title)
        label.maxWidth = 180.0
        AnchorPane.setLeftAnchor(label, 0.0)
        AnchorPane.setRightAnchor(label, 0.0)
        label.textAlignment = TextAlignment.CENTER
        label.alignment = Pos.CENTER
        label.isWrapText = true
        label.id = "title-label"
        //label.setPadding(new Insets(0,0,0,40));

        image?.fitWidth = 180.0
        image?.fitHeight = 270.0
        children.addAll(image, label)
    }
}
