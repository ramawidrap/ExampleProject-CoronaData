package com.example.weatherapp

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.weatherapp.adapter.DataAdapter
import com.example.weatherapp.model.Data
import com.example.weatherapp.viewmodel.DataViewModel
import com.example.weatherapp.viewmodel.DataViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_region.*
import javax.inject.Inject


class ListRegionFragment : Fragment() {

    private lateinit var data : List<Data>

    private lateinit var searchView: SearchView

    private lateinit var queryTextListener: SearchView.OnQueryTextListener

    @Inject
    lateinit var dataViewModelFactory : ViewModelProvider.Factory

    private lateinit var viewModel: DataViewModel
    private lateinit var dataAdapter : DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_list_region, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        App.app.getApplicationComponent().inject(this)
        viewModel = ViewModelProvider(this,dataViewModelFactory).get(DataViewModel::class.java)
        viewModel.getData().observe(this.viewLifecycleOwner, Observer<List<Data>>{
            data = it
            initUI()
        })

        viewModel.getError().observe(this.viewLifecycleOwner, Observer<Throwable> {
            Log.i("getData",it.message)
            Toast.makeText(this.context,it.message,Toast.LENGTH_LONG).show()
        })

    }

    private fun initUI() {
        dataAdapter = DataAdapter(this.context!!,data)
        rv_listKota.layoutManager = GridLayoutManager(this.context, 2)
        rv_listKota.adapter = dataAdapter
        dataAdapter.notifyDataSetChanged()

    }

    companion object {

        fun newInstance() : ListRegionFragment{
            return ListRegionFragment()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.i("QUERY","MENU CREATED")


        inflater.inflate(R.menu.app_menu,menu)
        searchView = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager =  activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))

        queryTextListener = object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("QUERY","WKWKWKW")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.i("QUERY","WKWKWKW")
                return true
            }
        }

        searchView.setOnQueryTextListener(queryTextListener)

        super.onCreateOptionsMenu(menu, inflater)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        when(id) {
            R.id.app_bar_search -> return false
            else ->  {

            }
        }
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onOptionsItemSelected(item)
    }
}
