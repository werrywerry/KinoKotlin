package com.bradley.joel.kinokotlin.controllers

import javafx.stage.DirectoryChooser
import javafx.stage.Stage
import java.io.*

fun getPath(slash: String, parent: Stage): String {
    var path = ""

    if (File("." + slash + "data.sav").isFile) {
        val fis = FileInputStream("." + slash + "data.sav")
        val ois = ObjectInputStream(fis)
        path = ois.readObject() as String
        ois.close()
    } else {
        val directoryChooser = DirectoryChooser()
        directoryChooser.title = "Choose Movie Directory"
        val movieDirectory = directoryChooser.showDialog(parent)
        path = movieDirectory.absolutePath

        val fos = FileOutputStream("." + slash + "data.sav")
        val oos = ObjectOutputStream(fos)
        oos.writeObject(path)
        oos.close()
    }

    return path
}