package starmage27.apputils.compose
//
//import android.content.res.Configuration
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.background
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.requiredHeight
//import androidx.compose.foundation.layout.requiredWidth
//import androidx.compose.foundation.layout.sizeIn
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Slider
//import androidx.compose.material3.SliderState
//import androidx.compose.material3.Surface
//import androidx.compose.material3.VerticalSlider
//import androidx.compose.material3.rememberSliderState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.derivedStateOf
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OutlinedSlider(
//    state: SliderState,
//    modifier: Modifier = Modifier,
//    vertical: Boolean = false,
//    enabled: Boolean = true,
//    thinness: Dp = 32.dp,
//    length: Dp = Dp.Unspecified,
//    outlineWidth: Dp = 4.dp,
//    outlineColor: Color = MaterialTheme.colorScheme.surface,
//    thumbColor: Color = MaterialTheme.colorScheme.onSurface,
//    trackColor: Color = MaterialTheme.colorScheme.surfaceContainerHighest,
//    activeTrackColor: Color = MaterialTheme.colorScheme.primaryContainer,
//    onValueChange: (Float) -> Unit = {},
//    onValueChangeFinished: () -> Unit = {},
//    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
//    thumb: @Composable (SliderState) -> Unit = @Composable { state ->
//        val padding = animateDpAsState(
//            targetValue = if (state.isDragging) 1.dp else outlineWidth
//        )
//        val ratio = if (!vertical) 1.618034f else 0.618034f
//        Surface(
//            modifier = Modifier
//                .padding(padding.value)
//                .aspectRatio(ratio)
//            ,
//            color = thumbColor,
//            shape = RoundedCornerShape(50)
//        ) { }
//    },
//    track: @Composable (sliderState: SliderState) -> Unit = @Composable { _ ->
//        Box(
//            modifier = Modifier.fillMaxSize(),
//        )
//    },
//) {
//    val fraction by remember { derivedStateOf {
//        (state.value - state.trackRange.start) / (state.trackRange.endInclusive - state.trackRange.start)
//    } }
//
//
//    val minSize = (thinness - outlineWidth) / 2
//    val sidesModifier = if (vertical) {
//        Modifier
//            .requiredHeight(minSize)
//            .fillMaxWidth()
//    } else {
//        Modifier
//            .requiredWidth(minSize)
//            .fillMaxHeight()
//    }
//
//    val middleModifier = if (vertical) {
//        Modifier
//            .padding(vertical = minSize)
//            .fillMaxHeight(fraction)
//            .fillMaxWidth()
//    } else {
//        Modifier
//            .padding(horizontal = minSize)
//            .fillMaxWidth(fraction)
//            .fillMaxHeight()
//    }
//
//    Surface(
//        modifier = modifier.then(
//            if (vertical) {
//                Modifier.sizeIn(0.dp, 0.dp, thinness, length)
//            } else {
//                Modifier.sizeIn(0.dp, 0.dp, length, thinness)
//            }
//        )
////            .size(width, height)
//        ,
//        shape = RoundedCornerShape(50),
//        color = outlineColor
//    ) {
//        Box(
//            modifier = Modifier
//                .padding(outlineWidth)
//            ,
//            contentAlignment = Alignment.BottomStart
//        ) {
//            Box(
//                modifier = Modifier
//                    .clip(RoundedCornerShape(50))
//                    .background(trackColor)
//                    .fillMaxSize()
//                ,
//                contentAlignment = Alignment.BottomStart
//            ) {
//                Box(
//                    modifier = sidesModifier
//                        .background(activeTrackColor)
//                    ,
//                )
//
//                Box(
//                    modifier = middleModifier
//                        .background(activeTrackColor)
//                    ,
//                )
//            }
//        }
//
//        if (!vertical) {
//            Slider(
//                state = state,
//                modifier = Modifier,
//                enabled = enabled,
//                interactionSource = interactionSource,
//                thumb = thumb,
//                track = track,
//                onValueChange = onValueChange,
//                onValueChangeFinished = onValueChangeFinished,
//            )
//        } else {
//            VerticalSlider(
//                state = state,
//                modifier = Modifier,
//                enabled = enabled,
//                interactionSource = interactionSource,
//                thumb = thumb,
//                track = track,
//                onValueChange = onValueChange,
//                onValueChangeFinished = onValueChangeFinished,
//                topToBottom = false
//            )
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//fun OutlinedSliderPreview() {
//    val horizontalState = rememberSliderState()
//    val verticalState = rememberSliderState()
//    Column {
//        OutlinedSlider(
//            horizontalState,
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Row {
//            OutlinedSlider(
//                verticalState,
//                vertical = true
//            )
//        }
//    }
//}