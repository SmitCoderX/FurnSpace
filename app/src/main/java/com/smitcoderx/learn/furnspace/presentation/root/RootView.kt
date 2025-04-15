package com.smitcoderx.learn.furnspace.presentation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.smitcoderx.learn.furnspace.presentation.navigation.RootNavigation

@Composable
fun RootView() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        RootNavigation()
    }
}
