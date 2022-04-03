package com.example.gerenciadordeenderecos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.gerenciadordeenderecos.database.daos.EnderecoDao
import com.example.gerenciadordeenderecos.database.models.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Endereco::class], version = 1, exportSchema = false)
public abstract class EnderecoRoomDatabase : RoomDatabase() {

    abstract fun EnderecoDao(): EnderecoDao

    companion object {

        @Volatile
        private var INSTANCE: EnderecoRoomDatabase? = null

        fun getDatabase(scope : CoroutineScope, context: Context): EnderecoRoomDatabase {
            return (INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EnderecoRoomDatabase::class.java,
                    "endereco_database"
                ).addCallback(EnderecoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            })

        }

        private class EnderecoDatabaseCallback(
            private val scope: CoroutineScope
        ): RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->

                    scope.launch {
                        populateDatabase(database.EnderecoDao())
                    }

                }
            }

        }

        private suspend fun populateDatabase(enderecoDao: EnderecoDao) {

            enderecoDao.deleteAll()
            var endereco = Endereco("Teste", "9999999", "00000-000", "Rua cambara orli",
                                    "1200", "jardim aracare", "bl1ap1", "itaqua", "sp")
            enderecoDao.insert(endereco)
        }


    }

}