package com.cameralibrarycompose

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import camera.library.BottomSheetOpenCamera
import camera.library.ui.noRippleClickable
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.cameralibrarycompose.MainActivity.Companion.photoUri
import com.cameralibrarycompose.ui.theme.CameraLibraryComposeTheme

class MainActivity : ComponentActivity() {

    companion object {
        var photoUri: Uri? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraLibraryComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CameraScreenButton()
                }
            }
        }
    }
}

@Composable
fun CameraScreenButton() {

    var isOpenGallery by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var isEdit by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Blue)
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUri)
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .diskCachePolicy(CachePolicy.ENABLED)
                    .build(),
                placeholder = painterResource(R.drawable.icon_user_white),
                error = painterResource(R.drawable.icon_user_white),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(196.dp)
                    .clip(CircleShape)
            )


            BackgroundCircle(
                size = 60.dp,
                icon = R.drawable.icon_camera,
                modifier = Modifier
                    .noRippleClickable {
                        isOpenGallery = true
                    }
                    .padding(top = 40.dp, end = 2.dp)

            )

            if (isOpenGallery) {
                BottomSheetOpenCamera(
                    isShowLabel = true,
                    onSuccess = {
                        if (it != null) {
                            photoUri = it
                            // viewModel.updateProfile(context, it)
                        }
                        isOpenGallery = false
                        selectedImageUri = it
                    },
                    onDismiss = {
                        isOpenGallery = false
                    }
                )
            }


        }

        Text(
            text = "Gallery or Camera Library",
            fontSize = 30.sp,
            color = Color.White

        )
        Text(
            text = "\uD83D\uDCF7 or \uD83D\uDDBC️",
            fontSize = 40.sp,
            color = Color.White


        )

    }
}

