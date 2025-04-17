package com.example.accenturecodeinterview.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.accenturecodeinterview.presentation.viewmodel.EmployerConfigViewModel

/**
 * Create By Sunil Kumar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmployerDetailScreen(
    navController: NavController,
    employerId: Int,
    viewModel: EmployerConfigViewModel
) {
    val employer =
        viewModel.getEmployerData(
            employerId
        )
    var isBackPressed = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Employer Details") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (!isBackPressed.value) {
                                isBackPressed.value = true
                                navController.popBackStack()
                            }
                        },
                        enabled = !isBackPressed.value
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            Text(
                text = employer.Name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Companion.Bold,
                modifier = Modifier.padding(4.dp),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            // Information Fields
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                EmployerDetailRow(
                    label = "Employer ID",
                    value = employer.EmployerID.toString()
                )
                EmployerDetailRow(
                    label = "Place",
                    value = employer.Place
                )

                EmployerDetailRow(
                    label = "Discount",
                    value = "${employer.DiscountPercentage}%"
                )
            }
        }
    }
}

@Composable
fun EmployerDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Companion.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}