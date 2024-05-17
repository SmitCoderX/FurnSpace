package com.smitcoderx.learn.furnspace

import android.credentials.GetCredentialException
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.smitcoderx.learn.furnspace.ui.theme.FurnSpaceTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val credentialManager = CredentialManager.create(this)

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FurnSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Button(onClick = { handleIdOption() }) {
                        Text(text = "Testing")
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun handleIdOption() {
        val googleSignInOption = GetSignInWithGoogleOption.Builder(BuildConfig.webClientId)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleSignInOption)
            .build()

        GlobalScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = this@MainActivity
                )
                handleSignIn(result)
            } catch (e: GetCredentialException) {
                Log.d("TAG", "handleIdOption: ${e.message}")
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse) {
        when (val credentials = result.credential) {
            is CustomCredential -> {
                if (credentials.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdToken = GoogleIdTokenCredential.createFrom(
                            credentials.data
                        )
                        Log.d("TAG", "Data: ${googleIdToken.data}")
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.d("TAG", "handleSignIn: ${e.message}")
                    }
                }
            }

            else -> {
                Log.e("TAG", "Unexpected type of credential")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FurnSpaceTheme {
        Greeting("Android")
    }
}