package ru.mobileup.kmm_form_validation.sharedsample.ui.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import dev.icerock.moko.resources.compose.localized
import kotlinx.coroutines.flow.collectLatest
import ru.mobileup.kmm_form_validation.compose.toCompose

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextField(
    inputControl: ru.mobileup.kmm_form_validation.control.InputControl,
    label: String,
    modifier: Modifier = Modifier
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val hasFocus by inputControl.hasFocus.collectAsState()
    val error by inputControl.error.collectAsState()
    val currentValue by inputControl.text.collectAsState()

    var currentSelection by rememberSaveable(stateSaver = TextRangeSaver) {
        mutableStateOf(TextRange(0))
    }

    var currentComposition by rememberSaveable(stateSaver = NullableTextRangeSaver) {
        mutableStateOf(null)
    }

    val currentTextFieldValue by remember {
        derivedStateOf {
            TextFieldValue(currentValue, currentSelection, currentComposition)
        }
    }

    LaunchedEffect(key1 = inputControl.moveCursorEvent) {
        inputControl.moveCursorEvent.collectLatest {
            currentSelection = TextRange(it)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        val focusRequester = remember { FocusRequester() }

        if (hasFocus) {
            SideEffect {
                focusRequester.requestFocus()
            }
        }

        LaunchedEffect(key1 = inputControl) {
            inputControl.scrollToItEvent.collectLatest {
                bringIntoViewRequester.bringIntoView()
            }
        }

        OutlinedTextField(
            value = currentTextFieldValue,
            keyboardOptions = inputControl.keyboardOptions.toCompose(),
            singleLine = inputControl.singleLine,
            label = { Text(text = label) },
            onValueChange = {
                inputControl.onTextChanged(it.text)
                currentSelection = it.selection
                currentComposition = it.composition
            },
            isError = error != null,
            visualTransformation = inputControl.visualTransformation.toCompose(),
            modifier = modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    inputControl.onFocusChanged(it.isFocused)
                }
        )

        ErrorText(error?.localized() ?: "")
    }
}

private val TextRangeSaver = listSaver(
    save = { listOf(it.start, it.end) },
    restore = { TextRange(it[0], it[1]) }
)

private val NullableTextRangeSaver = listSaver<TextRange?, Int>(
    save = { if (it != null) listOf(it.start, it.end) else emptyList() },
    restore = { TextRange(it[0], it[1]) }
)
