package com.example.gerenciadordeenderecos.application

import android.app.Application
import com.example.gerenciadordeenderecos.database.EnderecoRoomDatabase
import com.example.gerenciadordeenderecos.repository.EnderecoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

//  Vai ser executada apenas uma vez para termos uma instancia unica de database/repository
class EnderecoApplication : Application(){

    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { EnderecoRoomDatabase.getDatabase(applicationScope,this)}
    val repository by lazy { EnderecoRepository(database.EnderecoDao())}

}