package camera.library

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import camera.library.ui.BackgroundCircle
import camera.library.ui.noRippleClickable


@Preview(showSystemUi = false)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetOpenCamera(
    isShowLabel: Boolean = false,
    onSuccess: (Uri?) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    var isOpen by remember { mutableStateOf("") }
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center

        ) {
            if (isShowLabel) {
                Text(
                    text = stringResource(R.string.profile_photo),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontSize = 19.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 20.dp)
                )
            }
            Row {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment=Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 40.dp, bottom = 60.dp)
                )
                {

                    BackgroundCircle(
                        size = 50.dp,
                        icon = R.drawable.icon_camera_purpule,
                        color =  Color(0xFFEDE7F6),
                        modifier = Modifier
                            .noRippleClickable {
                                isOpen = CAMERA
                            }

                    )

                    Text(
                        text = stringResource(R.string.camera),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment=Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(start = 25.dp)
                ) {
                    BackgroundCircle(
                        size = 50.dp,
                        icon = R.drawable.icon_photo,
                        color = Color(0xFFE1F5FE),
                        modifier = Modifier
                            .noRippleClickable {
                                isOpen = GALLERY
                            }
                    )
                    Text(
                        text = stringResource(R.string.gallery),
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        fontSize = 13.sp,
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }

            }
        }
        if (isOpen != "") {
            CameraGalleryOperation(isOpen) {
                onSuccess(it)
            }
        }
    }
}

