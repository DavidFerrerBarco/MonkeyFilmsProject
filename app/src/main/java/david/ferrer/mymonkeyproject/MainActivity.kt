package david.ferrer.mymonkeyproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import david.ferrer.mymonkeyproject.ui.login.ui.LoginScreen
import david.ferrer.mymonkeyproject.ui.login.ui.LoginViewModel
import david.ferrer.mymonkeyproject.ui.theme.MyMonkeyProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyMonkeyProjectTheme(

            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomNavigator()
                }
            }
        }
    }
}
