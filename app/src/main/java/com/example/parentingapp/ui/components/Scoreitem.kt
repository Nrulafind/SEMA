package com.example.parentingapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parentingapp.R
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.dummyCourseMenu

@Composable
fun ScoreItem(
    course: Course,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.size(140.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box {
            Image(
                painter = painterResource(course.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Box(modifier = Modifier
                .background(colorResource(id = R.color.white_40opacity))
                .width(200.dp)
                .height(40.dp)
                .clip(RoundedCornerShape(8.dp))
                .align(Alignment.BottomCenter))
            Text(
                text = course.title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 6.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScoreItemPreview() {
    MaterialTheme {
        ScoreItem(
            course = Course(R.drawable.matematika, "Matematika", emptyList()),
            modifier = Modifier.padding(8.dp)
        )
    }
}