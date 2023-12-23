package com.refri.techwired.ui.newslist
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.refri.techwired.R
import com.refri.techwired.base.BaseFragment
import com.refri.techwired.base.Resource
import com.refri.techwired.data.model.response.Article
import com.refri.techwired.databinding.FragmentNewsListBinding
import com.refri.techwired.ui.detail.NewsDetailActivity
import com.refri.techwired.ui.newslist.Adapter.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding, NewsListViewModel>(FragmentNewsListBinding::inflate),
    NewsListContract.View{

    private lateinit var mainAdapter: NewsListAdapter

    override fun setDataAdapter(data: List<Article>?){
        data?.let{ mainAdapter.setItems(it)}
    }

    override fun initView() {
        getData()
        initSwipeRefresh()
    }

    override fun initList(){
        mainAdapter = NewsListAdapter { article ->
            startActivity(NewsDetailActivity.getStartIntent(requireContext(), article ))
        }

        getViewBinding().rvContent.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = this@NewsListFragment.mainAdapter
        }

    }

    override fun initSwipeRefresh(){
        getViewBinding().srlContent.setOnRefreshListener {
            getViewBinding().srlContent.isRefreshing = false
            getData()
        }
    }

    override fun getData(){
       getViewModel().getNewsList()
    }

    override fun observeData() {
       getViewModel().getNewsListLiveData().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    it.data?.let{data ->
                        if (data.isEmpty()) {
                            showError(true, getString(R.string.text_no_item))
                            showContent(false)
                        } else {
                            showContent(true)
                            showError(false, null)
                            initList()
                            setDataAdapter(it.data)
                        }

                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvContent.isVisible = isVisible
    }

    override fun showLoading(isVisible: Boolean) {
       getViewBinding().pbLoading.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvMessage.isVisible = isErrorEnabled
        getViewBinding().tvMessage.text = msg
    }

    companion object {
        val TAG = NewsListFragment::class.java.canonicalName
        private const val EXTRA_CATEGORY = "EXTRA_CATEGORY"
        fun newInstance(category: String): NewsListFragment {
            val args = Bundle()
            args.putString(EXTRA_CATEGORY, category)
            val fragment = NewsListFragment()
            fragment.arguments = args
            return fragment
        }
    }


}