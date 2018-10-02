package com.bradley.joel.kinokotlin.layouts

import com.bradley.joel.kinokotlin.models.Film
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.text.TextAlignment
import java.io.File
import java.io.FileInputStream
import java.util.ArrayList

class DetailedLayout(film: Film, image: ImageView) : BorderPane() {

    var infoFields = arrayOf("Year", "Rated", "Released", "Runtime", "Genre", "Director", "Writer", "Actors", "Plot", "Language", "Country", "Awards")
    var labels = ArrayList<Label>()
    var infos = ArrayList<String>()
    var infoLabels = ArrayList<Label>()
    val backButton = Button("Back")
    val collapseButton = Button("<->")
    val addButton = Button("Add")
    val watchButton = Button("Watch")
    private var imageAndTitleLayout: VBox
    private var infoLayout: GridPane

    init {

        style = "-fx-background-color: #333"
        getInfos(film)

        for (i in infoFields.indices) {
            val label = Label(infoFields[i] + ":")
            label.id = "info-label"
            labels.add(label)
        }

        val titleLabel = Label(film.Title)
        titleLabel.maxWidth = 300.0
        titleLabel.alignment = Pos.CENTER
        titleLabel.textAlignment = TextAlignment.CENTER
        titleLabel.isWrapText = true
        titleLabel.id = "title-label"
        AnchorPane.setLeftAnchor(titleLabel, 0.0)
        AnchorPane.setRightAnchor(titleLabel, 0.0)

        imageAndTitleLayout = VBox(10.0)
        imageAndTitleLayout.padding = Insets(40.0, 50.0, 0.0, 50.0)
        imageAndTitleLayout.children.addAll(image, titleLabel)

        infoLayout = GridPane()

        infoLayout.columnConstraints.addAll(ColumnConstraints(20.0),
                ColumnConstraints(100.0),
                ColumnConstraints(),
                ColumnConstraints(20.0))
        infoLayout.vgap = 20.0

        for (i in labels.indices) {
            infoLayout.add(labels[i], 1, i + 1)
            infoLayout.add(infoLabels[i], 2, i + 1)
        }

        val buttonPanel = HBox(10.0)
        buttonPanel.padding = Insets(10.0)
        buttonPanel.children.addAll(collapseButton, backButton, watchButton, addButton)

        left = imageAndTitleLayout
        center = infoLayout
        top = buttonPanel

    }

    private fun getInfos(film: Film) {

        infos.add(film.Year)
        infos.add(film.Rated)
        infos.add(film.Released)
        infos.add(film.Runtime)
        infos.add(film.Genre)
        infos.add(film.Director)
        infos.add(film.Writer)
        infos.add(film.Actors)
        infos.add(film.Plot)
        infos.add(film.Language)
        infos.add(film.Country)
        infos.add(film.Awards)

        for (info in infos) {
            val label = Label(info)
            label.isWrapText = true
            label.textAlignment = TextAlignment.JUSTIFY
            label.id = "info-label"
            infoLabels.add(label)
        }
    }
}
