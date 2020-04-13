package com.example.weatherapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.Data
import kotlinx.android.synthetic.main.layout_list_kota.view.*

class DataAdapter(val context : Context, val listDatas : List<Data>) : RecyclerView.Adapter<DataAdapter.DataListViewHolder>(), Filterable{
    val listData = listDatas
    companion object {
        fun searchItem () {

        }
    }

    class DataListViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val title = itemView.tv_kota
        val positif = itemView.tv_jp
        val meninggal = itemView.tv_jm
        val sembuh = itemView.tv_js

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_kota,parent,false)
        return DataListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        holder.title.text = listData[position].provinsi
        holder.positif.text = "Jumlah Positif : ${listData[position].kasusPosi}"
        holder.meninggal.text = "Jumlah Meninggal : ${listData[position].kasusMeni}"
        holder.sembuh.text = "Jumlah Sembuh : ${listData[position].kasusSemb}"
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }
}