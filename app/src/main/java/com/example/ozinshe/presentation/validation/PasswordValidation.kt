package com.example.ozinshe.presentation.validation

import android.util.Patterns

object PasswordValidation {

    fun execute (password: String): ValidationResult {
        val containsLettersAndDigits = password.any { it.isDigit() } && password.any { it.isLetter() }

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль не может быть пустым"
            )
        }
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать не менее 8 символов"
            )
        }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать буквы и цифры"
            )
        }
        else return ValidationResult(
            successful = true,
        )
    }
}