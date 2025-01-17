package com.aseemwangoo.handsonkotlin.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aseemwangoo.handsonkotlin.HomeView
import com.aseemwangoo.handsonkotlin.components.addTodo.AddView
import com.aseemwangoo.handsonkotlin.google.GoogleUserModel
import com.aseemwangoo.handsonkotlin.screens.AuthScreen
import com.aseemwangoo.handsonkotlin.ui.theme.HandsOnKotlinTheme
import com.squareup.moshi.Moshi

@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    HandsOnKotlinTheme {
        NavHost(navController = navController, startDestination = Destinations.Auth) {
            composable(Destinations.Auth) { AuthScreen(navController) }
            composable(Destinations.Home) { backStackEntry ->
                val userJson = backStackEntry.arguments?.getString("user")

                val moshi = Moshi.Builder().build()
                val jsonAdapter = moshi.adapter(GoogleUserModel::class.java)
                val userObject = jsonAdapter.fromJson(userJson!!)

                HomeView(navController, userModel = userObject!!)
            }
            composable(Destinations.AddTodo) { AddView(navController) }
        }
    }
}