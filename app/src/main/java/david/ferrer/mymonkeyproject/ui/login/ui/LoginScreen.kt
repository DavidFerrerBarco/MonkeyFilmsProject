package david.ferrer.mymonkeyproject.ui.login.ui

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.R
import david.ferrer.mymonkeyproject.ui.navigate.Routes
import david.ferrer.mymonkeyproject.ui.register.ui.EmailField
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(viewModel: LoginViewModel, navigationController: NavHostController){
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Login(Modifier.align(Alignment.Center), viewModel, navigationController)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel, navigationController: NavHostController) {

    val user : String by viewModel.user.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    val loginEnable : Boolean by viewModel.loginEnable.observeAsState(initial = false)
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    val mostrarAlert = remember { mutableStateOf(false) }

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else {
        if(mostrarAlert.value){
            RecordarEmail{mostrarAlert.value = it}
        }

        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(16.dp))
            UserField(user, { viewModel.onLoginChanged(it, password) })
            Spacer(modifier = Modifier.padding(16.dp))
            PasswordField("Contraseña",password, { viewModel.onLoginChanged(user, it) })
            Spacer(modifier = Modifier.padding(8.dp))
            ForgotPassword(Modifier.align(Alignment.End)){mostrarAlert.value = it}
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(loginEnable) {
                coroutineScope.launch{
                    viewModel.onLoginSelected(navigationController)
                }
            }
            Spacer(modifier = Modifier.padding(100.dp))
            RegisterText(Modifier.align(Alignment.CenterHorizontally), navigationController)
        }
    }
}

@Composable
fun RegisterText(modifier: Modifier,navigationController: NavHostController) {
    Text(
        "Regístrate ahora",
        modifier = modifier.clickable { navigationController.navigate(Routes.Registro.route)},
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun LoginButton(loginEnable: Boolean, onLoginSelected: () -> Unit) {
    Button(
        onClick = {onLoginSelected()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            disabledBackgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = loginEnable
    ) {
        Text("Iniciar Sesión")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier, SacarAlert: (Boolean) -> Unit) {
    Text(
        "Olvidaste la contraseña?",
        modifier = modifier.clickable { SacarAlert(true) },
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun RecordarEmail(onDismiss: (Boolean) -> Unit) {
    var email by remember {
        mutableStateOf("")
    }
    AlertDialog(
        title = {
            Text("Introduce tu correo y recibirás un correo para cambiar la contraseña\n")
        },
        text = {
            EmailField(email, onTextFieldChanged = { email = it })
        },
        onDismissRequest = {onDismiss(false)},
        confirmButton = {
            Button(
                onClick = {
                    if(Patterns.EMAIL_ADDRESS.matcher(email).matches())
                        onDismiss(false)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 70.dp, vertical = 15.dp),
                enabled = Patterns.EMAIL_ADDRESS.matcher(email).matches(),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    disabledBackgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    disabledContentColor = Color.White
                )
            ) {
                Text("Enviar")
            }
        },
        dismissButton = {},
    )
}

@Composable
fun PasswordField(texto: String, password: String, onTextFieldChanged: (String) -> Unit) {
    var passwordVisible by remember{ mutableStateOf(false) }
    TextField(
        value = password,
        onValueChange = {onTextFieldChanged(it)},
        placeholder = { Text(texto) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible}) {
                Icon(
                    imageVector = Icons.Default.Visibility,
                    contentDescription = "visibilidad"
                )
            }
        }
    )
}

@Composable
fun UserField(user: String, onTextFieldChanged:(String) -> Unit) {
    TextField(
        value = user,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Usuario") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF636262),
            backgroundColor = Color(0xFFDEDDDD),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )

}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.monoicono),
        contentDescription = "HEADER",
        modifier = modifier
    )

}