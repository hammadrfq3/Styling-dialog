package comcustom.dialog.ui

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import comcustom.dialog.R


@Composable
fun MyDialog(
    showDialog: Boolean = true,
    title: String,
    positiveButtonText: String,
    negativeButtonText: String,
    buttonShape: Shape = RoundedCornerShape(15.dp),
    dialogBackgroundColor: Color = colorResource(id = R.color.white),
    negativeButtonBackgroundColor: Color = colorResource(id = R.color.white),
    isCancelable: Boolean = true,
    isEditableDialog: Boolean = false,
    isAnimatedDialog: Boolean = false,
    isPickerDialog: Boolean = false,
    textAlign: TextAlign = TextAlign.Center,
    @RawRes lottieAnimatorRes: Int? = null,
    @DrawableRes image: Int? = null,
    description: String,
    onDismiss: () -> Unit,
    onDoneClick:  (Any) -> Unit,
) {

    var returnValue : Any? = null

    if (showDialog) {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = isCancelable,
                dismissOnBackPress = isCancelable
            ),
            onDismissRequest = {
                onDismiss.invoke()
            },
        ) {

            Box(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = if (image != null || lottieAnimatorRes != null) 25.dp else 0.dp)
                        .background(color = dialogBackgroundColor, RoundedCornerShape(20.dp))
                        .padding(top = if (image != null || lottieAnimatorRes != null) 50.dp else 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 10.dp),
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp),
                            text = title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            textAlign = textAlign
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp, end = 10.dp, bottom = 10.dp, start = 10.dp)
                            .fillMaxWidth()
                            .background(color = Color.Transparent, shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        if (!isPickerDialog)
                            Button(
                                modifier = Modifier
                                    .weight(.5f)
                                    .height(45.dp),
                                onClick = {
                                    onDismiss.invoke()
                                },
                                shape = buttonShape,
                                colors = ButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.White,
                                    disabledContentColor = Color.Transparent,
                                    disabledContainerColor = Color.Transparent
                                )
                            ) {
                                Text(
                                    text = negativeButtonText,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        Button(
                            modifier = Modifier
                                .weight(.5f)
                                .height(45.dp),
                            onClick = {
                                returnValue?.let {
                                    onDoneClick.invoke(it)
                                } ?: kotlin.run {
                                    onDoneClick.invoke("")
                                }
                            },
                            shape = buttonShape,

                        ) {
                            Text(
                                text = positiveButtonText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                if (image != null)
                    Box(
                        modifier = Modifier
                            .background(Color.White, shape = CircleShape)
                    ) {
                        Image(
                            modifier = Modifier
                                .size(62.dp)
                                .padding(6.dp),
                            alignment = Alignment.Center,
                            painter = painterResource(id = image),
                            contentDescription = ""
                        )
                    }
            }

        }
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDialog(
        title = "Enter dialog title",
        description = "A dialog is a type of modal window that appears in front of app content to provide critical information, or prompt for a decision to be made.",
        positiveButtonText = "Ok",
        negativeButtonText = "Cancel"
        ,onDismiss = {
        },
        onDoneClick = {
        })
}