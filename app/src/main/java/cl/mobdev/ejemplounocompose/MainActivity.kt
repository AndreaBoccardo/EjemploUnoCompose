package cl.mobdev.ejemplounocompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.mobdev.ejemplounocompose.ui.theme.EjemploUnoComposeTheme

private val messages: List<MyMessage> = listOf(
    MyMessage("Hola, primer mensaje!", "Segunda línea Lorem ipsum dolor sit amet consectetur adipiscing elit vivamus, volutpat at pharetra nascetur praesent donec ac augue, sem felis fames natoque netus maecenas imperdiet."),
    MyMessage("Hola, primer mensaje! 2", "Segunda línea Augue litora fusce vulputate vestibulum condimentum molestie auctor, enim natoque montes vehicula arcu aenean tempor scelerisque, integer odio nisi lacinia torquent hendrerit. "),
    MyMessage("Hola, primer mensaje! 3", "Segunda línea Vulputate metus facilisi penatibus potenti laoreet massa eu ut cursus tortor blandit, cum eros malesuada ultricies justo gravida curabitur himenaeos proin libero, neque dictumst mollis magnis rhoncus morbi nisl sapien etiam ultrices."),
    MyMessage("Hola, primer mensaje! 4", "Segunda línea Facilisi orci mauris ac scelerisque molestie lobortis risus elementum vehicula magnis ridiculus senectus nam, tortor litora vestibulum quis leo sodales velit conubia vel fusce augue."),
    MyMessage("Hola, primer mensaje! 5", "Segunda línea  Mattis nostra urna neque mauris lobortis feugiat dictumst et rutrum, donec suscipit platea volutpat tincidunt aliquam justo aenean morbi quam, ut odio penatibus sodales integer egestas pharetra imperdiet."),
    MyMessage("Hola, primer mensaje! 6", "Segunda línea Vulputate metus facilisi penatibus potenti laoreet massa eu ut cursus tortor blandit, cum eros malesuada ultricies justo gravida curabitur himenaeos proin libero, neque dictumst mollis magnis rhoncus morbi nisl sapien etiam ultrices."),
    MyMessage("Hola, primer mensaje! 7", "Segunda línea Ultricies maecenas rutrum orci congue placerat quis senectus mus urna, semper natoque sed platea consequat auctor vestibulum montes nascetur, vitae pulvinar habitasse ridiculus phasellus nec mollis est."),
    MyMessage("Hola, primer mensaje! 8", "Segunda línea Augue litora fusce vulputate vestibulum condimentum molestie auctor, enim natoque montes vehicula arcu aenean tempor scelerisque, integer odio nisi lacinia torquent hendrerit. "),
    MyMessage("Hola, primer mensaje! 9", "Segunda línea Ultricies maecenas rutrum orci congue placerat quis senectus mus urna, semper natoque sed platea consequat auctor vestibulum montes nascetur, vitae pulvinar habitasse ridiculus phasellus nec mollis est."),
    MyMessage("Hola, primer mensaje! 10", "Segunda línea Facilisi orci mauris ac scelerisque molestie lobortis risus elementum vehicula magnis ridiculus senectus nam, tortor litora vestibulum quis leo sodales velit conubia vel fusce augue."),
    MyMessage("Hola, primer mensaje! 11", "Segunda línea  Mattis nostra urna neque mauris lobortis feugiat dictumst et rutrum, donec suscipit platea volutpat tincidunt aliquam justo aenean morbi quam, ut odio penatibus sodales integer egestas pharetra imperdiet."),
    MyMessage("Hola, primer mensaje! 12", "Segunda línea Augue litora fusce vulputate vestibulum condimentum molestie auctor, enim natoque montes vehicula arcu aenean tempor scelerisque, integer odio nisi lacinia torquent hendrerit. "),
    MyMessage("Hola, primer mensaje! 13", "Segunda línea Ultricies maecenas rutrum orci congue placerat quis senectus mus urna, semper natoque sed platea consequat auctor vestibulum montes nascetur, vitae pulvinar habitasse ridiculus phasellus nec mollis est."))

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //bloque, acá botones, etc
        setContent {
            //para tema, se llama de acuerdo al nombre del package
           EjemploUnoComposeTheme {
               MyMessages(messages)
           }
        }
    }
}

data class MyMessage(val title: String, val body:String)

//simular lista de mensajes
@Composable
fun MyMessages(messages: List<MyMessage>){
    LazyColumn(){
        items(messages){message ->
            MyComponent(message)
        }
    }
}

//función que llamaré en setContent, se puede reutilizar
@Composable
fun MyText(text: String, color: Color, style: TextStyle, lines: Int = Int.MAX_VALUE){
    Text(text, color = color, style = style, maxLines = lines)
}

//segunda función
@Composable
fun MyTexts(message: MyMessage){

    var expanded by remember { mutableStateOf(false) }

    //elemento de layout, se separan las líneas una bajo otra, para click
    Column(modifier = Modifier
        .padding(start = 8.dp)
        .clickable {
            expanded = !expanded
        }) {
        MyText(message.title,
        MaterialTheme.colors.primary,
        MaterialTheme.typography.subtitle1)
        //si quiero separar ambas columnas
        Spacer(modifier = Modifier.height(16.dp))
        MyText(message.body,
        MaterialTheme.colors.onBackground,
        MaterialTheme.typography.subtitle2,
        if (expanded) Int.MAX_VALUE else 1
        )
    }
}

//preview modo claro solo @Preview
//preview modo oscuro con parametro, llamar ambas
@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTexts(){
    //debo indicar qué quiero previsualizar
    EjemploUnoComposeTheme {
        MyMessages(messages)

    //para hacer un scroll vertical
        /*val scrollState = rememberScrollState()

        Column(modifier = Modifier.verticalScroll(scrollState)) {
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
            MyComponent()
        }*/
        //MyComponent()
    }
}

//para tener imágenes, agregar en setContent y preview
@Composable
fun MyImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground)
        ,"Mi imagen de prueba",
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
            .size(64.dp)
    )
}

@Composable
fun MyComponent(message: MyMessage){
    //layout filas, un elemento junto a otro
    //modifier permite modificar color, tamaño, etc. padding es un margen
    Row(modifier = Modifier
        .background(MaterialTheme.colors.background)
        .padding(8.dp)) {
        MyImage()
        MyTexts(message)
    }
}