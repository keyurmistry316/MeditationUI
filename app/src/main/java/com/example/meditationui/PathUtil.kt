package com.example.meditationui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import kotlin.math.abs


fun Path.standerQuardTo(from:Offset, to:Offset)  {
    quadraticBezierTo(
        from.x,
        from.y,
        abs(from.y+from.y)/2f,
        abs(from.x+to.y)/2f
    )
}