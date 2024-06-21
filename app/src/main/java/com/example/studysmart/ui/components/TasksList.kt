package com.example.studysmart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.domain.model.Task
import com.example.studysmart.util.Priority

fun LazyListScope.taskList(
    sectionTitle: String,
    tasks: List<Task>,
    emptyListText: String,
    onTaskCardClick:(Int) -> Unit,
    onCheckBoxClick: (Task) -> Unit
) {
    item {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(12.dp)
        )
    }
    if (tasks.isEmpty()) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Align children horizontally center
            ) {
                Image(
                    modifier = Modifier
                        .size(140.dp)
                        .align(Alignment.CenterHorizontally), // Align image center horizontally
                    painter = painterResource(id = R.drawable.task),
                    contentDescription = emptyListText,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = emptyListText,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally) // Align text center horizontally
                )
            }
        }
    } else {
        items(tasks) { task ->
            TaskCard(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                task = task,
                onCheckBoxClick = {},
                onClick = {onTaskCardClick(task.taskId)}
            )
        }
    }
}

@Composable
private fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onCheckBoxClick: (Boolean) -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            TaskCheckBox(
                isCompleted = task.isComplete,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckBoxClick = { onCheckBoxClick(task.isComplete) }
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    textDecoration = if (task.isComplete) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${task.dueDate}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}
