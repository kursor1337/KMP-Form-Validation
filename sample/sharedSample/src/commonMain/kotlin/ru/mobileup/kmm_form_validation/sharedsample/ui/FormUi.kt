package ru.mobileup.kmm_form_validation.sharedsample.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.mobileup.kmm_form_validation.sharedsample.MR
import ru.mobileup.kmm_form_validation.sharedsample.ui.theme.AppTheme
import ru.mobileup.kmm_form_validation.sharedsample.ui.widgets.CheckboxField
import ru.mobileup.kmm_form_validation.sharedsample.ui.widgets.MenuButton
import ru.mobileup.kmm_form_validation.sharedsample.ui.widgets.PasswordTextField
import ru.mobileup.kmm_form_validation.sharedsample.ui.widgets.TextField

@Composable
fun FormUi(
    component: FormComponent,
    modifier: Modifier = Modifier
) {
    val valid by component.valid.collectAsState()
    val submitButtonState by component.submitButtonState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 20.dp, horizontal = 8.dp)
        ) {
            TextField(
                component.nameInput,
                label = stringResource(resource = MR.strings.name_hint),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            TextField(
                component.emailInput,
                label = stringResource(resource = MR.strings.email_hint),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            TextField(
                component.phoneInput,
                label = stringResource(resource = MR.strings.phone_hint),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            PasswordTextField(
                component.passwordInput,
                label = stringResource(resource = MR.strings.password_hint),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            PasswordTextField(
                component.confirmPasswordInput,
                label = stringResource(resource = MR.strings.confirm_password_hint),
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            CheckboxField(
                component.termsCheckBox,
                label = stringResource(resource = MR.strings.terms_hint)
            )

            val color = when (submitButtonState) {
                SubmitButtonState.Valid -> colorResource(MR.colors.green)
                SubmitButtonState.Invalid -> colorResource(MR.colors.red)
            }

            MenuButton(
                text = stringResource(resource = MR.strings.submit_button),
                onClick = component::onSubmitClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        AnimatedVisibility(valid) {
            Text(
                text = stringResource(resource = MR.strings.yahoo),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview
@Composable
fun FormUiPreview() {
    AppTheme {
        FormUi(FakeFormComponent())
    }
}