package com.example.ozinshe.presentation.screens.profile.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.data.models.UserProfile
import com.example.ozinshe.presentation.screens.profile.viewmodel.ProfileViewModel
import com.example.ozinshe.presentation.utils.HorizontalLine
import com.example.ozinshe.presentation.utils.LoadingComponent

@Composable
fun ProfileDataScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profileState by viewModel.profileState.collectAsState()

    val interactionSource = remember { MutableInteractionSource() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focusManager.clearFocus()
            },
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        ProfileDataScreenHeader()
        HorizontalLine()
        when {
            profileState.isLoading -> {
                LoadingComponent()
            }

            profileState.userData != null -> {
                ProfileData(
                    userData = profileState.userData,
                    viewModel = viewModel
                )
            }

            profileState.error != null -> {
                Text(text = profileState.error!!)
            }
        }
    }
}


@Composable
fun ProfileData(
    userData: UserProfile?,
    viewModel: ProfileViewModel
) {
    var name by remember { mutableStateOf(userData?.name ?: "") }
    var email by remember { mutableStateOf(userData?.user?.email ?: "") }
    var phoneNumber by remember { mutableStateOf(userData?.phoneNumber ?: "") }
    var birthDate by remember { mutableStateOf(userData?.birthDate ?: "") }

    Column(
        modifier = Modifier
            .padding(all = 24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            EditableTextField(title = "Name", value = name, placeholder = "") { name = it }
            EditableTextField(title = "Phone", value = phoneNumber, placeholder = "") { phoneNumber = it }
            EditableTextField(title = "Birthday", value = birthDate, placeholder = "YYYY-MM-DD") { birthDate = it }
        }

        Button(
            onClick = {
                viewModel.updateProfileData(
                    name = name,
                    phoneNumber = phoneNumber,
                    birthDate = birthDate
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "SAVE",
                modifier = Modifier
                    .padding(vertical = 8.dp),
                )
        }
    }
}

@Composable
fun EditableTextField(title: String, value: String, placeholder: String, onValueChange: (String) -> Unit) {
    val interactiveSource = remember { MutableInteractionSource() }
    var isEditing by remember { mutableStateOf(false) }
    val textFieldFocus = remember { FocusRequester() }
    val isFocused by interactiveSource.collectIsFocusedAsState()

    LaunchedEffect(isEditing) {
        if (isEditing) {
            textFieldFocus.requestFocus()
        }
    }

    LaunchedEffect(isFocused) {
        if (!isFocused) {
            isEditing = false
        }
    }

    Text(text = title)
    if (isEditing) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .focusRequester(textFieldFocus)
                .fillMaxWidth(),
            interactionSource = interactiveSource,
            placeholder = {
                Text(text = placeholder)
            }
        )
    } else {
        Text(
            text = value,
            textAlign = TextAlign.Start,
            modifier = Modifier.clickable {
                isEditing = true
            }
        )
    }
    HorizontalLine()
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun ProfileDataScreenHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                )
            }
        }
        Text(
            text = "Profile",
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        )
    }
}