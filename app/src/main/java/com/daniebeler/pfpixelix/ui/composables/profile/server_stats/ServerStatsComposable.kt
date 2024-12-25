package com.daniebeler.pfpixelix.ui.composables.profile.server_stats

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DomainSoftwareComposable(domain: String, viewModel: ServerStatsViewModel = hiltViewModel(key = "serverstats$domain")) {


    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(Unit) {
        viewModel.getData(domain)
    }

    if (viewModel.statsState.fediSoftware?.icon != null) {
        Image(painterResource(id = viewModel.statsState.fediSoftware!!.icon!!),
            contentDescription = "",
            modifier = Modifier
                .height(24.dp)
                .clickable { showBottomSheet = true })
    }


    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            }, sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            ) {
                if (viewModel.statsState.fediSoftware != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
//                        Image(
//                            painterResource(id = domainSoftware.icon),
//                            contentDescription = null,
//                            modifier = Modifier.height(56.dp)
//                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = viewModel.statsState.fediSoftware!!.name, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    }

                    if (viewModel.statsState.fediSoftware!!.description.isNotBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = viewModel.statsState.fediSoftware!!.description)
                    }


                    Spacer(modifier = Modifier.height(12.dp))

//                    TextButton (
//                        onClick = { viewModel.openUrl(viewModel.statsState.fediSoftware.link) },
//                        shape = RoundedCornerShape(12.dp),
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        Text(text = "Visit " + domainSoftware.link)
//                    }
                }


//                Spacer(modifier = Modifier.height(12.dp))
//
//                if (domainSoftware.postsCount != -1 || domainSoftware.totalUserCount != -1 || domainSoftware.activeUserCount != -1) {
//                    HorizontalDivider(Modifier.padding(vertical = 12.dp))
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    Text(
//                        text = domainSoftware.domain,
//                        fontSize = 32.sp,
//                        textAlign = TextAlign.Center,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier.fillMaxWidth()
//                    )
//
//                    if (domainSoftware.nodeDescription.isNotBlank()) {
//                        Spacer(modifier = Modifier.height(12.dp))
//                        Text(domainSoftware.nodeDescription)
//                        Spacer(modifier = Modifier.height(12.dp))
//                    }
//
//                    if (domainSoftware.postsCount != -1) {
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row {
//                            Text("Total posts:")
//                            Spacer(Modifier.width(8.dp))
//                            Text(
//                                text = String.format(
//                                    Locale.GERMANY, "%,d", domainSoftware.postsCount
//                                ), fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//
//                    if (domainSoftware.totalUserCount != -1) {
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row {
//                            Text("Total users:")
//                            Spacer(Modifier.width(8.dp))
//                            Text(
//                                text = String.format(
//                                    Locale.GERMANY, "%,d", domainSoftware.totalUserCount
//                                ), fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//
//                    if (domainSoftware.activeUserCount != -1) {
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row {
//                            Text("Active users:")
//                            Spacer(Modifier.width(8.dp))
//                            Text(
//                                text = String.format(
//                                    Locale.GERMANY, "%,d", domainSoftware.activeUserCount
//                                ), fontWeight = FontWeight.Bold
//                            )
//                        }
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//
//                    TextButton (
//                        onClick = { viewModel.openUrl("https://" + domainSoftware.domain) },
//                        shape = RoundedCornerShape(12.dp),
//                        modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        Text(text = "Visit https://" + domainSoftware.domain)
//                    }
//
//                    Spacer(modifier = Modifier.height(12.dp))
//                }
            }
        }
    }
}