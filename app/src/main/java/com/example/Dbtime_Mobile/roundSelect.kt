package com.example.Dbtime_Mobile

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.google.common.math.DoubleMath.roundToInt
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Math.*
import java.util.*
import kotlin.math.atan2

val displayMetrics = DisplayMetrics()
var width = 0f
var height = 0f
var dns = displayMetrics.density
var rd = 1f
var angle = 200.1
var pic =R.raw.pic11
val db = Firebase.firestore
var picChoice = 99
var time = System.currentTimeMillis()
var timeMS = time.toString()
var date = Date()
var dateSTR = date.toString()
var selectedItem1 = "בחר"
var selectedItem2 = "בחר"
var selectedItem3 = "בחר"
var wordLevel1 = "99"
var wordLevel2 = "99"
var wordLevel3 = "99"

val mAuth = FirebaseAuth.getInstance()
val user: FirebaseUser? = mAuth.getCurrentUser()
val email = user?.email

class roundSelect : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val displayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(displayMetrics)
        dns = displayMetrics.density
        width = (displayMetrics.widthPixels)/dns
        height = (displayMetrics.heightPixels)/dns
        rd = (width-100)/2 // Padding
        setContent {
            PreviewContent()
        }
    }
}

@Composable
fun Content() {
    var radius by remember { mutableStateOf(0f) }
    var shapeCenter by remember { mutableStateOf(Offset.Zero) }
    var handleCenter by remember { mutableStateOf(Offset.Zero) }
    var angle by remember { mutableStateOf(20.0) }
    val paint = Paint().asFrameworkPaint().apply {
        textSize = 100F
        textAlign = android.graphics.Paint.Align.CENTER
    }
    val imageModifier = Modifier
        .offset(y = ((height / 3.6) - rd / 2).dp, x = ((width / 2) - rd / 2).dp)
        .clip(CircleShape)
        .size((rd).dp)
    Column {
    Canvas(
        modifier = Modifier
            .width((width).dp)
            .height((height / 1.8).dp)
            .padding(8.dp)
            //   .fillMaxSize()
            .drawWithCache {
                val brush = Brush.linearGradient(
                    listOf(
                        Color(0xFF25BFEA),
                        Color(0xFF06B7E9)
                    )
                )
                onDrawBehind {
                    drawRoundRect(
                        brush,
                        cornerRadius = CornerRadius(10.dp.toPx())
                    )
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    handleCenter += dragAmount
                    angle = getRotationAngle(handleCenter, shapeCenter)
                    change.consume()
                }
            }
            .padding(100.dp)
    )
    {
        shapeCenter = center
        radius = size.minDimension / 2 + 100
        val x = (shapeCenter.x + cos(toRadians(angle)) * radius).toFloat()
        val y = (shapeCenter.y + sin(toRadians(angle)) * radius).toFloat()
        handleCenter = Offset(x, y)

        drawArc(
            color = Color.Green,
            startAngle = 270f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(80f),
            topLeft = Offset(shapeCenter.x - radius, shapeCenter.y - radius),
            size = Size(radius * 2, radius * 2)
        )
        drawArc(
            color = Color.Cyan,
            startAngle = 342f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(80f),
            topLeft = Offset(shapeCenter.x - radius, shapeCenter.y - radius),
            size = Size(radius * 2, radius * 2)
        )
        drawArc(
            color = Color.Yellow,
            startAngle = 54f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(80f),
            topLeft = Offset(shapeCenter.x - radius, shapeCenter.y - radius),
            size = Size(radius * 2, radius * 2)
        )
        drawArc(
            color = Color.Magenta,
            startAngle = 126f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(80f),
            topLeft = Offset(shapeCenter.x - radius, shapeCenter.y - radius),
            size = Size(radius * 2, radius * 2)
        )
        drawArc(
            color = Color.Red,
            startAngle = 198f,
            sweepAngle = 72f,
            useCenter = false,
            style = Stroke(80f),
            topLeft = Offset(shapeCenter.x - radius, shapeCenter.y - radius),
            size = Size(radius * 2, radius * 2)
        )
        drawIntoCanvas {
            it.nativeCanvas.drawText("מה ההרגשה כרגע בגוף?", 250f, -140f, paint)
        }

        drawCircle(color = Color.Blue, center = handleCenter, radius = 60f)
    }
        secondCanvas()
}
    Image(
        painter = painterResource(
            id = getPic(angle)
        ),
        contentDescription = stringResource(id = R.string.dog_content_description),
        contentScale = ContentScale.Fit,
        modifier = imageModifier,
    )
}

