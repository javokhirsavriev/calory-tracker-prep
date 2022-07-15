package uz.javokhirdev.calorytracker.onboarding.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.javokhirdev.calorytracker.onboarding.domain.usecase.ValidateNutrients

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingDomainModule {

    @[Provides ViewModelScoped]
    fun provideValidateNutrientsUseCase(): ValidateNutrients {
        return ValidateNutrients()
    }
}