package com.bradley.joel.kinokotlin.layouts

import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.TextAlignment
import javafx.stage.Modality
import javafx.stage.Stage

class ConfirmBox {

    private var answer: Boolean = false

    fun display(path: String): Boolean {
        val window = Stage()
        window.initModality(Modality.APPLICATION_MODAL)
        window.title = "Data Not Found"

        val label = Label("Film data file not found in $path. Would you like to generate this now. This could take some time.")
        label.isWrapText = true
        label.textAlignment = TextAlignment.CENTER

        val yesButton = Button("Yes")
        val noButton = Button("No")

        yesButton.setOnAction { e ->
            answer = true
            window.close()
        }
        noButton.setOnAction { e ->
            answer = false
            window.close()
        }

        val messagePane = HBox(20.0)
        messagePane.padding = Insets(20.0)
        messagePane.alignment = Pos.CENTER
        messagePane.children.add(label)

        val buttonPane = HBox(20.0)
        buttonPane.padding = Insets(20.0)
        buttonPane.alignment = Pos.CENTER
        buttonPane.children.addAll(yesButton, noButton)

        val layout = VBox(20.0)
        layout.children.addAll(messagePane, buttonPane)

        val scene = Scene(layout)
        window.scene = scene
        window.showAndWait()

        return answer
    }

}
