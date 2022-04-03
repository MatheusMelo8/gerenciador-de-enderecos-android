package com.example.gerenciadordeenderecos.ui.viewmodel

import androidx.lifecycle.*
import com.example.gerenciadordeenderecos.database.models.Endereco
import com.example.gerenciadordeenderecos.repository.EnderecoRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class EnderecoViewModel(private val repository: EnderecoRepository) : ViewModel() {

    val allEndereco : LiveData<List<Endereco>> = repository.allEndereco.asLiveData()

    fun insert(endereco: Endereco) = viewModelScope.launch {

        repository.insert(endereco)

    }

    fun deleteAll() = viewModelScope.launch {

        repository.deleteAll()

    }

}

class EnderecoViewModelFactory(private val repository: EnderecoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EnderecoViewModel::class.java)) {
            return EnderecoViewModel(repository) as T
        }

        throw IllegalArgumentException("Uknow ViewModel class")
    }

}