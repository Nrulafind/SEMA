package com.example.parentingapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parentingapp.R
import com.example.parentingapp.ScoreActivity
import com.example.parentingapp.ScorePage
import com.example.parentingapp.data.Course
import com.example.parentingapp.data.dummyCourseMenu
import com.example.parentingapp.databinding.FragmentScoreBinding
import com.example.parentingapp.ui.components.BottomBaritem
import com.example.parentingapp.ui.components.HomeSection
import com.example.parentingapp.ui.components.ScoreItem
import com.example.parentingapp.ui.theme.ParentingAppTheme


class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ParentingAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ScorePage()
                    }
                }
            }
        }
    }

    @Composable
    fun ScorePage(modifier: Modifier = Modifier) {
        Scaffold(bottomBar = { BottomBar() }
        ) { innerPadding ->
            Column(
                modifier = modifier
                    .padding(innerPadding)
            ) {
                HomeSection(
                    title = stringResource(id = R.string.list_course),
                    content = { CourseMenu(dummyCourseMenu) }
                )
            }
        }
    }

    @Composable
    fun BottomBar(
        modifier: Modifier = Modifier,
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary,
            modifier = modifier
        ) {
            val navigationItems = listOf(
                BottomBaritem(
                    title = stringResource(id = R.string.home),
                    icon = Icons.Default.Home
                ),
                BottomBaritem(
                    title = stringResource(id = R.string.score),
                    icon = Icons.Default.List
                ),
                BottomBaritem(
                    title = stringResource(id = R.string.absent),
                    icon = Icons.Default.List
                ),
                BottomBaritem(
                    title = stringResource(id = R.string.profile),
                    icon = Icons.Default.List
                )
            )
            navigationItems.map {
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.title)
                    },
                    label = {
                        Text(text = it.title)
                    },
                    selected = it.title == navigationItems[1].title,
                    unselectedContentColor = Color.LightGray,
                    onClick = {}
                )
            }
        }
    }

    @Composable
    fun CourseMenu(
        listCourse: List<Course>,
        modifier: Modifier = Modifier,
    ) {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            content = {
                items(listCourse.size) {
                    ScoreItem(course = listCourse[it], modifier.padding(8.dp))
                }
            })
    }

    @Composable
    @Preview(showBackground = true, device = Devices.PIXEL_4)
    fun ScoreActivityPreview(){
        ParentingAppTheme {
            ScoreActivity()
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}