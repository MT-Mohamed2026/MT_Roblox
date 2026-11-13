package com.mtprograms

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF101719)
private val Panel = Color(0xFF1B292B)
private val Accent = Color(0xFF20C997)
private val SoftText = Color(0xFFB8C9C6)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MTProgramsApp() }
    }
}

@Composable
fun MTProgramsApp() {
    var connected by remember { mutableStateOf(false) }
    var volume by remember { mutableFloatStateOf(1f) }
    var bass by remember { mutableStateOf(false) }
    var enhance by remember { mutableStateOf(false) }
    var boost by remember { mutableStateOf(false) }
    var arabic by remember { mutableStateOf(true) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Accent,
            background = Background,
            surface = Panel,
            onPrimary = Color.Black
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "MT Programs",
                    color = Accent,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Text("For Enjoy and Problems", color = SoftText)

                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(
                        Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("الاتصال بالكمبيوتر", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(
                            if (connected) "الحالة: متصل (تجريبي)"
                            else "الحالة: غير متصل",
                            color = if (connected) Accent else SoftText
                        )
                        Button(
                            onClick = { connected = !connected },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(if (connected) "إيقاف الاتصال التجريبي" else "ابدأ")
                        }
                        Text(
                            "ملاحظة: الاتصال الحقيقي بالكمبيوتر لم يُفعّل بعد.",
                            color = SoftText,
                            fontSize = 12.sp
                        )
                        Text("يمكن لاحقًا استخدام Wi-Fi أو USB Tethering.")
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(
                        Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("إعدادات الصوت", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("مستوى الصوت: ${(volume * 1000).toInt()}%")
                        Slider(
                            value = volume,
                            onValueChange = { volume = it },
                            valueRange = 0f..10f
                        )
                        Text(
                            "ارفع الصوت بحذر؛ المستويات العالية قد تسبب تشويشًا أو ضررًا.",
                            color = SoftText,
                            fontSize = 12.sp
                        )
                        SettingSwitch("Bass Booster", bass) { bass = it }
                        SettingSwitch("Audio Quality Enhancer", enhance) { enhance = it }
                        SettingSwitch("Sound Boost", boost) { boost = it }
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(containerColor = Panel),
                    shape = RoundedCornerShape(18.dp)
                ) {
                    Column(
                        Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("لوحة المفاتيح", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            OutlinedButton(onClick = { arabic = !arabic }) {
                                Text(if (arabic) "عربي" else "English")
                            }
                        }
                        val rows = if (arabic) {
                            listOf(
                                listOf("ض","ص","ث","ق","ف","غ","ع","ه","خ","ح","ج"),
                                listOf("ش","س","ي","ب","ل","ا","ت","ن","م","ك","ط"),
                                listOf("ئ","ء","ؤ","ر","لا","ى","ة","و","ز","ظ")
                            )
                        } else {
                            listOf(
                                listOf("Q","W","E","R","T","Y","U","I","O","P"),
                                listOf("A","S","D","F","G","H","J","K","L"),
                                listOf("Z","X","C","V","B","N","M")
                            )
                        }
                        rows.forEach { keys ->
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                keys.forEach { key ->
                                    OutlinedButton(
                                        onClick = { },
                                        modifier = Modifier.weight(1f).height(44.dp),
                                        contentPadding = PaddingValues(0.dp)
                                    ) {
                                        Text(key, fontSize = 13.sp)
                                    }
                                }
                            }
                        }
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) {
                                Text("⌫")
                            }
                            Button(onClick = { }, modifier = Modifier.weight(2f)) {
                                Text("Space")
                            }
                            OutlinedButton(onClick = { }, modifier = Modifier.weight(1f)) {
                                Text("Enter")
                            }
                        }
                        Text(
                            "هذه لوحة واجهة أولية؛ إرسال المفاتيح للكمبيوتر سيُضاف في مرحلة الاتصال.",
                            color = SoftText,
                            fontSize = 12.sp
                        )
                    }
                }

                Text(
                    "MT Programs • Prototype 0.1.0",
                    color = SoftText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun SettingSwitch(title: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, fontSize = 14.sp)
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
