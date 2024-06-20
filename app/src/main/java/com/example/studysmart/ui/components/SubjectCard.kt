package com.example.studysmart.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.studysmart.R

@Composable
fun SubjectCard(
    modifier: Modifier = Modifier,
    subjectName: String,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    Box(
            modifier = modifier
                .size(160.dp)
                .clickable { onClick() }
                .background(
                    brush = Brush.verticalGradient(gradientColors),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.plane),
                    contentDescription = subjectName,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = subjectName,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }

