package com.example.dailynews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_rows.view.*
import kotlinx.android.synthetic.main.news_rows.view.*

class NewsAdapter(private val listener:NewsitemClicked) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    val arrNews:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.news_rows,parent,false)
        val vh=NewsViewHolder(v)
        //When Clicked on Item
        v.setOnClickListener{
            val fullNews=arrNews[vh.adapterPosition]
            listener.onitemClicked(fullNews)
        }
        //
        return vh
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currItem = arrNews[position]
        holder.titleview.text=currItem.title
        holder.authorview.text=currItem.author
        Glide.with(holder.itemView.context).load(currItem.urlToImage).into(holder.news_img)
    }

    override fun getItemCount(): Int {
        return arrNews.size
    }
    //update news
    fun updateNews(updateNews: ArrayList<News>){
        arrNews.clear()
        arrNews.addAll(updateNews)
        notifyDataSetChanged()
    }
    //

    class NewsViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview){
        val titleview=itemview.textview_row_title
        val authorview=itemview.textview_row_author
        val news_img=itemview.news_img
    }

}
interface NewsitemClicked{
    fun onitemClicked(fullNews:News)
}