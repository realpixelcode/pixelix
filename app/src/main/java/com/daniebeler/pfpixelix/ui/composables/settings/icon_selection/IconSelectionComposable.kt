package com.daniebeler.pfpixelix.ui.composables.settings.icon_selection

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.daniebeler.pfpixelix.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconSelectionComposable(
    navController: NavController,
    viewModel: IconSelectionViewModel = hiltViewModel(key = "iconselectionvm")
) {

    val context = LocalContext.current

    val newIconName = remember { mutableStateOf("") }

    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.fillList(context)
    }

    Scaffold(contentWindowInsets = WindowInsets(0), topBar = {
        TopAppBar(windowInsets = WindowInsets(0, 0, 0, 0), title = {
            Text("Icon Selection", fontWeight = FontWeight.Bold)
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = ""
                )
            }
        })

    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            LazyVerticalGrid(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                state = lazyGridState,
                columns = GridCells.Fixed(3)
            ) {

                items(viewModel.icons) {
                    if (it.enabled) {
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                                    shape = CircleShape
                                )
                                .padding(6.dp)
                        ) {
                            Image(
                                it.icon,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier.padding(6.dp)
                        ) {
                            Image(it.icon,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f)
                                    .clickable {
                                        newIconName.value = it.name
                                    })
                        }
                    }

                }
            }


        }

        if (newIconName.value.isNotBlank()) {
            AlertDialog(title = {
                Text(text = stringResource(R.string.change_app_icon))
            }, text = {
                Text(text = stringResource(R.string.change_app_icon_dialog_content))
            }, onDismissRequest = {
                newIconName.value = ""
            }, confirmButton = {
                TextButton(onClick = {
                    viewModel.changeIcon(context = context, newIconName.value)
                }) {
                    Text(stringResource(R.string.change))
                }
            }, dismissButton = {
                TextButton(onClick = {
                    newIconName.value = ""
                }) {
                    Text(stringResource(id = R.string.cancel))
                }
            })
        }

    }
}