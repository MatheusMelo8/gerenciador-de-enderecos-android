package com.example.gerenciadordeenderecos

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadordeenderecos.application.EnderecoApplication
import com.example.gerenciadordeenderecos.database.models.Endereco
import com.example.gerenciadordeenderecos.databinding.ActivityMainBinding
import com.example.gerenciadordeenderecos.ui.adapter.EnderecoAdapter
import com.example.gerenciadordeenderecos.ui.view.NewEnderecoActivity
import com.example.gerenciadordeenderecos.ui.viewmodel.EnderecoViewModel
import com.example.gerenciadordeenderecos.ui.viewmodel.EnderecoViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : EnderecoAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var replyString : String

    private val enderecoViewModel : EnderecoViewModel by viewModels {

        EnderecoViewModelFactory((application as EnderecoApplication).repository)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.recyclerView = this.binding.recyclerview
        this.adapter = EnderecoAdapter()
        this.recyclerView.adapter = this.adapter
        this.recyclerView.layoutManager = LinearLayoutManager(this)

        var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if ( result.resultCode == Activity.RESULT_OK) {
                result.data?.let {
                    it.getStringExtra("REPLY")?.let {
                        replyString = it
                    }
                }

                val parts : List<String> = replyString.split(Regex("///"))
                var enderecoInsert : Endereco = Endereco(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],
                    parts[6], parts[7], parts[8])

                enderecoViewModel.insert(
                    enderecoInsert
                )
            }



        }

        this.binding.fab.setOnClickListener {

            resultLauncher.launch(Intent(this, NewEnderecoActivity::class.java))

        }

        this.binding.deleteFab.setOnClickListener {

            MaterialAlertDialogBuilder(this)
                .setTitle("Alerta!!!!")
                .setMessage("Você deseja realmente excluir TODOS os registros de endereços?")
                .setPositiveButton("Sim", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        enderecoViewModel.deleteAll()
                        Toast.makeText(applicationContext, "Você deletou Todos os registros", Toast.LENGTH_LONG).show()
                    }

                })
                .setNegativeButton("Não", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        Toast.makeText(applicationContext, "Operação cancelada", Toast.LENGTH_SHORT).show()
                    }

                })
                .show()

        }

    }

    override fun onStart() {
        super.onStart()

        enderecoViewModel.allEndereco.observe(this) { enderecos ->
            enderecos?.let { adapter.submitList(it) }
        }
    }
}