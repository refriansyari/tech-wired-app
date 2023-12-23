package com.refri.techwired.ui.newslist.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.refri.techwired.R
import com.refri.techwired.data.model.response.Article
import com.refri.techwired.databinding.ItemNewsHeadlineBinding
import com.refri.techwired.databinding.ItemNewsListBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class NewsListAdapter(private val itemClick: (Article) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<Article> = mutableListOf()

    fun setItems(items: List<Article>){
        clearItems()
        addItems(items)
        notifyDataSetChanged()
    }

    fun addItems(items: List<Article>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearItems() {
        this.items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FIRST_ITEM -> {
                val binding = ItemNewsHeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FirstItemViewHolder(binding, itemClick)
            }
            VIEW_TYPE_NORMAL_ITEM -> {
                val binding = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NewsListViewHolder(binding, itemClick)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_FIRST_ITEM
        } else {
            VIEW_TYPE_NORMAL_ITEM
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FirstItemViewHolder -> holder.bindView(items[position])
            is NewsListViewHolder -> holder.bindView(items[position])
        }
    }

    override fun getItemCount() : Int{
        return if (items != null && items.isNotEmpty()) {
            items.size
        } else {
            0
        }

    }

    class NewsListViewHolder(private val binding: ItemNewsListBinding,  val itemClick: (Article) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private var date = ""

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindView(item: Article) {
            with(item) {
                binding.clContainer.setOnClickListener { itemClick(this) }

                if (item.image != null){
                    binding.ivShowImage.load(item.image){
                        crossfade(true)
                    }
                } else {
                    binding.ivShowImage.setImageResource(R.drawable.ic_test_image)
                }

                binding.tvTitleArticle.text = item.title
                binding.tvPublisher.text = "- " + item.source!!.name

                date = item.publishedAt.toString()
                val formattedDate = convertDateFormat(date)
                binding.tvPublishDate.text = formattedDate
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun convertDateFormat(input: String): String {
            // Parse the input string into an Instant
            val instant = Instant.parse(input)

            // Convert the Instant to LocalDateTime using a specific time zone (e.g., UTC)
            val zoneId = ZoneId.of("UTC")
            val dateTime = LocalDateTime.ofInstant(instant, zoneId)

            // Format the date in the desired output format
            val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
            val formattedDate = dateTime.format(outputFormatter)

            return formattedDate
        }

    }

    class FirstItemViewHolder(private val binding: ItemNewsHeadlineBinding,  val itemClick: (Article) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        private var date = ""

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindView(item: Article) {
            with(item) {
                binding.clContainer.setOnClickListener { itemClick(this) }

                if (item.image != null){
                    binding.ivHeadlinePicture.load(item.image){
                        crossfade(true)
                    }
                } else {
                    binding.ivHeadlinePicture.setImageResource(R.drawable.ic_test_image)
                }

                binding.tvHeadlineTitle.text = item.title
                binding.tvHeadlineDescription.text = item.description
                binding.tvPublisher.text = "- " + item.source!!.name

                date = item.publishedAt.toString()
                val formattedDate = convertDateFormat(date)
                binding.tvPublishDate.text = formattedDate
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun convertDateFormat(input: String): String {
            // Parse the input string into an Instant
            val instant = Instant.parse(input)

            // Convert the Instant to LocalDateTime using a specific time zone (e.g., UTC)
            val zoneId = ZoneId.of("UTC")
            val dateTime = LocalDateTime.ofInstant(instant, zoneId)

            // Format the date in the desired output format
            val outputFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)
            val formattedDate = dateTime.format(outputFormatter)

            return formattedDate
        }

    }

    companion object {
        private const val VIEW_TYPE_FIRST_ITEM = 1
        private const val VIEW_TYPE_NORMAL_ITEM = 2
    }
}