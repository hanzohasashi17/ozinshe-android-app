package com.example.ozinshe.presentation.validation

import android.util.Patterns

object RepeatPasswordValidation {

    fun execute (password: String, repeatPassword: String): ValidationResult {
        if (password != repeatPassword) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароли не совпадают"
            )
        }
        else return ValidationResult(
            successful = true,
        )
    }
}