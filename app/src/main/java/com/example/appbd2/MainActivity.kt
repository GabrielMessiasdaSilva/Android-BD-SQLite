package com.example.appbd2

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appbd2.R
import com.example.appbd2.db.DBHandler
import com.example.appbd2.ui.theme.AppBD2Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.M)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppBD2Theme {
                App()
        }
    }
}


@Preview(showBackground = true, widthDp = 200, heightDp =1400)
@Composable
fun PreviewApp(){
    App()

}



    @Composable
    fun App() {
        val context = LocalContext.current
        val dbHandler = DBHandler(context)
        AppBD2Theme {
            var isMenuExpanded by remember { mutableStateOf(false) }

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                SimpleCenterAlignedTopAppBar(
                    isMenuExpanded = isMenuExpanded,
                    onMenuClick = { isMenuExpanded = !isMenuExpanded }
                )
            }

            Surface(
                modifier = Modifier.fillMaxSize().padding(top = 60.dp),


                ) {
                val activity = context as Activity
                val CourseName = remember {
                    mutableStateOf(TextFieldValue())
                }
                val courseEndereco = remember {
                    mutableStateOf(TextFieldValue())
                }
                val coursebairro = remember {
                    mutableStateOf(TextFieldValue())
                }
                val coursecep = remember {
                    mutableStateOf(TextFieldValue())
                }
                val coursecidade = remember {
                    mutableStateOf(TextFieldValue())
                }
                val courseestado = remember {
                    mutableStateOf(TextFieldValue())
                }
                val coursetel = remember {
                    mutableStateOf(TextFieldValue())
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 40.dp)
                ) {
                    item {
                        Imagem()
                    }


                    item {
                        SimpleTextFieldSample("Nome:", CourseName.value) {
                            CourseName.value = it
                        }
                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("Endereço:", courseEndereco.value) {
                            courseEndereco.value = it
                        }

                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("Bairro:", coursebairro.value) {
                            coursebairro.value = it
                        }

                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("Cep:", coursecep.value) {
                            coursecep.value = it
                        }
                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("Cidade:", coursecidade.value) {
                            coursecidade.value = it
                        }
                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("Estado:", courseestado.value) {
                            courseestado.value = it
                        }
                        Divider(Modifier.height(20.dp))
                        SimpleTextFieldSample("telefone:", coursetel.value) {
                            coursetel.value = it
                        }


                        Divider(Modifier.height(20.dp))
                        ButtonSample(
                            dbHandler,
                            CourseName.value,
                            courseEndereco.value,
                            coursebairro.value,
                            coursecep.value,
                            coursecidade.value,
                            courseestado.value,
                            coursetel.value,
                            context
                        )
                    }
                }
            }
        }

    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleCenterAlignedTopAppBar(isMenuExpanded: Boolean, onMenuClick: () -> Unit) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Color.Black
                    ),
                    title = {
                        Column() {
                            Text(text = "Ranix", color = Color.Blue)
                            Text(
                                text = "Cadastro",
                                color = Color.White,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier.align(
                                    Alignment.CenterHorizontally
                                )
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { onMenuClick() }) {
                            Icon(
                                imageVector = Icons.Filled.Menu, tint = Color.Blue,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle, tint = Color.Blue,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            }
        ) {
            if (isMenuExpanded) {
                MenuRanix()
            }
        }
    }



    @Composable
    fun MenuRanix() {
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Descriçaõ Localizada ")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Editar") },
                    onClick = { /* Handle edit! */ },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Edit,
                            contentDescription = null
                        )
                    })
                DropdownMenuItem(
                    text = { Text("Configurações") },
                    onClick = { /* Handle settings! */ },
                    leadingIcon = {
                        Icon(
                            Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    })
            }}}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SimpleTextFieldSample(campo: String, value:TextFieldValue, onValueChange:(TextFieldValue)-> Unit){
        var text by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            label = {
                Text(campo)
            },
            singleLine = true
        )
    }}
    @Composable
    fun ButtonSample(
        dbHandler: DBHandler,
        courseName:TextFieldValue,
        courseEndereco:TextFieldValue,
        coursebairro:TextFieldValue,
        coursecep: TextFieldValue,
        coursecidade: TextFieldValue,
        courseestado: TextFieldValue,
        coursetel: TextFieldValue,
        context: Context
    ) {
        Button(onClick = {  dbHandler.addEndereco(
            courseName.text,
            courseEndereco.text,
            coursebairro.text,
            coursecep.text,
            coursecidade.text,
            courseestado.text,
            coursetel.text,
        )
            Toast.makeText(context, "Cadastro realizado", Toast.LENGTH_SHORT).show()
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Text("Cadastrar")
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Imagem(){
        Image(
            painter = painterResource(R.drawable.login),
            contentDescription = "Foto de Contato de Perfil",
            modifier = Modifier
                .height(300.dp)
                .width(500.dp)

        )}}
