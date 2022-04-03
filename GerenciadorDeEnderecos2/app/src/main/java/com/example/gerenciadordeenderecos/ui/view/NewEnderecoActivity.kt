package com.example.gerenciadordeenderecos.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.gerenciadordeenderecos.databinding.ActivityNewEnderecoBinding

class NewEnderecoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNewEnderecoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityNewEnderecoBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    override fun onStart() {
        super.onStart()

        this.binding.btnSalvar.setOnClickListener {

            var nome = this.binding.textNome.text.toString()
            var telefone = this.binding.textTelefone.text.toString()
            var cep = this.binding.textCep.text.toString()
            var logradouro = this.binding.textLogradouro.text.toString()
            var numero = this.binding.textNumero.text.toString()
            var bairro = this.binding.textBairro.text.toString()
            var complemento = this.binding.textComplemento.text.toString()
            var cidade = this.binding.textCidade.text.toString()
            var estado = this.binding.textEstado.text.toString()

            val replyIntent = Intent()

            if (saveData(telefone, logradouro, cep)){

                var reply : String = "${nome}///$telefone///$cep///$logradouro///$numero///$bairro///$complemento///$cidade///$estado"
                replyIntent.putExtra("REPLY", reply)
                Toast.makeText(applicationContext, "Seu endereço foi salvo com sucesso!", Toast.LENGTH_LONG).show()
                setResult(RESULT_OK, replyIntent)
                finish()

            } else {

                Toast.makeText(applicationContext, "Verifique se preencheu os campos corretamente!!!", Toast.LENGTH_LONG).show()
                setResult(RESULT_CANCELED, replyIntent)

            }


        }

    }

    private fun saveData(telefone : String, logradouro: String, cep : String) : Boolean{

        if(telefone.isBlank() || telefone.isEmpty() || telefone.length != 14 ){
            return false
        }

        if(logradouro.isBlank() || logradouro.isEmpty() || logradouro.length < 10 || logradouro.length > 100  ){
            return false
        }

        if(cep.isBlank() || cep.isEmpty() || cep.length != 8 ){
            return false
        }

        return true

    }

}