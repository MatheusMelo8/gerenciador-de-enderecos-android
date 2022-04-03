package com.example.gerenciadordeenderecos.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gerenciadordeenderecos.database.models.Endereco
import kotlinx.coroutines.flow.Flow


@Dao
interface EnderecoDao {

    @Query("SELECT * FROM tabela_endereco ORDER BY nome ASC")
    fun getAlphabetizeEnderecos(): Flow<List<Endereco>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(endereco: Endereco)

    @Query("DELETE FROM tabela_endereco")
    suspend fun deleteAll()

}