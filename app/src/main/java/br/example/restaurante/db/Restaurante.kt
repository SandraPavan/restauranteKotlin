package br.example.restaurante.db

import java.io.Serializable

data class Restaurante(
    var id: Long = 0,
    var nome: String? = null,
    var endereco: String? = null,
    var telefone: Long? = null,
    var email: String? = null,
    var site: String? = null,
    var linkcardapio: String? = null ) : Serializable {

    override fun toString(): String {
        return nome.toString()
    }
}