# Quick Gallery or Camera

<img width="241" alt="image" src="https://github.com/karun02525/Camera-or-Gallery-Piker-Compose/assets/36824081/af0f7a64-f481-4076-b18f-c692d61206ef">
<img width="241" alt="image" src="https://github.com/karun02525/Camera-or-Gallery-Piker-Compose/assets/36824081/3ca29c03-5f23-4d20-9917-b25bfc9c0b29">
<img width="238" alt="image" src="https://github.com/karun02525/Camera-or-Gallery-Piker-Compose/assets/36824081/c029f6a7-5fd8-45cb-a47a-c8b6f91ea4b0">
<img width="260" alt="image" src="https://github.com/karun02525/Camera-or-Gallery-Piker-Compose/assets/36824081/3bd910e4-46cd-41ae-912e-716c96ab74e6">
<img width="250" alt="image" src="https://github.com/karun02525/Camera-or-Gallery-Piker-Compose/assets/36824081/0c573ab0-ecab-4e7d-9e0d-268321f8858e">

```
 BottomSheetOpenCamera(
                    onSuccess = {uri->
                        if (uri != null) {
                             viewModel.updateProfile(uri)
                        }
                    },
                    onDismiss = {
                        isOpenGallery = false
                    }
                )


repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}

dependencies {
	        implementation 'com.github.karun02525:Camera-or-Gallery-Piker-Compose:1.0.0'
	}






