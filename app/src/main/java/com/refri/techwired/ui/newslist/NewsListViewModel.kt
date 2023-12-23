package com.refri.techwired.ui.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.refri.techwired.base.Resource
import com.refri.techwired.data.model.response.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(val repository: NewsListRepository) : ViewModel(),
    NewsListContract.ViewModel {

    private val newsLiveData = MutableLiveData<Resource<List<Article>>>()


    override fun getNewsList() {
        newsLiveData.value = Resource.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getNewsList()
                viewModelScope.launch(Dispatchers.Main) {
                    newsLiveData.value = response.articles.let { Resource.Success(it) }
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    newsLiveData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun getNewsListLiveData(): LiveData<Resource<List<Article>>> = newsLiveData
    override fun logResponse(msg: String?) {}

}