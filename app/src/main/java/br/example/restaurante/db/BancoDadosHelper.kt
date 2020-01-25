package br.example.restaurante.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import br.example.restaurante.db.ConstantsDb.RESTAURANTE_DB_TABLE
import br.example.restaurante.db.ConstantsDb.RESTAURANTE_DB_NAME

class BancoDadosHelper(context: Context) :
    ManagedSQLiteOpenHelper(ctx = context ,
        name = "restaurante.db",  version = 1) {

    private val scriptSQLCreate = arrayOf(
        "INSERT INTO $RESTAURANTE_DB_TABLE VALUES(1, 'Delide','Rua Machado de Assis, 8-04','14996334981','https://www.facebook.com/delidigrill/','https://www.facebook.com/delidigrill/', 'https://www.facebook.com/pg/delidigrill/about/?ref=page_internal');",
        "INSERT INTO $RESTAURANTE_DB_TABLE VALUES(2,'Brasileirinho','Rua Antonio Alves, 20-49','14999013355', 'http://www.brasileirinhodelivery.com.br/unidade/bauru', 'http://www.brasileirinhodelivery.com.br/unidade/bauru', 'https://pedidos.brasileirinhodelivery.com.br/web/');")

    //singleton da classe
    companion object {
        private var instance: BancoDadosHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): BancoDadosHelper {
            if (instance == null) {
                instance = BancoDadosHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }


    override fun onCreate(db: SQLiteDatabase) {
        // Criação de tabelas
        db.createTable("$RESTAURANTE_DB_TABLE", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "nome" to TEXT,
            "endereco" to TEXT,
            "telefone" to INTEGER,
            "email" to TEXT,
            "site" to TEXT,
            "linkcardapio" to TEXT

        )

        // insere dados iniciais na tabela
        scriptSQLCreate.forEach {sql ->
            db.execSQL(sql)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Atualização do banco de dados
        db.dropTable("$RESTAURANTE_DB_NAME", true)
        onCreate(db)

    }

}

val Context.database: BancoDadosHelper get() = BancoDadosHelper.getInstance(getApplicationContext())

