package com.example.gerenciadordeenderecos.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadordeenderecos.R
import com.example.gerenciadordeenderecos.database.models.Endereco

class EnderecoAdapter : ListAdapter<Endereco, EnderecoAdapter.EnderecoViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnderecoViewHolder {
        return EnderecoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EnderecoViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.nome, current.telefone, current.cep, current.logradouro, current.numero,
                    current.bairro, current.complemento, current.cidade, current.estado)
    }

    class EnderecoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nomeItemView : TextView = itemView.findViewById(R.id.tvNome)
        private val telefoneItemView : TextView = itemView.findViewById(R.id.tvTelefone)
        private val logradouroItemView : TextView = itemView.findViewById(R.id.tvLogradouro)

        @SuppressLint("SetTextI18n")
        fun bind(text: String?, telefone: String?, cep: String?, logradouro: String?, numero: String?,
                 bairro: String?, complemento: String?, cidade: String?, estado: String?) {
            nomeItemView.text = text
            telefoneItemView.text = "Tel: $telefone"

            if(complemento == null){
                logradouroItemView.text = "CEP: $cep - $logradouro, $numero - $bairro - $cidade - $estado"
            } else {
                logradouroItemView.text = "CEP: $cep - $logradouro, $numero - $complemento - $bairro - $cidade - $estado"
            }

        }

        companion object {
            fun create(parent: ViewGroup): EnderecoViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_items, parent, false)

                return EnderecoViewHolder(view)
            }
        }

    }

    class WordsComparator : DiffUtil.ItemCallback<Endereco>() {
        override fun areItemsTheSame(oldItem: Endereco, newItem: Endereco): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Endereco, newItem: Endereco): Boolean {
            return oldItem.nome == newItem.nome
        }
    }

}