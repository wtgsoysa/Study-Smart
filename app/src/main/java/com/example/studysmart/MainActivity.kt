package com.example.studysmart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.studysmart.domain.model.Session
import com.example.studysmart.domain.model.Subject
import com.example.studysmart.domain.model.Task
import com.example.studysmart.ui.dashboard.DashboardScreen
import com.example.studysmart.ui.subject.SubjectScreen
import com.example.studysmart.ui.task.TaskScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskScreen()
        }
    }
}

val subjects = listOf(
    Subject(name = "English", goalHours = 10f, colors = Subject.subjectCardColors[0], subjectId = 0),
    Subject(name = "Physics", goalHours = 10f, colors = Subject.subjectCardColors[1],subjectId = 0),
    Subject(name = "Maths", goalHours = 10f, colors = Subject.subjectCardColors[2],subjectId = 0),
    Subject(name = "ICT", goalHours = 10f, colors = Subject.subjectCardColors[3],subjectId = 0),
    Subject(name = "BIO", goalHours = 10f, colors = Subject.subjectCardColors[4],subjectId = 0),
)

val tasks = listOf(
    Task(
        title = "Solve five math problems quickly",
        description = "",
        dueDate = 0L,
        priority = 0,
        relatedToSubject = "",
        isComplete = false,
        taskSubjectId = 0,
        taskId =  1
    ),
    Task(
        title = "Create a simple science experiment.",
        description = "",
        dueDate = 0L,
        priority = 1,
        relatedToSubject = "",
        isComplete = false,
        taskSubjectId = 0,
        taskId =  1
    ),
    Task(
        title = "Describe your favorite book character.",
        description = "",
        dueDate = 0L,
        priority = 2,
        relatedToSubject = "",
        isComplete = false,
        taskSubjectId = 0,
        taskId =  1
    ),
    Task(
        title = "Research a famous historical event.",
        description = "",
        dueDate = 0L,
        priority = 1,
        relatedToSubject = "",
        isComplete = false,
        taskSubjectId = 0,
        taskId =  1
    ),
    Task(
        title = "Summarize today's main news headlines.",
        description = "",
        dueDate = 0L,
        priority = 0,
        relatedToSubject = "",
        isComplete = true,
        taskSubjectId = 0,
        taskId =  1
    )
)

val session = listOf(
    Session(
        relatedToSubject = "English",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Physics",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Maths",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "ICT",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "BIO",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
)

