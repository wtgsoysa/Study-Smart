package com.example.studysmart.ui.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.session
import com.example.studysmart.subjects
import com.example.studysmart.tasks
import com.example.studysmart.ui.components.AddSubjectDialog
import com.example.studysmart.ui.components.CountCard
import com.example.studysmart.ui.components.DeleteDialog
import com.example.studysmart.ui.components.SubjectCard
import com.example.studysmart.ui.components.studySessionList
import com.example.studysmart.ui.components.taskList
import java.time.LocalDate

@Composable
fun DashboardScreen() {


    var isAddSubjectDialogOpen by rememberSaveable {mutableStateOf(false)}
    var isDeleteSessionDialogOpen by rememberSaveable {mutableStateOf(false)}
    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var subjectName by remember {mutableStateOf("")}
    var goalHours by remember {mutableStateOf("")}
    var selectedColor by remember {mutableStateOf(Subject.subjectCardColors.random())}

    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange ={subjectName = it} ,
        onGoalHoursChange = {goalHours = it},
        selectedColor = selectedColor ,
        onColorChange = {selectedColor = it} ,
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = {
            isAddSubjectDialogOpen = false
        }

    )

    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete Session?",
        bodyText = "Are you sure you want to delete this session? Your studied hours will be reduced \n" +"By this session time. This action can not be under",
        onDismissRequest = {isDeleteSessionDialogOpen = false },
        onConfirmButtonClick = { isDeleteSessionDialogOpen = false }
    )







    Scaffold(
        topBar = {
            DashboardScreenTopBar()
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    subjectCount = subjects.size,
                    studiedHours = "10",
                    goalHours = "20"
                )
            }
            item {
                SubjectCardsSection(
                    modifier = Modifier.fillMaxSize(),
                    subjectList = subjects,
                    onAddIconClicked = {
                        isAddSubjectDialogOpen = true
                    }
                )
            }
            item {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp)
                ) {
                    Text(text = "Start Study Session")
                }
            }
            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListText = "You don't have any tasks.\nClick the + button in subject screen to add new task",
                tasks = tasks,
                onCheckBoxClick = {},
                onTaskCardClick = {}
            )
            studySessionList(
                sectionTitle = "Recent Study Session",
                emptyListText = "You don't have any student session.\n " + "Start a study session to begin recording your progress",
                sessions = session,
                onDeleteIconClick = {isDeleteSessionDialogOpen = true}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DashboardScreenTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "StudySmart",
                style = MaterialTheme.typography.headlineMedium,
            )
        }
    )
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
) {
    Row(modifier = modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Count",
            count = "$subjectCount"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Subject Hours",
            count = studiedHours
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = goalHours
        )
    }
}

@Composable
private fun SubjectCardsSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subject.\nClick the + button to add new subject.",
    onAddIconClicked: () -> Unit
){
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Subjects",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(12.dp)
            )
            IconButton(onClick = onAddIconClicked) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Subject"
                )
            }
        }

        if (subjectList.isEmpty()) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.plane),
                contentDescription = emptyListText,
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = emptyListText,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
            ) {
                items(subjectList) { subject ->
                    SubjectCard(
                        modifier = Modifier,
                        subjectName = subject.name,
                        gradientColors = subject.colors,
                        onClick = {}
                    )
                }
            }
        }
    }
}
