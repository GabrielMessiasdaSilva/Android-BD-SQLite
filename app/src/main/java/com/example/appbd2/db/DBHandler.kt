package com.example.appbd2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.appbd2.model.CourseModel


class DBHandler  (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {

        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + ENDERECO_COL + " TEXT,"
                + BAIRRO_COL + " TEXT,"
                + CEP_COL + " TEXT,"
                + CIDADE_COL + " TEXT,"
                + ESTADO_COL + " TEXT,"
                + TEL_COL + " TEXT)")

        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addEndereco(
        courseName: String?,
        courseEndereco: String?,
        coursebairro: String?,
        coursecep: String?,
        coursecidade: String?,
        courseestado: String?,
        coursetel: String?,
    ) {
        val db = this.writableDatabase
        val values = ContentValues()

        values.put(NAME_COL,courseName)
        values.put(ENDERECO_COL,courseEndereco)
        values.put(BAIRRO_COL,coursebairro)
        values.put(CEP_COL,coursecep)
        values.put(CIDADE_COL,coursecidade)
        values.put(ESTADO_COL,courseestado)
        values.put(TEL_COL,coursetel)

        db.insert(TABLE_NAME,null , values)
        db.close()
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(db)

    }
    companion object {
        //criando variáveis constantes para nosso banco de dados.
        // a variável abaixo é para o nome do nosso banco de dados.
        private const val DB_NAME = "coursedb"

        // abaixo de int está a versão do nosso banco de dados
        private const val DB_VERSION = 1

        // a variável abaixo é para o nome da nossa tabela.
        private const val TABLE_NAME = "mycourses"

        // a variável abaixo é para nossa coluna id.

        private const val ID_COL = "id"

        // a variável abaixo é para a coluna do nome
        private const val NAME_COL = "Nome"


        private const val ENDERECO_COL = "Endereço"


        private const val BAIRRO_COL = "Bairro"


        private const val CEP_COL = "Cep"

        private const val CIDADE_COL = "Cidade"

        private const val ESTADO_COL = "Estado"

        private const val TEL_COL = "Telefone"
    }



    fun readCourses(): ArrayList<CourseModel>? {
        val db = this.readableDatabase


        // na linha abaixo estamos criando um banco de dados para leitura do nosso banco de dados.


        // on below line we are creating a cursor with query to read data from database.
        val cursorCourses: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        // on below line we are creating a new array list.
        val courseModelArrayList: ArrayList<CourseModel> = ArrayList()

        // movendo nosso cursor para a primeira posição.
        if (cursorCourses.moveToFirst()) {
            do {
                // na linha abaixo estamos adicionando os dados do cursor à nossa lista de arrays.
                courseModelArrayList.add(
                    CourseModel(
                        cursorCourses.getString(1),
                        cursorCourses.getString(4),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getString(7)
                    )
                )
            } while (cursorCourses.moveToNext())
            // movendo nosso cursor para o próximo.
        }
        // finalmente fechando nosso cursor e retornando nossa lista de arrays.
        cursorCourses.close()
        return courseModelArrayList
    }
}