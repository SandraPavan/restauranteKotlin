package com.example.restaurante

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home);

        val myToolbar = toolbar
        myToolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(myToolbar)

        val restaurantes = arrayOf("Hora da Fome", "Prato do dia", "Delide")
        val adapter
                = ArrayAdapter(this, android.R.layout.simple_list_item_1, restaurantes)

        var listaRestaurante = lista
        listaRestaurante.setAdapter(adapter);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.novo -> {
                val intent = Intent(this, RestauranteActivity::class.java)
                startActivity(intent)
                return false
            }

            R.id.mapa -> {
                Toast.makeText(this, "Mapa", Toast.LENGTH_LONG).show()
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
