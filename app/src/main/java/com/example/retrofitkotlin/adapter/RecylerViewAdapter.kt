package com.example.retrofitkotlin.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlin.R
import com.example.retrofitkotlin.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*


class RecylerViewAdapter(private val cryptoList: ArrayList<CryptoModel>, private val listener : RowHolder.Listener) : RecyclerView.Adapter<RecylerViewAdapter.RowHolder>() {
    open class RowHolder(view: View) : RecyclerView.ViewHolder(view) {

        interface Listener {
            fun onItemClick(crptoModel: CryptoModel)
        }
        private var colors : Array<String> = arrayOf("#F0F8FF","#FF7F50","#B22222","#FF69B4","#FFFACD","#FFFFFF","#FF0000","#800080","#008000","#808080","#0000FF","#000000","#b765d7","#4decec")


        fun bind(crptoModel: CryptoModel, colors: Array<String>, position: Int, listener: Listener) {
            itemView.setOnClickListener {
                listener.onItemClick(crptoModel)
            }
            itemView.setBackgroundColor(Color.parseColor(colors[position % 14]))
            itemView.text_name.text = crptoModel.currency
            itemView.text_price.text = crptoModel.price

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)

    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {

        holder.bind(cryptoList[position],colors = arrayOf(String()) ,position,listener)



    }

    override fun getItemCount(): Int {
         return cryptoList.count()
    }
}