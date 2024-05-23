package com.example.ozinshe.presentation.composables.textfields

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun AuthTextField(
    initValue: String,
    onValueChange: (String) -> Unit,
    topPadding: Dp = 16.dp,
    title: String,
    placeholderText: String,
    leftIconResource: Int,
    haveSecondIcon: Boolean,
    rightIconResource: Int = 0
) {
    val unFocusedBorderColor = MaterialTheme.colorScheme.secondary
    val focusedBorderColor = MaterialTheme.colorScheme.primary
    val (borderColor, setBorderColor) = remember { mutableStateOf(unFocusedBorderColor) }

    Row(
        modifier = Modifier.padding(top = topPadding)
    ) {
        Column {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 5.dp)
            )
            TextField(
                placeholder = {
                    Text(
                        text = placeholderText,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                value = initValue,
                onValueChange = onValueChange,
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .onFocusChanged {
                        if (it.isFocused) {
                            setBorderColor(focusedBorderColor)
                        } else {
                            setBorderColor(unFocusedBorderColor)
                        }
                    }
                    .border(
                        1.dp,
                        borderColor,
                        RoundedCornerShape(12.dp)
                    ),
                textStyle = MaterialTheme.typography.titleMedium,
                leadingIcon = {
                    Image(
                        ImageVector.vectorResource(leftIconResource),
                        contentDescription = "Email icon",
                        modifier = Modifier
                            .padding(16.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    if (haveSecondIcon) {
                        Image(
                            ImageVector.vectorResource(rightIconResource),
                            contentDescription = "Email icon",
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    } else null
                }
            )
        }
    }
}