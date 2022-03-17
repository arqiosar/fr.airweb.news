package fr.mastergime.arqioui.frairwebnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import fr.mastergime.arqioui.frairwebnews.R
import fr.mastergime.arqioui.frairwebnews.model.responses.News
import kotlinx.android.synthetic.main.news_item_layout.view.*
import java.lang.Exception

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(), Filterable{

    private var newsList = emptyList<News>()
    var newsListFiltered  = emptyList<News>()
    var image: String = ""

    var onItemClick: ((News) -> Unit)? = null

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(newsListFiltered[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsListFiltered.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.itemView.news_title.text = newsListFiltered[position].title

        image = newsListFiltered[position].picture
        Picasso.get()
            .load("$image").into(holder.itemView.news_image, object : Callback {
                override fun onSuccess() {
                    //holder.itemView.progressbar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    TODO("Not yet implemented")
                }
            })
    }


    fun setData(newList: List<News>) {
        newsList = newList
        newsListFiltered = newsList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                newsListFiltered = if (charString.isEmpty()) newsList else {
                    val filteredList = ArrayList<News>()
                    newsList
                        .filter {
                            (it.title.contains(constraint!!))

                        }
                        .forEach { filteredList.add(it) }
                    filteredList

                }
                return FilterResults().apply { values = newsListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                newsListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<News>
                notifyDataSetChanged()
            }
        }
    }

}