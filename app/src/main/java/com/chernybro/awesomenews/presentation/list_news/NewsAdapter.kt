package com.chernybro.awesomenews.presentation.list_news

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chernybro.awesomenews.databinding.ItemArticlePreviewBinding
import com.chernybro.awesomenews.domain.models.ArticlePreview
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation


class NewsAdapter()
    : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    private var newsList: List<ArticlePreview> = listOf()

    fun setData(items: List<ArticlePreview>) {
        newsList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding =  ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val articleItem = newsList[position]
        holder.bind(articleItem)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ArticleViewHolder(articleItemView: ItemArticlePreviewBinding)
        : RecyclerView.ViewHolder(articleItemView.root){

        private val binding = articleItemView

        fun bind(articleItem: ArticlePreview){
            binding.articlePreviewDate.text = articleItem.date
            binding.articlePreviewDescription.text = articleItem.description
            binding.articlePreviewSource.text = articleItem.source
            Picasso.get().load(articleItem.image).transform(RoundedCornersTransformation(25,0)).resize(88,72).into(binding.articlePreviewImage)
        }

    }

}