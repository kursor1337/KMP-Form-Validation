package ru.mobileup.kmm_form_validation.android_sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import ru.mobileup.kmm_form_validation.sharedsample.ui.theme.AppTheme
import ru.mobileup.kmm_form_validation.sharedsample.Core
import ru.mobileup.kmm_form_validation.sharedsample.ui.FormUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormUi(component = Core.createFormComponent(defaultComponentContext()))
                }
            }
        }
    }
}