package com.example.studysmart.ui.subject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.IconButton
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.StrokeCap
import com.example.studysmart.session
import com.example.studysmart.tasks
import com.example.studysmart.ui.components.CountCard
import com.example.studysmart.ui.components.studySessionList
import com.example.studysmart.ui.components.taskList

@Composable
fun SubjectScreen() {
    val listState = rememberLazyListState()
    val isFABExtended by remember {  derivedStateOf { listState.firstVisibleItemIndex == 0 }}


    Scaffold(
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onBackButtonClick = { },
                onDeleteButtonClick = { },
                onEditButtonClick = { }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
            onClick = {/*Todo*/},
            icon = {Icon(imageVector = Icons.Default.Add, contentDescription = "Add")},
            text = {Text(text = "Add Task")},
            expanded =  isFABExtended


            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SubjectOverviewScreen(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    studieHours = "10",
                    goalHours = "15",
                    progress = 0.75f
                )
            }
            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any tasks.\nClick the + button to add new task",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "COMPLETED TASKS",
                emptyListText = "You don't have any tasks.\nClick the check box on completion of task",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
            studySessionList(
                sectionTitle = "Recent Study Session",
                emptyListText = "You don't have any student session.\n " + "Start a study session to begin recording your progress",
                sessions = session,
                onDeleteIconClick = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SubjectScreenTopBar(
    title: String,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    LargeTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        actions = {
            IconButton(onClick = onDeleteButtonClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Subject"
                )
            }
            IconButton(onClick = onEditButtonClick) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Subject"
                )
            }
        }
    )
}

@Composable
private fun SubjectOverviewScreen(
    modifier: Modifier,
    studieHours: String,
    goalHours: String,
    progress: Float
) {
    val percentageProgress = remember(progress) {
        (progress * 100).toInt().coerceIn(0, 100)
    }
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
        Spacer(modifier = Modifier.size(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Study Hours",
            count = studieHours
        )
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                progress = 1f,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            )
            CircularProgressIndicator(
                modifier = Modifier.size(80.dp),
                progress = progress,
                strokeWidth = 4.dp,
                strokeCap = StrokeCap.Round,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = "$percentageProgress%")
        }
    }
}
