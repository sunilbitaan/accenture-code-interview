package com.example.accenturecodeinterview.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.accenturecodeinterview.R
import com.example.accenturecodeinterview.domian.EmployerData
import com.example.accenturecodeinterview.presentation.viewmodel.EmployerConfigViewModel


@Composable
fun EmployerListUI(
    navController: NavController,
    viewModel: EmployerConfigViewModel
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val limit = 100
    val text = rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp, start = 4.dp, end = 4.dp, bottom = 0.dp)
    ) {
        SearchField(text, onSearch = {
            viewModel.searchEmployer(text.value, limit)
        })

        when {
            uiState.error?.isNotEmpty() == true -> ShowEmptySearchMessage(
                uiState.error.toString()
            )

            text.value.isEmpty() -> ShowEmptySearchMessage(
                context.getString(R.string.start_typing)
            )

            uiState.employers.isEmpty() -> {
                ShowEmptySearchMessage(context.getString(R.string.no_result))
            }

            else -> LazyColumn {
                items(uiState.employers) { employer ->
                    EmployerCard(navController, employer)
                }
            }
        }
    }
}

@Composable
fun SearchField(
    text: MutableState<String>,
    onSearch: () -> Unit
) {
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            text.value = it
            onSearch()
        },
        label = { Text("Search Employers") },
        leadingIcon = { Icon(Icons.Outlined.Search, contentDescription = "Search Icon") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 5.dp)
    )
}

@Composable
fun ShowEmptySearchMessage(msg: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun EmployerCard(navController: NavController, msg: EmployerData) {
    Column(modifier = Modifier.padding(all = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate("detail/${msg.EmployerID}")
                }
                .background(MaterialTheme.colorScheme.surface)
                .padding(10.dp)
                .clip(RoundedCornerShape(12.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Employer Information
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = msg.Name,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Divider
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
