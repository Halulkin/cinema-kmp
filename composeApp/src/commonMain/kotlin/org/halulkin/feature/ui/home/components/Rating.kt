package org.halulkin.feature.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import cinema_kmp.composeapp.generated.resources.Res
import cinema_kmp.composeapp.generated.resources.lightning
import org.halulkin.designsystem.theme.SystemError
import org.halulkin.designsystem.theme.SystemErrorText
import org.halulkin.designsystem.theme.SystemSuccess
import org.halulkin.designsystem.theme.SystemSuccessText
import org.halulkin.designsystem.theme.SystemWaring
import org.halulkin.designsystem.theme.SystemWaringText
import org.halulkin.core.utils.toOneDecimalString
import org.jetbrains.compose.resources.painterResource

@Composable
fun Rating(
    voteAverage: Double,
    modifier: Modifier = Modifier,
) {
    val (backgroundColor, textColor) = when {
        voteAverage >= 9.0 -> SystemWaring to SystemWaringText
        voteAverage >= 7.0 -> SystemSuccess to SystemSuccessText
        else -> SystemError to SystemErrorText
    }
    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(24))
            .background(backgroundColor)
            .padding(vertical = 2.dp, horizontal = 8.dp)
    ) {
        if (voteAverage >= 9.0) {
            Icon(
                painter = painterResource(resource = Res.drawable.lightning),
                contentDescription = "Lightning",
                tint = textColor,
            )
        }
        Text(
            text = voteAverage.toOneDecimalString(),
            style = MaterialTheme.typography.labelSmall,
            color = textColor,
        )
    }
}
