package com.example.gerenciadordeenderecos.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabela_endereco")
data class Endereco(

    @PrimaryKey
    @ColumnInfo(name="nome")val nome : String,
    @ColumnInfo(name = "telefone")val telefone: String?,
    @ColumnInfo(name = "cep")val cep: String?,
    @ColumnInfo(name = "logradouro")val logradouro: String?,
    @ColumnInfo(name = "numero")val numero: String?,
    @ColumnInfo(name = "bairro")val bairro: String?,
    @ColumnInfo(name = "complemento")val complemento: String?,
    @ColumnInfo(name = "cidade")val cidade: String?,
    @ColumnInfo(name = "estado")val estado: String?

)
