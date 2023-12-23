package com.refri.techwired.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import coil.load
import com.refri.techwired.R
import com.refri.techwired.base.BaseActivity
import com.refri.techwired.data.model.response.Article
import com.refri.techwired.databinding.ActivityNewsDetailBinding
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class NewsDetailActivity : BaseActivity<ActivityNewsDetailBinding, NewsDetailViewModel>(
    ActivityNewsDetailBinding::inflate),
    NewsDetailContract.View {

    override fun initView() {
        val newsDetail = intent.getParcelableExtra<Article>(EXTRA_PARCEL)
        newsDetail?.let { setViewData(it) }
    }


    override fun setViewData(newsDetail : Article) {

        val title = newsDetail.title
        val author = newsDetail.author
        val publishedAt = newsDetail.publishedAt.toString()
        val formattedDate = convertDateFormat(publishedAt)
        val publisher = newsDetail.source?.name
        val desc = newsDetail.description
        val content = newsDetail.content
        val url = newsDetail.url

        getViewBinding().tvHeadlineTitle.text = title
        getViewBinding().tvAuthor.text = author
        getViewBinding().tvPublishDate.text = formattedDate
        getViewBinding().tvPublisher.text = publisher
        getViewBinding().tvHeadlineDescription.text = desc
        getViewBinding().tvContent.text = content

        if (newsDetail.image != null){
            getViewBinding().ivHeadlinePicture.load(newsDetail.image){
                crossfade(true)
            }
        } else {
            getViewBinding().ivHeadlinePicture.setImageResource(R.drawable.ic_test_image)
        }

        getViewBinding().btnOfficialWebsite.setOnClickListener {
            openExternalBrowser(url ?: "")
        }
    }

    override fun openExternalBrowser(url: String) {
        startActivity(this, url)
    }

    @SuppressLint("NewApi")
    override fun convertDateFormat(input: String): String {
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


    companion object {
        private const val EXTRA_PARCEL = "EXTRA_PARCEL"
        private const val EXTRA_URL = "EXTRA_URL"
        val TAG : String = NewsDetailActivity::class.java.simpleName

        fun getStartIntent(context: Context?, newsListResponse: Article): Intent {
            return Intent(context, NewsDetailActivity::class.java)
                .putExtra(EXTRA_PARCEL, newsListResponse)
        }

        @JvmStatic
        fun startActivity(context: Context?, url: String?) {
            val openUrl = Intent(Intent.ACTION_VIEW)
            openUrl.data = Uri.parse(url)
            openUrl.putExtra(EXTRA_URL, url)
            context?.startActivity(openUrl)
        }
    }


}