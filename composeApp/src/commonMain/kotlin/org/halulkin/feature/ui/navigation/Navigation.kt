package org.halulkin.feature.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.halulkin.feature.ui.details.MovieDetailsRoute
import org.halulkin.feature.ui.favorite.FavoriteRoute
import org.halulkin.feature.ui.home.HomeRoute
import org.halulkin.feature.ui.movielist.MovieListRoute
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController) },
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favorite,
    )
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.title)
                    )
                },

                label = {
                    Text(text = stringResource(item.title))
                },
                alwaysShowLabel = true,
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen ->
                            popUpTo(screen) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route,
        modifier = modifier,
    ) {
        composable(route = BottomNavItem.Home.route) {
            HomeRoute(
                onMovieClick = { movieId ->
                    navController.navigate(NavItem.MovieDetails(movieId))
                },
                onMovieListClick = { moviesType ->
                    navController.navigate(NavItem.MovieList(moviesType.name))
                }
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteRoute(
                onMovieClick = { movieId ->
                    navController.navigate(NavItem.MovieDetails(movieId))
                }
            )
        }
        composable<NavItem.MovieDetails> {
            MovieDetailsRoute(
                onBackPress = { navController.popBackStack() }
            )
        }

        composable<NavItem.MovieList> {
            MovieListRoute(
                onMovieClick = { movieId ->
                    navController.navigate(NavItem.MovieDetails(movieId))
                },
            )
        }
    }
}
