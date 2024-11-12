package com.travela.propertylisting.ui.screen

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.travela.propertylisting.R
import com.travela.propertylisting.coreandroid.util.SingleEventObserver
import com.travela.propertylisting.datamodel.ext.getErrorMessage
import com.travela.propertylisting.ui.screen.views.PropertyMapScreen
import com.travela.propertylisting.ui.theme.TravelaPropertyListingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyListActivity : ComponentActivity() {
    private val viewModel: PropertyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelaPropertyListingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PropertyMapScreen(
                        viewModel,
                        innerPadding,
                        Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                        onNavigationBack = {
                            onBackPressedDispatcher.onBackPressed()
                        },
                        onPermissionDenied = {
                            Toast.makeText(
                                this,
                                getString(R.string.location_permission_issue),
                                Toast.LENGTH_LONG
                            ).show()

                            finish()
                        }
                    )
                }
            }
        }

        viewModel.lvApiIssue.observe(this, SingleEventObserver {
            Toast.makeText(
                this,
                it.getErrorMessage(this),
                Toast.LENGTH_LONG
            ).show()

        })

        viewModel.requestLocation()
    }
}