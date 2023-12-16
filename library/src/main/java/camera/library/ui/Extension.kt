package camera.library.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundCircle(
    size: Dp = 30.dp,
    iconSize: Dp = size / 2,
    modifier: Modifier,
    @DrawableRes icon: Int,
    color: Color = White,
    contentAlignment: Alignment = Alignment.Center
) {
    val shape = CircleShape
    Box(
        modifier = modifier
            .size(size)
            .shadow(
                elevation = 2.dp,
                shape = shape
            )
            .background(color, shape = shape),
        contentAlignment = contentAlignment
    )
    {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(iconSize)
        )
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}