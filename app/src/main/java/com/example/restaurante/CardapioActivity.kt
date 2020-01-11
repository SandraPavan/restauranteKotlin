package com.example.restaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cardapio.*

class CardapioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardapio)

        val myChildToolbar = toolbar_child_restaurant
        setSupportActionBar(myChildToolbar)

        // Get a support ActionBar corresponding to this toolbar
        val ab = supportActionBar

        // Enable the Up button
        ab?.setDisplayHomeAsUpEnabled(true)
    }
}
