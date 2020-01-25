package br.example.restaurante.db

import android.content.Context
import br.example.restaurante.db.ConstantsDb.RESTAURANTE_DB_TABLE
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import org.jetbrains.anko.db.delete
import timber.log.Timber


class RestauranteRepository(val context: Context) {

    fun findAll() : ArrayList<Restaurante> = context.database.use {
        val restaurantes = ArrayList<Restaurante>()

        select(RESTAURANTE_DB_TABLE, "id", "nome", "endereco",  "telefone","email", "site", "linkcardapio")
            .parseList(object: MapRowParser<List<Restaurante>> {
                override fun parseRow(columns: Map<String, Any?>): List<Restaurante> {
                    val restaurante = Restaurante(
                        id = columns.getValue("id").toString()?.toLong(),
                        nome = columns.getValue("nome") as String,
                        endereco = columns.getValue("endereco") as String,
                        telefone = columns.getValue("telefone")?.toString()?.toLong(),
                        email = columns.getValue("email") as String,
                        site = columns.getValue("site") as String,
                        linkcardapio = columns.getValue("linkcardapio") as String)
                    restaurantes.add(restaurante)
                    return restaurantes
                }
            })

        restaurantes
    }


    fun create(restaurante: Restaurante) = context.database.use {
        insert(RESTAURANTE_DB_TABLE,
            "nome" to restaurante.nome,
            "endereco" to restaurante.endereco,
            "telefone" to restaurante.telefone,
            "email" to restaurante.email,
            "site" to restaurante.site,
            "linkcardapio" to restaurante.linkcardapio)
    }

    fun update(restaurante: Restaurante) = context.database.use {
        val updateResult = update(RESTAURANTE_DB_TABLE,
            "nome" to restaurante.nome,
            "endereco" to restaurante.endereco,
            "telefone" to restaurante.telefone,
            "email" to restaurante.email,
            "site" to restaurante.site,
            "linkcardapio" to restaurante.linkcardapio)
            .whereArgs("id = {id}","id" to restaurante.id).exec()

        Timber.d("Update result code is $updateResult")
    }


    fun delete(id: Long) = context.database.use {
        delete(RESTAURANTE_DB_TABLE, "id = {restauranteId}", args = *arrayOf("restauranteId" to id))
    }
}
