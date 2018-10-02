package com.bradley.joel.kinokotlin.controllers

fun getColumnCount(windowWidth: Double, collectionsWidth: Double): Int {
    var cols = 3
    if (windowWidth - collectionsWidth in 895..1099) {
        cols = 4
    }
    if (windowWidth - collectionsWidth in 1100..1324) {
        cols = 5
    }
    if (windowWidth - collectionsWidth in 1325..1549) {
        cols = 6
    }
    if (windowWidth - collectionsWidth in 1550..1774) {
        cols = 7
    }
    if (windowWidth - collectionsWidth in 1775..1999) {
        cols = 8
    }
    if (windowWidth - collectionsWidth in 2000..2224) {
        cols = 9
    }
    if (windowWidth - collectionsWidth in 2225..2449) {
        cols = 10
    }
    if (windowWidth - collectionsWidth >= 2450) {
        cols = 11
    }
    return cols
}
