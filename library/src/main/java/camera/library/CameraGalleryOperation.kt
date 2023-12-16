package camera.library

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import camera.library.utils.ComposeFileProvider
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageContractOptions
import com.canhub.cropper.CropImageOptions

@Composable
fun CameraGalleryOperation(isOpen: String, content: (Uri?) -> Unit) {
    val context = LocalContext.current
    var captureUri by remember { mutableStateOf<Uri?>(null) }
    val imageCropLauncher = rememberLauncherForActivityResult(
        contract = CropImageContract()
    ) { result ->
        if (result.isSuccessful) {
            content(result.uriContent)
        } else {
            content(null)
        }
    }
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            val cropOptions = CropImageContractOptions(captureUri, CropImageOptions())
            imageCropLauncher.launch(cropOptions)
        }
    )

    val imagePickerLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            val cropOptions = CropImageContractOptions(uri, CropImageOptions())
            imageCropLauncher.launch(cropOptions)
        }

    LaunchedEffect(Unit) {
        if (isOpen == CAMERA) {
            val uri = ComposeFileProvider.getImageUri(context)
            captureUri = uri
            cameraLauncher.launch(uri)
        } else if (isOpen == GALLERY) {
            imagePickerLauncher.launch("image/*")
        }
    }
}
const val CAMERA="camera"
const val GALLERY="gallery"
