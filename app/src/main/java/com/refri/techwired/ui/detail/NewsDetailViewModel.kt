package com.refri.techwired.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(val repository: NewsDetailRepository) : ViewModel() {
//do nothing
}