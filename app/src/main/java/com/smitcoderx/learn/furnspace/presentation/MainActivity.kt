package com.smitcoderx.learn.furnspace.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.smitcoderx.learn.furnspace.presentation.root.RootView
import com.smitcoderx.learn.furnspace.presentation.theme.FurnSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FurnSpaceTheme { RootView() } }
    }
}
