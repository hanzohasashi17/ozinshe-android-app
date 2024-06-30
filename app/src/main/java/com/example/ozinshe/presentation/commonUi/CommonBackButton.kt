package com.example.ozinshe.presentation.commonUi

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.ozinshe.R
import com.example.ozinshe.presentation.theme.Dimension

@Composable
fun CommonBackButton(
    onBackButtonClick: () -> Unit
) {
    IconButton(
        onClick = { onBackButtonClick() }
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "back",
            modifier = Modifier
                .size(Dimension.iconSize)
        )
    }
}