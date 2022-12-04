package david.ferrer.mymonkeyproject.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import david.ferrer.mymonkeyproject.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val cineRegular = FontFamily(Font(R.font.cregular))
val cineBold = FontFamily(Font(R.font.cbold))
val cineExtraBold = FontFamily(Font(R.font.cebold))
val cineLight = FontFamily(Font(R.font.clight))
val cineExtraLight = FontFamily(Font(R.font.celight))
val cineHeavy = FontFamily(Font(R.font.cheavy))
val cineThin = FontFamily(Font(R.font.cthin))