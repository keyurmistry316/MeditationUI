package com.example.meditationui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditationui.ui.theme.AquaBlue
import com.example.meditationui.ui.theme.Beige1
import com.example.meditationui.ui.theme.Beige2
import com.example.meditationui.ui.theme.Beige3
import com.example.meditationui.ui.theme.BlueViolet1
import com.example.meditationui.ui.theme.BlueViolet2
import com.example.meditationui.ui.theme.BlueViolet3
import com.example.meditationui.ui.theme.ButtonBlue
import com.example.meditationui.ui.theme.DarkerButtonBlue
import com.example.meditationui.ui.theme.DeepBlue
import com.example.meditationui.ui.theme.LightGreen1
import com.example.meditationui.ui.theme.LightGreen2
import com.example.meditationui.ui.theme.LightGreen3
import com.example.meditationui.ui.theme.LightRed
import com.example.meditationui.ui.theme.OrangeYellow1
import com.example.meditationui.ui.theme.OrangeYellow2
import com.example.meditationui.ui.theme.OrangeYellow3
import com.example.meditationui.ui.theme.TextWhite

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
    ) {

        Column {
            GreetingSection("")
            ChipsSection(
                listOf(
                    "Sweet Sleep",
                    "Insomnia",
                    "Depression",
                    "Imperative",
                    "Anxiety",
                    "Cheerful"
                )
            )
            DailyThoughtSection()
            FeatureSection(
                featureList = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3,
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3,
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3,
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3,
                    ),
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_home),
                BottomMenuContent("Meditate", R.drawable.ic_bubble),
                BottomMenuContent("Sleep", R.drawable.ic_moon),
                BottomMenuContent("Music", R.drawable.ic_music),
                BottomMenuContent("Profile", R.drawable.ic_profile),
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }

}

@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {

    val selectedItemIndex = remember { mutableIntStateOf(initialSelectedItemIndex) }
    Row(
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp),
    ) {
        items.forEachIndexed { index, bottomMenuContent ->

            BottomMenuItem(
                bottomMenuContent = bottomMenuContent,
                isSelected = index == selectedItemIndex.value,
                activeColor = activeColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor

            ) {
                selectedItemIndex.value = index
            }
        }

    }

}

@Composable
fun BottomMenuItem(
    bottomMenuContent: BottomMenuContent,
    isSelected: Boolean = false,
    activeColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    onClick: () -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(if (isSelected) activeColor else Color.Transparent)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = bottomMenuContent.iconId),
                contentDescription = bottomMenuContent.title,
                tint = if (isSelected) activeTextColor else inactiveTextColor,
                modifier = Modifier.size(20.dp)
            )
        }
        Text(
            text = bottomMenuContent.title,
            color = if (isSelected) activeTextColor else inactiveTextColor
        )
    }

}


@Composable
fun GreetingSection(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Column {
            Text(
                text = "Good Morning,$name",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "We wish you have a good day!",
                color = TextWhite,
                style = MaterialTheme.typography.titleSmall,
            )

        }
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "Search",
            colorFilter = ColorFilter.tint(Color.White)

        )
    }
}

@Composable
fun ChipsSection(chipsList: List<String>) {

    val selectedChipIndex = remember { mutableIntStateOf(0) }

    LazyRow {
        items(chipsList.size) {
            Box(modifier = Modifier
                .padding(start = 10.dp, top = 15.dp, bottom = 15.dp)
                .clickable { selectedChipIndex.value = it }
                .clip(RoundedCornerShape(8.dp))
                .background(
                    if (selectedChipIndex.value == it) {
                        ButtonBlue
                    } else {
                        DarkerButtonBlue
                    }
                )
                .padding(15.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = chipsList[it], color = TextWhite)
            }


        }
    }
}

@Composable
fun DailyThoughtSection() {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(LightRed)
            .padding(15.dp, 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Daily Thought",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = "Meditation 3-10 min",
                color = TextWhite,
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_play),
                contentDescription = ""
            )
        }


    }
}

@Composable
fun FeatureSection(featureList: List<Feature>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = "Features",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,

            )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(featureList.size) {
                FeatureItem(featureList[it])
            }
        }
    }

}

@Composable
fun FeatureItem(feature: Feature) {
    BoxWithConstraints(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(10.dp)
            .clip(
                RoundedCornerShape(10.dp)
            )
            .background(feature.darkColor)
    ) {

        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //Medium color path
        val mediumColorPoint1 = Offset(0f, height * 0.3f)
        val mediumColorPoint2 = Offset(width * 0.1f, height * 0.35f)
        val mediumColorPoint3 = Offset(width * 0.4f, height * 0.05f)
        val mediumColorPoint4 = Offset(width * 75f, height * 0.7f)
        val mediumColorPoint5 = Offset(width * 1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColorPoint1.x, mediumColorPoint1.y)
            standerQuardTo(mediumColorPoint1, mediumColorPoint2)
            standerQuardTo(mediumColorPoint2, mediumColorPoint3)
            standerQuardTo(mediumColorPoint3, mediumColorPoint4)
            standerQuardTo(mediumColorPoint4, mediumColorPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        // Light colored path
        val lightPoint1 = Offset(0f, height * 0.35f)
        val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
        val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
        val lightPoint4 = Offset(width * 0.65f, height.toFloat())
        val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

        val lightColoredPath = Path().apply {
            moveTo(lightPoint1.x, lightPoint1.y)
            standerQuardTo(lightPoint1, lightPoint2)
            standerQuardTo(lightPoint2, lightPoint3)
            standerQuardTo(lightPoint3, lightPoint4)
            standerQuardTo(lightPoint4, lightPoint5)
            lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
            lineTo(-100f, height.toFloat() + 100f)
            close()
        }

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath, color = feature.mediumColor)
            drawPath(path = lightColoredPath, color = feature.lightColor)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        )
        Text(
            text = feature.title,
            fontSize = 20.sp,
            color = TextWhite,
            fontWeight = FontWeight.Bold,
            lineHeight = 26.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp)
        )
        Icon(
            painter = painterResource(id = feature.iconId),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)

        )
        Text(
            text = "Start",
            fontSize = 14.sp,
            color = TextWhite,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clickable { }
                .padding(end = 10.dp, bottom = 5.dp)
                .align(Alignment.BottomEnd)
                .clip(RoundedCornerShape(10.dp))
                .background(ButtonBlue)
                .padding(15.dp, 6.dp)
        )


    }
}
