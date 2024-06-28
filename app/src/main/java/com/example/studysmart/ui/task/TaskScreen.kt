package com.example.studysmart.ui.task

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.components.DeleteDialog
import com.example.studysmart.ui.components.TaskCheckBox
import com.example.studysmart.ui.theme.Red
import com.example.studysmart.util.Priority

@Composable
fun TaskScreen() {

    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }
    taskTitleError = when {
        title.isBlank() -> "Title can't be empty"
        title.length < 4 -> "Title can't be less than 4 characters"
        title.length > 30 -> "Title can't be more than 30 characters"
        else -> null
    }

    var taskDescriptionError by rememberSaveable { mutableStateOf<String?>(null) }
    taskDescriptionError = when {
        description.isBlank() -> "Description can't be empty"
        else -> null
    }

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Task?",
        bodyText = "Are you sure you want to delete this task?",
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = {
            isDeleteDialogOpen = false

        }

    )

    Scaffold(
        topBar = {
            TaskScreenTopBar(
                isTaskExist = true,
                isCompleted = true, // Changed from isComplete to isCompleted
                checkBoxBorderColor = Red,
                onBackButtonClick = { /*TODO*/ },
                onDeleteButtonClick = {isDeleteDialogOpen = true },
                onCheckBoxClick = { /*TODO*/ }
            )
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize()
                .padding(paddingValue)
                .padding(horizontal = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                isError = taskTitleError != null && title.isNotBlank(),
                supportingText = { Text(text = taskTitleError.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
                isError = taskDescriptionError != null && description.isNotBlank(),
                supportingText = { Text(text = taskDescriptionError.orEmpty()) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Due Date",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "28 June, 2024",
                    style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select Due Date"
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Priority")
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Priority.entries.forEach { priority ->
                    PriorityButton(
                        modifier = Modifier.weight(1f),
                        label = priority.title,
                        backgroundColor = priority.color,
                        borderColor = if (priority == Priority.Medium) {
                            Color.White
                        } else Color.Transparent,
                        labelColor = if (priority == Priority.Medium) {
                            Color.White
                        } else Color.White.copy(alpha = 0.7f),
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Related to Subject",
                style = MaterialTheme.typography.titleMedium
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "English", style = MaterialTheme.typography.bodyLarge
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Select Subject"
                    )
                }
            }
            Button(
                enabled = taskTitleError == null,
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(),

            ) {
                Text(text = "Save ")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskScreenTopBar(
    isTaskExist: Boolean,
    isCompleted: Boolean, // Changed from isComplete to isCompleted
    checkBoxBorderColor: Color,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onCheckBoxClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = { Text(text = "Task", style = MaterialTheme.typography.headlineSmall) },
        actions = {
            if (isTaskExist) {
                TaskCheckBox(
                    isCompleted = isCompleted, // Changed from isComplete to isCompleted
                    borderColor = checkBoxBorderColor,
                    onCheckBoxClick = onCheckBoxClick
                )
                IconButton(onClick = onDeleteButtonClick) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete"
                    )
                }
            }
        }
    )
}

@Composable
private fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    borderColor: Color,
    labelColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(5.dp)
            .border(1.dp, borderColor, shape = RoundedCornerShape(5.dp))
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = label, color = labelColor)
    }
}
