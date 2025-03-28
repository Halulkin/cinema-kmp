package org.halulkin.designsystem.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    onBackButtonClick: (() -> Unit)? = null,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actionButton: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = { title?.let { Text(text = it) } },
        windowInsets = WindowInsets(8.dp, 8.dp, 8.dp, 8.dp),
        colors = TopAppBarDefaults.topAppBarColors().copy(
            scrolledContainerColor = Color.Transparent,
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.White,
        ),
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            onBackButtonClick?.let { onBackClick ->
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "back",
                    )
                }
            }
        },
        actions = {
            actionButton?.let { action ->
                action()
            }
        },
    )
}
