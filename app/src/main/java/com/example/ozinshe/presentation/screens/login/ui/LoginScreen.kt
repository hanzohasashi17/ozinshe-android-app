package com.example.ozinshe.presentation.screens.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ozinshe.R
import com.example.ozinshe.presentation.commonUi.AuthHelpButton
import com.example.ozinshe.presentation.commonUi.AuthTextField
import com.example.ozinshe.presentation.commonUi.CommonSubmitButton
import com.example.ozinshe.presentation.screens.login.viewmodel.LoginViewModel
import com.example.ozinshe.presentation.theme.Dimension
import com.example.ozinshe.presentation.validation.EmailValidation
import com.example.ozinshe.presentation.validation.PasswordValidation


@Composable
fun LoginScreen(
    signInButtonClick: () -> Unit,
    navToRegistrationScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }

    val screenState by viewModel.loginState.collectAsState()

    LaunchedEffect(screenState) {
        when {
            screenState.user != null -> {
                signInButtonClick()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimension.horizontalPadding)
    ) {
        Spacer(modifier = Modifier.height(Dimension.verticalPadding * 6f))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.signInTitle),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(Dimension.verticalSubPadding))
            Text(
                text = stringResource(id = R.string.signInTitleDesc),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier.height(Dimension.verticalPadding * 2f))
        AuthTextField(
            initValue = email,
            errorMessage = emailError,
            onValueChange = { email = it },
            title = "Email",
            placeholderText = stringResource(R.string.emailPlaceholder),
            leftIconResource = R.drawable.ic_email,
        )
        AuthTextField(
            initValue = password,
            errorMessage = passwordError,
            onValueChange = { password = it },
            title = stringResource(id = R.string.passwordLabel),
            placeholderText = stringResource(id = R.string.passwordPlaceholder),
            leftIconResource = R.drawable.ic_password,
            isPasswordField = true,
        )
        Spacer(modifier = Modifier.height(Dimension.verticalPadding))
        CommonSubmitButton(
            onClick = {
                emailError = ""
                passwordError = ""
                when {
                    !EmailValidation.execute(email).successful -> {
                        emailError = EmailValidation.execute(email).errorMessage.toString()
                    }

                    !PasswordValidation.execute(password).successful -> {
                        passwordError = PasswordValidation.execute(password).errorMessage.toString()
                    }

                    else -> viewModel.login(email = email, password = password)
                }
            },
            text = stringResource(id = R.string.signInBtn),
        )
        Spacer(modifier = Modifier.height(Dimension.verticalSubPadding * 3f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AuthHelpButton(
                helpText = stringResource(id = R.string.help_to_register),
                actionText = stringResource(id = R.string.signUpBtn)
            ) {
                navToRegistrationScreen()
            }
        }
    }
}