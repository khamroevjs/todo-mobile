package com.example.todo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val MARGIN_SCROLLBAR: Dp = 0.dp

internal interface ScrollbarAdapter

@Composable
internal fun rememberScrollbarAdapter(scrollState: LazyListState): ScrollbarAdapter =
    object : ScrollbarAdapter {}

@Composable
internal fun VerticalScrollbar(
    modifier: Modifier,
    adapter: ScrollbarAdapter
) {
}

@Composable
internal fun Dialog(
    title: String,
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    androidx.compose.ui.window.Dialog(
        onDismissRequest = onCloseRequest,
    ) {
        Card(elevation = 8.dp) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .height(IntrinsicSize.Min)
            ) {
                ProvideTextStyle(MaterialTheme.typography.subtitle1) {
                    Text(text = title)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(modifier = Modifier.weight(1F)) {
                    content()
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onCloseRequest,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "Done")
                }
            }
        }
    }
}

internal fun Modifier.onKeyUp(key: Key, action: () -> Unit): Modifier =
    onKeyEvent { event ->
        if ((event.type == KeyEventType.KeyUp) && (event.key == key)) {
            action()
            true
        } else {
            false
        }
    }
