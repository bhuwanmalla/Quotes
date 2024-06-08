package bhuwan.example.quotesexamplewithvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import bhuwan.example.quotesexamplewithvm.Model.Quotes
import bhuwan.example.quotesexamplewithvm.ViewModel.QuotesViewModel
import bhuwan.example.quotesexamplewithvm.ViewModel.QuotesViewModelFactory
import bhuwan.example.quotesexamplewithvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(
            this,
            QuotesViewModelFactory(application)
        )[QuotesViewModel::class.java]


        binding.nextBtn.setOnClickListener {

//      call setquote function every time when next button is clicked
            setQuote(viewModel.nextQuote())
        }


//       calls the array only one time when the user clicked next button,
//       so only one data is called for only one time

//        binding.nextBtn.setOnClickListener {
//            binding.quoteText.text = viewModel.getQuotes().text
//            binding.quoteAuthor.text = viewModel.getQuotes().author
//        }

        binding.previousBtn.setOnClickListener {
            setQuote(viewModel.previousQuote())
        }


        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, viewModel.getQuotes()::class.java)
            startActivity(intent)
        }

    }

    fun setQuote(quote: Quotes) {
        binding.quoteText.text = quote.text
        binding.quoteAuthor.text = quote.author
    }

}