private fun getPic(angl: Double): Int {
    when (angl) {
        in 270.1..342.0 -> {
            pic =  R.raw.pic11
            picChoice=1
        }
        in 342.1..359.9 -> {
            pic = R.raw.pic22
            picChoice=2
        }
        in 0.0..54.0 -> {
            pic = R.raw.pic22
            picChoice=2
        }
        in 54.1..126.0 -> {
            pic =  R.raw.pic33
            picChoice=3
        }
        in 126.1..198.0 -> {
            pic =  R.raw.pic44
            picChoice=4
        }
        in 198.1..270.0 -> {
            pic =  R.raw.pic55
            picChoice=5
        }
        else -> print("none of the above")
    }
    return pic
}

private fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
    val (dx, dy) = currentPosition - center
    val theta = atan2(dy, dx).toDouble()
    angle = Math.toDegrees(theta)
    if (angle < 0) {
        angle += 360.0
    }
    return angle
}

@Preview
@Composable
fun PreviewContent() {
    ComposeTutorialTheme {
        Surface {
            Content()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDB1() {
    val dropDounModifier = Modifier
        .offset(y = 8.dp, x = (8).dp)
        .width((width/1.6).dp)
        .height((60).dp)
    val listItems = arrayOf("בחר","אהבה","אושר","שמחה","נעימות","אמון","בטחון","גאווה","נינוחות","יציבות","התרגשות","סלחנות","חמלה","אכפתיות","רוממות רוח","פיוס","אדיבות","אמפטיה","מוצלחות","סיפוק","הישג","עליונות","כבוד","עונג","רעננות","נאמנות","הכרת תודה","אינטימיות","תקווה","השראה","הצלחה","סקרנות","אומץ","חיבה","נדיבות","איפוק","שלווה")
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {mutableStateOf(false)} // state of the menu
    selectedItem1 by remember {mutableStateOf(listItems[0])} // remember the selected item
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = dropDounModifier,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem1,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "סל חיובי") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                DropdownMenuItem(onClick = {
                    selectedItem1 = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

private infix fun String.by(remember: MutableState<String>) {

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDB2() {
    val dropDounModifier = Modifier
        .offset(y = 8.dp, x = (8).dp)
        .width((width/1.6).dp)
        .height((60).dp)
    val listItems = arrayOf("בחר","פעלתנות","שחרור","זיכוך","חופש","נחרצות","להיטות","תעוזה","מסוגלות","ערנות","התרגשות","ספקנות","מחויבות","אדישות","אפתיה","ניתוק","אטימות","קפדנות")
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {mutableStateOf(false)} // state of the menu
    selectedItem2 by remember {mutableStateOf(listItems[0])} // remember the selected item
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = dropDounModifier,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem2,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "סל נייטרלי") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                DropdownMenuItem(onClick = {
                    selectedItem2 = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyDB3() {
    val dropDounModifier = Modifier
       // .offset(y = ((height / 2) + rd*1.6).dp, x = 8.dp)
        .offset(y = 8.dp, x = (8).dp)
        .width((width/1.6).dp)
        .height((60).dp)
    val listItems = arrayOf("בחר","פחד","לחץ","עצב","כעס","שנאה","קנאה","עקצוץ","גועל","בוז","דיכאון","בגידה","אכזבה","התנגדות","זיעה","רעד","גירוד","גל קור","גל חום","עוינות","ציניות","זלזול","חוסר איזון","עצבנות","עייפות","דחייה","נטישה","פספוס – החמצה","חוסר ערך","דאגה","בלבול","תסכול","בדידות","מועקה","חוסר וודאות","קורבנות","מרירות")
    val contextForToast = LocalContext.current.applicationContext
    var expanded by remember {mutableStateOf(false)} // state of the menu
    selectedItem3 by remember {mutableStateOf(listItems[0])} // remember the selected item
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = dropDounModifier,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        // text field
        TextField(
            value = selectedItem3,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "סל שלילי") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                DropdownMenuItem(onClick = {
                    selectedItem3 = selectedOption
                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

@Composable
fun sendButton() {
    var cntx = LocalContext.current.applicationContext
    val btnModifier = Modifier
        .offset(y = 18.dp, x = (width/4).dp)
        .height((width/6).dp)
        .width((width/2).dp)
        .clip(CircleShape)
    Button(
        modifier = btnModifier,
        onClick = {
            val notificationFB = hashMapOf(
                "picChoice" to picChoice,
                "dateTime" to dateSTR,
                "timeMS" to timeMS,
                "positiveWord" to selectedItem1,
                "neutralWord" to selectedItem2,
                "negativeWord" to selectedItem3,
                "positiveWordLevel" to wordLevel1,
                "neutralWordLevel" to wordLevel2,
                "negativeWordLevel" to wordLevel3,
            )
            if (email != null) {
                db.collection ("users").document(email)
                    .collection("notificationFB").document(dateSTR)
                    .set(notificationFB)
                    .addOnSuccessListener { documentReference ->
                        Log.d("RRubi", "DocumentSnapshot added ")
                    }
                    .addOnFailureListener { e ->
                        Log.w("RRubi", "Error adding document", e)
                    }
            }
            Toast.makeText(cntx, "שולח...", Toast.LENGTH_SHORT).show()
            Thread.sleep(3_000)
            System.exit(0)
        }) {
        Text(text = "שלח")
    }
}

@Composable
fun Slider1(
      ModifierSlyder: Modifier =   Modifier
        .offset(y = 10.dp, x = 30.dp)
        .width(width = 100.dp)
        .height(height = 35.dp)
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    wordLevel1 = sliderPosition.toString().substring(0,sliderPosition.toString().indexOf("."))
    Text(modifier = ModifierSlyder, text = "       "+wordLevel1)
    Slider(
        modifier = ModifierSlyder,
        valueRange = 1f..10f,
        value = sliderPosition,
        onValueChange = { sliderPosition = it })
}


@Composable
fun Slider2(
    ModifierSlyder: Modifier =   Modifier
        .offset(y = 10.dp, x = 30.dp)
        .width(width = 100.dp)
        .height(height = 35.dp)
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    wordLevel2 = sliderPosition.toString().substring(0,sliderPosition.toString().indexOf("."))
    Text(modifier = ModifierSlyder, text = "       "+wordLevel2)
    Slider(
        modifier = ModifierSlyder,
        valueRange = 1f..10f,
        value = sliderPosition,
        onValueChange = { sliderPosition = it })
}

@Composable
fun Slider3(
    ModifierSlyder: Modifier =   Modifier
        .offset(y = 10.dp, x = 30.dp)
        .width(width = 100.dp)
        .height(height = 35.dp)
) {
    var sliderPosition by remember { mutableStateOf(0f) }
    wordLevel3 = sliderPosition.toString().substring(0,sliderPosition.toString().indexOf("."))
    Text(modifier = ModifierSlyder, text = "       "+wordLevel3)
    Slider(
        modifier = ModifierSlyder,
        valueRange = 1f..10f,
        value = sliderPosition,
        onValueChange = { sliderPosition = it })
}

@Composable
fun secondCanvas() {
    val paint = Paint().asFrameworkPaint().apply {
        textSize = 40f
        textAlign = android.graphics.Paint.Align.LEFT
    }
    Box(
        modifier = Modifier
            .offset(y = (0).dp, x = 0.dp)
            .width((width).dp)
            .height((height / 2).dp)
            .padding(8.dp)
            .clip( RoundedCornerShape(8.dp))
            .background(Color(0xFF25BFEA))
    ) {
        Column {
            text1To10()
            Row {
                MyDB1()
                Column {
                   Slider1()
                }
            }
            Row {
                MyDB2()
                Column {
                    Slider2()
                }
            }
            Row {
                MyDB3()
                Column {
                    Slider3()
                }
            }
            Row {
                sendButton()
            }
        }
    }
}

@Composable
fun text1To10() {
    Text(
        "1-קצת...10-הרבה",
        textAlign = TextAlign.Left,
        fontSize = 10.sp,
        modifier = Modifier.width((width-10).dp)
    )
}
