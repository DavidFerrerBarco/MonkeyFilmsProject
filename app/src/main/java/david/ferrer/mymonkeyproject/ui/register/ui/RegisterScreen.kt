package david.ferrer.mymonkeyproject.ui.register.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import david.ferrer.mymonkeyproject.ui.login.ui.*
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, navigationController: NavHostController) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)){
        Register(Modifier.align(Alignment.Center), viewModel, navigationController)
    }
}

@Composable
fun Register(modifier: Modifier, viewModel: RegisterViewModel, navigationController: NavHostController) {
    val user : String by viewModel.user.observeAsState(initial = "")
    val email : String by viewModel.email.observeAsState(initial = "")
    val password : String by viewModel.password.observeAsState(initial = "")
    var secondPassword : String by remember{ mutableStateOf("") }
    val registerEnable : Boolean by viewModel.registerEnable.observeAsState(initial = false)
    val isLoading : Boolean by viewModel.isLoading.observeAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    val accion : Boolean by viewModel.generoAccion.observeAsState(initial = false)
    val terror : Boolean by viewModel.generoTerror.observeAsState(initial = false)
    val suspense : Boolean by viewModel.generoSuspense.observeAsState(initial = false)
    val anime : Boolean by viewModel.generoAnime.observeAsState(initial = false)
    val drama : Boolean by viewModel.generoDrama.observeAsState(initial = false)
    val romance : Boolean by viewModel.generoRomance.observeAsState(initial = false)

    if(isLoading){
        Box(Modifier.fillMaxSize()){
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }else {

        Column(modifier = modifier) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(8.dp))
            UserField(user, { viewModel.onRegisterChanged(it, email, password) })
            Spacer(modifier = Modifier.padding(8.dp))
            EmailField(email, { viewModel.onRegisterChanged(user, it, password) })
            Spacer(modifier = Modifier.padding(8.dp))
            PasswordField("Contraseña", password, { viewModel.onRegisterChanged(user, email,it) })
            Spacer(modifier = Modifier.padding(8.dp))
            PasswordField("Repite Contraseña", secondPassword, { secondPassword = it })
            Spacer(modifier = Modifier.padding(8.dp))
            TituloGenero()
            Spacer(modifier = Modifier.padding(8.dp))
            Row{
                GeneroCheckBox("Acción", accion) { viewModel.onAccionChanged(it) }
                Spacer(modifier = Modifier.padding(12.dp))
                GeneroCheckBox("Terror", terror) { viewModel.onTerrorChanged(it) }
                Spacer(modifier = Modifier.padding(12.dp))
                GeneroCheckBox("Suspense", suspense) { viewModel.onSuspenseChanged(it) }
            }
            Row{
                GeneroCheckBox("Anime", anime) { viewModel.onAnimeChanged(it) }
                Spacer(modifier = Modifier.padding(12.dp))
                GeneroCheckBox("Drama", drama) { viewModel.onDramaChanged(it) }
                Spacer(modifier = Modifier.padding(12.dp))
                GeneroCheckBox("Romance", romance) { viewModel.onRomanceChanged(it) }
            }
            RegisterButton(registerEnable, password, secondPassword) {
                coroutineScope.launch{
                    viewModel.onLoginSelected(navigationController)
                }
            }
        }
    }
}

@Composable
fun GeneroCheckBox(texto: String, genero: Boolean, onGeneroChange: (Boolean) -> Unit) {
    Row {
        Checkbox(
            checked = genero,
            onCheckedChange = { onGeneroChange(genero)},
            colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primaryVariant)
        )
        Text(texto, modifier = Modifier.padding(top = 11.dp))
    }
}

@Composable
fun TituloGenero() {
    Text(
        "Géneros preferidos",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun RegisterButton(registerEnable: Boolean, password : String, secondPassword: String, onLoginSelected: () -> Unit) {
    Button(
        onClick = {
            if(password == secondPassword)
                onLoginSelected()
                  },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            disabledBackgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled =
        if(password != secondPassword)
            false
        else
            registerEnable
    ) {
        Text("Registrarse")
    }
}

@Composable
fun EmailField(email: String, onTextFieldChanged: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {onTextFieldChanged(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("Email") },
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
