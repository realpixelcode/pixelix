package com.daniebeler.pixelix.ui.composables.settings.blocked_accounts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.daniebeler.pixelix.R
import com.daniebeler.pixelix.domain.model.Account
import com.daniebeler.pixelix.ui.composables.CustomPullRefreshIndicator
import com.daniebeler.pixelix.ui.composables.states.ErrorComposable
import com.daniebeler.pixelix.utils.Navigate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BlockedAccountsComposable(
    navController: NavController,
    viewModel: BlockedAccountsViewModel = hiltViewModel()
) {

    val pullRefreshState = rememberPullRefreshState(
        refreshing = viewModel.blockedAccounts.isLoading,
        onRefresh = { viewModel.getBlockedAccounts() }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                windowInsets = WindowInsets(0, 0, 0, 0),
                title = {
                    Text(text = stringResource(id = R.string.blocked_accounts))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
                }
            )

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(paddingValues)
        ) {
            if (viewModel.blockedAccounts.blockedAccounts.isEmpty() && !viewModel.blockedAccounts.isLoading && viewModel.blockedAccounts.error.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_blocked_accounts),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                viewModel.blockedAccounts.blockedAccounts.let { blockedAccounts ->
                    LazyColumn {
                        items(blockedAccounts, key = {
                            it.id
                        }) {
                            Row {
                                CustomBlockedAccountRow(
                                    account = it,
                                    navController = navController,
                                    viewModel
                                )
                            }
                        }
                    }
                }
            }
            CustomPullRefreshIndicator(
                viewModel.blockedAccounts.isLoading,
                pullRefreshState,
            )
           // LoadingComposable(isLoading = viewModel.blockedAccounts.isLoading)

            ErrorComposable(message = viewModel.blockedAccounts.error, pullRefreshState)
        }
    }

    if (viewModel.unblockAlert.isNotEmpty()) {
        AlertDialog(
            title = {
                Text(text = stringResource(R.string.unblock_account))
            },
            text = {
                Text(text = stringResource(R.string.confirm_to_unblock_this_account))
            },
            onDismissRequest = {
                viewModel.unblockAlert = ""
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.unblockAccount(viewModel.unblockAlert)
                        viewModel.unblockAlert = ""
                    }
                ) {
                    Text(stringResource(R.string.unblock_caps))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.unblockAlert = ""
                    }
                ) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

@Composable
private fun CustomBlockedAccountRow(
    account: Account,
    navController: NavController,
    viewModel: BlockedAccountsViewModel
) {
    Row(
        Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                Navigate().navigate("profile_screen/" + account.id, navController)
            }
        ) {
            AsyncImage(
                model = account.avatar, contentDescription = "",
                modifier = Modifier
                    .height(32.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Text(text = account.username, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = {
                viewModel.unblockAlert = account.id
            }) {
                Text(text = stringResource(R.string.unblock))
            }
        }

    }

}