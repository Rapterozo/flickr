package com.example.flickrapp.utils

import android.app.Activity
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.WindowMetricsCalculator

fun Activity.isLargeSizeScreen(): Boolean {
    val metrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(this)
    val width = metrics.bounds.width()
    val height = metrics.bounds.height()
    val density = resources.displayMetrics.density
    val windowSizeClass = WindowSizeClass.compute(width / density, height / density)
    // COMPACT, MEDIUM, or EXPANDED
    return windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
}