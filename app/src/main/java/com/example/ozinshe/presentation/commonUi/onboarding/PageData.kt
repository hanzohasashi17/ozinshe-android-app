package com.example.ozinshe.presentation.commonUi.onboarding

import androidx.annotation.DrawableRes
import com.example.ozinshe.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onboardingPages = listOf(
    Page(
        title = "ÖZINŞE-ге қош келдің!",
        description = "Фильмдер, телехикаялар, ситкомдар, анимациялық жобалар, телебағдарламалар мен реалити-шоулар, аниме және тағы басқалары",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "ÖZINŞE-ге қош келдің!",
        description = "Кез келген құрылғыдан қара \nСүйікті фильміңді  қосымша төлемсіз телефоннан, планшеттен, ноутбуктан қара",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "ÖZINŞE-ге қош келдің!",
        description = "Тіркелу оңай. Қазір тіркел де қалаған фильміңе қол жеткіз",
        image = R.drawable.onboarding3
    ),
)