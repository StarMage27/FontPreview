package starmage27.apputils.compose

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun NumberField(
    modifier: Modifier = Modifier,
    value: String = "",
    placeholder: String = "1",
    label: String = "",
    isError: Boolean = false,
    suffix: @Composable (() -> Unit)? = null,
    onValueChange: (String) -> Unit = { _ -> },
    // language=RegExp
    regexPattern: String = "^\\d+$",
) {
    val pattern: Regex = remember { Regex(regexPattern) }
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { newValue ->
            if (newValue.matches(pattern) || newValue.isEmpty()) {
                onValueChange(newValue)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        },
        isError = isError,
        suffix = suffix,
        singleLine = true
    )
}