package com.example.ozinshe.presentation.screens.registration.ui

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
import com.example.ozinshe.presentation.screens.registration.viewmodel.RegistrationViewModel
import com.example.ozinshe.presentation.theme.Dimension
import com.example.ozinshe.presentation.validation.EmailValidation
import com.example.ozinshe.presentation.validation.PasswordValidation
import com.example.ozinshe.presentation.validation.RepeatPasswordValidation
import com.example.ozinshe.presentation.validation.ValidationResult


@Composable
fun RegistrationScreen(
    signUpButtonClick: () -> Unit,
    navToLoginScreen: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    var email by rememberSaveable { mutableStateOf("") }
    var emailError by rememberSaveable { mutableStateOf("") }

    var password by rememberSaveable { mutableStateOf("") }
    var passwordError by rememberSaveable { mutableStateOf("") }

    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var repeatPasswordError by rememberSaveable { mutableStateOf("") }

    val screenState by viewModel.registrationState.collectAsState()

    LaunchedEffect(screenState) {
        when {
            screenState.user != null -> {
                signUpButtonClick()
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
                text = stringResource(id = R.string.signUpTitle),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(Dimension.verticalSubPadding))
            Text(
                text = stringResource(id = R.string.signUpTitleDesc),
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
        AuthTextField(
            initValue = repeatPassword,
            errorMessage = repeatPasswordError,
            onValueChange = { repeatPassword = it },
            title = stringResource(id = R.string.passwordRepeatLabel),
            placeholderText = stringResource(id = R.string.passwordPlaceholder),
            leftIconResource = R.drawable.ic_password,
            isPasswordField = true,
        )
        Spacer(modifier = Modifier.height(Dimension.verticalPadding))
        CommonSubmitButton(
            text = stringResource(id = R.string.signUpBtn),
            onClick = {
                emailError = ""
                passwordError = ""
                repeatPasswordError = ""
                when {
                    !EmailValidation.execute(email).successful -> {
                        emailError = EmailValidation.execute(email).errorMessage.toString()
                    }
                    !PasswordValidation.execute(password).successful -> {
                        passwordError = PasswordValidation.execute(password).errorMessage.toString()
                    }
                    !RepeatPasswordValidation.execute(password, repeatPassword).successful -> {
                        repeatPasswordError = RepeatPasswordValidation.execute(password, repeatPassword).errorMessage.toString()
                    }
                    else -> {
                        viewModel.registration(email = email, password = password)
                    }
                }

            }
        )
        Spacer(modifier = Modifier.height(Dimension.verticalSubPadding * 3f))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AuthHelpButton(
                helpText = stringResource(id = R.string.help_to_login),
                actionText = stringResource(id = R.string.signInBtn)
            ) {
                navToLoginScreen()
            }
        }
    }
}