package com.example.finalproject_pam.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalproject_pam.R
import com.example.finalproject_pam.ui.screen.AddScreen
import com.example.finalproject_pam.ui.screen.DestinasiAdd

import com.example.finalproject_pam.ui.screen.HomeScreen
import com.example.finalproject_pam.ui.screen.LoginScreen
import com.example.finalproject_pam.ui.screen.SignUpScreen


@Composable
fun AnimalsApp(navController: NavHostController = rememberNavController()){
    PetaNavigasi(navController = navController)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HewanTopAppbar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
){

    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(
                        id = R.string.back
                    )
                    )
                }
            }
        }
    )
}

@Composable
fun PetaNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = Modifier
        ){
        
        composable("login"){
            LoginScreen(
                navigateToHome = {navController.navigate("home")},
                navigateToSignUp = {navController.navigate("signup")}
                )
        }

        composable("signup"){
            SignUpScreen (
                navigateToLogin = {navController.popBackStack()}
            )
        }
        
        composable("home"){
            HomeScreen(navigateToItemEntry = {navController.navigate(DestinasiAdd.route)})
        }
        
        composable(DestinasiAdd.route){
            AddScreen(navigateBack = { navController.popBackStack()})
        }
    }

}