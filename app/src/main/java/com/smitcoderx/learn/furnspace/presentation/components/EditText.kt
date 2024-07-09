package com.smitcoderx.learn.furnspace.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smitcoderx.learn.furnspace.R
import com.smitcoderx.learn.furnspace.core.mulishFontFamily
import com.smitcoderx.learn.furnspace.ui.theme.HintColor
import com.smitcoderx.learn.furnspace.ui.theme.IconColor
import com.smitcoderx.learn.furnspace.ui.theme.TextColor
import com.smitcoderx.learn.furnspace.ui.theme.TextFieldColor

@Composable
fun EditText(
    value: String,
    hint: String,
    interactionSource: MutableInteractionSource,
    isPasswordField: Boolean = false,
    inputType: KeyboardType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordVisibility: Boolean by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = inputType, imeAction = ImeAction.Next),
        onValueChange = { onValueChange(it) },
        singleLine = true,
        visualTransformation = if (isPasswordField) if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation() else VisualTransformation.None,
        maxLines = 1,
        textStyle = TextStyle(
            color = TextColor,
            fontFamily = mulishFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
        ),
        modifier = modifier
            .fillMaxWidth()
            .background(
                TextFieldColor, shape = RoundedCornerShape(10.dp),
            )
            .padding(15.dp),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(contentAlignment = Alignment.TopStart) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = HintColor,
                        fontFamily = mulishFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                }
                if (isPasswordField) {
                    val image = if (passwordVisibility)
                        R.drawable.ic_visibilty
                    else R.drawable.ic_visibility_off
                    IconButton(
                        onClick = {
                            passwordVisibility = !passwordVisibility
                        }, modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(25.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = image),
                            contentDescription = "password",
                            tint = IconColor,
                        )
                    }
                }
                innerTextField()
            }
        }
    )
}