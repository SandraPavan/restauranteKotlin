package com.example.restaurante

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.example.restaurante.db.Restaurante
import br.example.restaurante.db.RestauranteRepository
import kotlinx.android.synthetic.main.activity_restaurante.*

class RestauranteActivity : AppCompatActivity() {

    var restaurante: Restaurante? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurante)

        if (intent?.getSerializableExtra("restaurante") != null) {
            restaurante = intent?.getSerializableExtra("restaurante") as Restaurante
            txtNome?.setText(restaurante?.nome)
            txtEndereco?.setText(restaurante?.endereco)
            txtTelefone?.setText(restaurante?.telefone?.toString())
            txtEmail?.setText(restaurante?.email)
            txtSite?.setText(restaurante?.site)
            txtLink?.setText(restaurante?.linkcardapio)
        }else{
            restaurante = Restaurante()
        }

        val myChildToolbar = toolbar_child
        setSupportActionBar(myChildToolbar)

        // Get a support ActionBar corresponding to this toolbar
        val ab = supportActionBar

        // Enable the Up button
        ab?.setDisplayHomeAsUpEnabled(true)

        btnCadastro?.setOnClickListener {
            restaurante?.nome = txtNome?.text.toString()
            restaurante?.endereco = txtEndereco?.text.toString()
            restaurante?.email = txtEmail?.text.toString()
            restaurante?.telefone = txtTelefone?.text.toString().toLong()
            restaurante?.site = txtSite?.text.toString()
            restaurante?.linkcardapio = txtLink?.text.toString()

            if(restaurante?.id?.toInt() == 0){
                RestauranteRepository(this).create(restaurante!!)
            }else{
                RestauranteRepository(this).update(restaurante!!)
            }
            finish()
        }
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
