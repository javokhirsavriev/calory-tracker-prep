package uz.javokhirdev.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.javokhirdev.calorytracker.core.navigation.Route
import uz.javokhirdev.calorytracker.navigation.navigate
import uz.javokhirdev.calorytracker.onboarding.presentation.activity.ActivityScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.age.AgeScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.gender.GenderScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.goal.GoalScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.height.HeightScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.nutrientgoal.NutrientGoalScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.weight.WeightScreen
import uz.javokhirdev.calorytracker.onboarding.presentation.welcome.WelcomeScreen
import uz.javokhirdev.calorytracker.tracker.presentation.overview.TrackerOverviewScreen
import uz.javokhirdev.calorytracker.ui.theme.CaloryTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.GOAL) {
                            GoalScreen(onNavigate = navController::navigate)
                        }

                        composable(Route.TRACKER_OVERVIEW) {
                            TrackerOverviewScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.SEARCH) {

                        }
                    }
                }
            }
        }
    }
}