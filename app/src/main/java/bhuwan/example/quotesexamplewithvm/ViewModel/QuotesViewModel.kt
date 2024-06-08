package bhuwan.example.quotesexamplewithvm.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import bhuwan.example.quotesexamplewithvm.Model.Quotes
import com.google.gson.Gson

class QuotesViewModel(val context: Context) : ViewModel() {

    lateinit var quotesArray: Array<Quotes>
    var index: Int = 0

    init {
        quotesArray = loadFromAssets()
    }

    private fun loadFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)

    }

    // Explicit return type function

    fun getQuotes(): Quotes {
        return quotesArray[index]
    }

    // Same as above function getQuotes
    // single-line function

    // fun getQuotes() = quotesArray[index]


    fun nextQuote() = quotesArray[index++]

    fun previousQuote() = quotesArray[index--]


}