package fr.mastergime.arqioui.frairwebnews.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.mastergime.arqioui.frairwebnews.R
import fr.mastergime.arqioui.frairwebnews.adapter.NewsAdapter
import fr.mastergime.arqioui.frairwebnews.databinding.NewsFragmentLayoutBinding
import fr.mastergime.arqioui.frairwebnews.factory.NewsViewModelFactory
import fr.mastergime.arqioui.frairwebnews.model.responses.News
import fr.mastergime.arqioui.frairwebnews.model.viewmodels.NewsViewModel
import fr.mastergime.arqioui.frairwebnews.repository.RequestRepository
import kotlinx.android.synthetic.main.news_fragment_layout.*

class NewsFragment : Fragment() {

    private lateinit var _newsBinding: NewsFragmentLayoutBinding
    private val newsAdapter by lazy { NewsAdapter() }

    private val newsViewModel: NewsViewModel by viewModels(factoryProducer = {
        NewsViewModelFactory(
            RequestRepository()
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "News"

        _newsBinding = NewsFragmentLayoutBinding.inflate(inflater)
        return _newsBinding.root
    }

   /* override fun OnCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.item_menu, menu)
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerview()
        newsViewModel.getNews()

        newsViewModel.newsResponse.observe(viewLifecycleOwner, Observer { response ->

            if (response.isSuccessful) {
                progressBar2.visibility = View.GONE
                /*response.body()?.let {
                    newsAdapter.setData(it.news)
                }*/

                var list = emptyList<News>()
                for(i in 0 until (response.body()?.news?.size!!)){
                    if(response.body()?.news?.get(i)?.type == "news"){
                        list += response.body()!!.news[i]
                    }
                }

                response.body()?.let {
                    if (it != null) {
                        newsAdapter.setData(list)
                    }
                }

                Toast.makeText(context,response.message(), Toast.LENGTH_LONG).show()
            }

        })

        news_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsAdapter.filter.filter(newText)
                return false
            }

        })

    }

    private fun setupRecyclerview() {
        val itemDecor = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        _newsBinding.recyclerViewNews.adapter = newsAdapter
        _newsBinding.recyclerViewNews.layoutManager = LinearLayoutManager(context)
        _newsBinding.recyclerViewNews.addItemDecoration(itemDecor)

    }

}