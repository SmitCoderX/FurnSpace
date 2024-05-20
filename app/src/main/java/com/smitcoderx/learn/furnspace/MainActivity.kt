package com.smitcoderx.learn.furnspace

import android.credentials.GetCredentialException
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.smitcoderx.learn.furnspace.ui.theme.FurnSpaceTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {

    val credentialManager = CredentialManager.create(this)
    private lateinit var auth: FirebaseAuth

    private var user1 = mutableStateOf("No User")


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        user1.value = auth.currentUser?.displayName ?: "No User"



        setContent {
            FurnSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(text = user1.value, color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        AnimatedVisibility(visible = auth.currentUser == null) {
                            Button(
                                onClick = { handleIdOption() },
                                Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                            ) {
                                Text(text = "Login")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = { handleSignOut() },
                            Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                        ) {
                            Text(text = "Sign Out")
                        }
                    }
                }
            }
        }
    }

    private fun handleSignOut() {
        user1.value = "No User"
        auth.signOut()
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    private fun handleIdOption() {

        val googleSignInOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(true)
            .setServerClientId(this.getString(R.string.webClientId))
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

    private suspend fun handleSignIn(result: GetCredentialResponse) {
        when (val credentials = result.credential) {
            is CustomCredential -> {
                if (credentials.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdToken = GoogleIdTokenCredential.createFrom(
                            credentials.data
                        )
                        val googleCredentials =
                            GoogleAuthProvider.getCredential(googleIdToken.idToken, null)
                        try {
                            val user = auth.signInWithCredential(googleCredentials).await()
                            user1.value = user.user?.displayName.toString()
                        } catch (e: Exception) {
                            user1.value = e.message.toString()
                            e.printStackTrace()
                        }
                        Log.d("TAG", "Data: ${googleIdToken.data}")
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.d("TAG", "handleSignIn: ${e.message}")
                    } catch (e: Exception) {
                        Log.d("TAG", "handleSignInError: ${e.message}")
                    }
                }
            }

            else -> {
                Log.e("TAG", "Something Here")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        user1.value = currentUser?.displayName.toString() ?: "No User"
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