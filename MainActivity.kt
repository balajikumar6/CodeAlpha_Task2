package com.example.randomquoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Quote(val text: String, val author: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentQuote by remember { mutableStateOf(quotes.random()) }

            RandomQuoteScreen(
                quote = currentQuote,
                onNewQuoteClick = { currentQuote = quotes.random() }
            )
        }
    }

    private val quotes = listOf(
        Quote("Be yourself; everyone else is already taken.", "Oscar Wilde"),
        Quote("In the middle of every difficulty lies opportunity.", "Albert Einstein"),
        Quote("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill")
    )

}

@Composable
fun RandomQuoteScreen(quote: Quote, onNewQuoteClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "\"${quote.text}\"",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Author name
        Text(
            text = "- ${quote.author.ifEmpty { "Unknown" }}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNewQuoteClick) {
            Text("New Quote")
        }
    }
}

