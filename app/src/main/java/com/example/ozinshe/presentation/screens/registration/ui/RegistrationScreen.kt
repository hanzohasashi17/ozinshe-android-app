package com.example.ozinshe.presentation.screens.registration.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.R
import com.example.ozinshe.presentation.composables.buttons.SubmitButtonComponent
import com.example.ozinshe.presentation.composables.textfields.AuthTextField
import com.example.ozinshe.presentation.screens.registration.viewmodel.RegistrationViewModel


@Composable
fun RegistrationScreen(
    onBackButtonClick: () -> Unit,
    signInButtonClick: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (password, setPassword) = rememberSaveable { mutableStateOf("") }
    val (repeatPassword, setRepeatPassword) = rememberSaveable { mutableStateOf("") }

    val registrationState by viewModel.registrationState.collectAsState()

    LaunchedEffect(registrationState) {
        when {
            registrationState.user != null -> {
                signInButtonClick()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 24.dp)
    ) {
        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            IconButton(
                onClick = onBackButtonClick,
                content = {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_btn),
                        contentDescription = "Back",
                    )
                }
            )
        }
        Row(
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.tirkelu),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(id = R.string.derekterdi_toltyrynyz),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
        AuthTextField(
            initValue = email,
            onValueChange = { setEmail(it) },
            topPadding = 32.dp,
            title = "Email",
            placeholderText = stringResource(R.string.sizdin_email),
            leftIconResource = R.drawable.ic_email,
            haveSecondIcon = false
        )
        AuthTextField(
            initValue = password,
            onValueChange = { setPassword(it) },
            title = stringResource(id = R.string.qupia_soz),
            placeholderText = stringResource(id = R.string.sizdin_qupia_soziniz),
            leftIconResource = R.drawable.ic_password,
            haveSecondIcon = true,
            rightIconResource = R.drawable.ic_eye
        )
        AuthTextField(
            initValue = repeatPassword,
            onValueChange = { setRepeatPassword(it) },
            title = stringResource(id = R.string.qupia_sozdi_qaitalanyz),
            placeholderText = stringResource(id = R.string.sizdin_qupia_soziniz),
            leftIconResource = R.drawable.ic_password,
            haveSecondIcon = true,
            rightIconResource = R.drawable.ic_eye
        )
        SubmitButtonComponent(
            text = stringResource(id = R.string.tirkelu),
            onClick = {
                viewModel.sendRegisterData(email = email, password = password, repeat = repeatPassword)
            },
            topPadding = 40.dp
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.sizde_akkaunt_bar_ma),
                modifier = Modifier
                    .clickable {  }
                    .fillMaxWidth(),
                style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                ),
                textAlign = TextAlign.End
            )
        }
    }
}