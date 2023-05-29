package com.example.parentingapp.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parentingapp.R
import com.example.parentingapp.ViewModelFactory
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.CourseRepository
import com.example.parentingapp.data.Score
import com.example.parentingapp.data.dummyUser
import com.example.parentingapp.ui.components.HomeSection
import com.example.parentingapp.ui.theme.ParentingAppTheme

@Composable
fun ScoreDetailScreen(
    courseTitle: String,
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    viewModel: ScoreDetailViewModel = viewModel(
        factory = ViewModelFactory(
            CourseRepository(),
        ),
    ),
) {
    val course by viewModel.getCourseData(courseTitle).collectAsState()

    Scaffold(topBar = {
        DetailTopBar(actions = { navigateUp() })
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
//                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            Spacer(modifier = Modifier.padding(8.dp))
            StudentData()
            HomeSection(
                title = stringResource(id = R.string.nilai),
                content = {
                    Score(course)
                }
            )
//            Text(
//                text = stringResource(id = R.string.nilai),
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                color = Color.Black,
//                modifier = Modifier
//                    .padding(8.dp)
//                    .align(Alignment.Start)
//            )
        }
    }
}

@Composable
fun StudentData(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(350.dp)
            .height(200.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = colorResource(id = R.color.light_yellow)
    ) {
        Column {
            Text(
                text = stringResource(
                    id = R.string.data_siswa
                ),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                color = colorResource(id = R.color.black)
            )
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = dummyUser.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Color.Black
            )
            Text(
                text = dummyUser.grade,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Black
            )
            Text(
                text = dummyUser.semester,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp),
                color = Color.Black
            )
            Box {
                Box(
                    modifier = Modifier
                        .background(colorResource(id = R.color.white_40opacity))
                        .width(350.dp)
                        .height(30.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .padding(bottom = 8.dp)
                        .align(Alignment.BottomCenter)
                )
                Text(
                    text = dummyUser.status,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 7.dp)
                )
            }

        }
    }
}

@Composable
fun Score(
    course: Course,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(course.score.size) { score ->
            ListScore(score = course.score[score], modifier.padding(8.dp))
        }
    }
}

@Composable
fun ListScore(
    score: Score,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(350.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = colorResource(id = R.color.light_pink)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(colorResource(id = R.color.teal_200))
                            .padding(8.dp)
//                            .align(Alignment.Top)
                    )
                    Text(
                        text = stringResource(id = R.string.lulus),
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 10.sp,
                        color = Color.White
                    )
                }
                Text(
                    text = score.name,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = score.kkm,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 10.sp,
                    color = Color.White
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = score.score,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.End,
                fontSize = 35.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun DetailTopBar(
    actions: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        elevation = 0.dp,
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                actions()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back),
                )
            }
        },
        modifier = modifier
    )
}

@Composable
@Preview(showBackground = true)
fun ScoreDetailPreview() {
    ParentingAppTheme {
        ScoreDetailScreen(courseTitle = "Matematika", navigateUp = { })
    }
}