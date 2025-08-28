package es.masorange.livebox.sdk.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

internal typealias CheckedState = MutableState<Boolean>

/**
 * Implementation of a basic Switch with leading label in Compose.
 */
@Composable
internal fun SwitchLabel(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    label: String,
    textStyle: TextStyle = TextStyle.Default,
    colors: SwitchColors = SwitchDefaults.colors(),
    checked: Boolean = false,
    checkedState: CheckedState = remember { mutableStateOf(checked) },
    onCheckedChange: ((Boolean) -> Unit)? = null
) {
    Row(modifier = modifier, verticalAlignment = verticalAlignment) {
        Text(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    checkedState.value = !checkedState.value
                    onCheckedChange?.invoke(checkedState.value)
                },
            text = label,
            style = textStyle
        )

        Spacer(modifier = Modifier.width(16.dp))

        Switch(
            checked = checkedState.value,
            colors = colors,
            onCheckedChange = {
                onCheckedChange?.invoke(it)
                checkedState.value = it
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun SwitchLabelPreview() {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        SwitchLabel(
            label = "Switch Label enabled",
            checked = true
        )
        SwitchLabel(
            label = "Switch Label disabled",
            checked = false
        )
    }
}
