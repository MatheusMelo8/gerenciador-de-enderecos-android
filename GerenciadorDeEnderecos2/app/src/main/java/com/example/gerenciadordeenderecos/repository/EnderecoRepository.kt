package com.example.gerenciadordeenderecos.repository

import androidx.annotation.WorkerThread
import com.example.gerenciadordeenderecos.database.daos.EnderecoDao
import com.example.gerenciadordeenderecos.database.models.Endereco
import kotlinx.coroutines.flow.Flow

class EnderecoRepository(private val enderecoDao: EnderecoDao) {

    val allEndereco: Flow<List<Endereco>> = enderecoDao.getAlphabetizeEnderecos()

    @WorkerThread
    suspend fun insert(endereco: Endereco){
        enderecoDao.insert(endereco)
    }

    @WorkerThread
    suspend fun deleteAll() {

        enderecoDao.deleteAll()

    }

}