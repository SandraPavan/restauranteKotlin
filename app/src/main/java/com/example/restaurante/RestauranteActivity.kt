package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_restaurante.*

class RestauranteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)

        val myChildToolbar = toolbar_child
        setSupportActionBar(myChildToolbar)

        // Get a support ActionBar corresponding to this toolbar
        val ab = supportActionBar

        // Enable the Up button
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu_cardapio: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_cardapio, menu_cardapio)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.cardapio -> {
                val intent = Intent(this, CardapioActivity::class.java)
                startActivity(intent)
                return false
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
