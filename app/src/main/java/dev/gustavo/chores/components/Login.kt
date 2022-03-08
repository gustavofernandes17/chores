package dev.gustavo.chores.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.gustavo.chores.R
import dev.gustavo.chores.ui.theme.Blue500
import dev.gustavo.chores.ui.theme.Blue700

// Still needs to implement function that will handle user signup
// Still needs to implement proper user authentication
@Composable
fun LoginScreen(navController: NavController) {

    var emailField by rememberSaveable { mutableStateOf("") }
    var passwordField by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    val goToGoogle: (context: Context) -> Unit = { context ->

        val temporaryUrl = "https://www.google.com";

        val goToGoogleChromeIntent = Intent(
            Intent.ACTION_VIEW
        );

        goToGoogleChromeIntent.setData(Uri.parse(temporaryUrl));

        context.startActivity(goToGoogleChromeIntent);

    }


    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        HeaderLogo()

        AuthorizationForm(
            emailField,
            { emailField = it },
            passwordField,
            { passwordField = it },
            {navController.navigate("feed")}
        )
        Text(
            text = stringResource(id = R.string.create_account_label),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).clickable {goToGoogle(context)},
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.caption
        )
    }
}

// An Improvised Logo for the App
@Composable
fun HeaderLogo() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary,
        title = { Text("Chores") }
    )
}

//Container for the Email Input and Password Input
@Composable
fun AuthorizationForm(
    emailContent: String,
    onEmailChange: (String) -> Unit,
    passwordContent: String,
    onPasswordChange: (String) -> Unit,
    onSubmitEnter: () -> Unit
) {

    // a simple lambda to check if the email have already been typed before trying to allow the user to log in
    // just for fun and security reasons and also optmization
    val isPossibleToSubmit: (emailContent: String, passwordContent: String) -> Boolean = { emailContent, passwordContent ->
        !(emailContent.isEmpty() || passwordContent.isEmpty())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = emailContent,
            onValueChange = onEmailChange,
            label = { Text(stringResource(R.string.email_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 16.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        TextField(
            value = passwordContent,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.password_label)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 8.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = onSubmitEnter,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp, vertical = 8.dp),
            enabled = isPossibleToSubmit(emailContent, passwordContent)
        ) {
            Text(stringResource(R.string.on_submit_enter_button).uppercase())
        }
    }
}


