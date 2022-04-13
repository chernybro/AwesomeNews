package com.chernybro.awesomenews.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chernybro.awesomenews.data.remote.NewsApi
import com.chernybro.awesomenews.data.remote.dto.toArticlePreviewList
import com.chernybro.awesomenews.domain.models.ArticlePreview
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsApi: NewsApi
): ViewModel() {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val itemsData: MutableLiveData<List<ArticlePreview>> = MutableLiveData()

    fun getNewsList(country: String) {
        val disposable: Disposable = newsApi.getNewsByCountry(country)
            .subscribeOn(Schedulers.io())
            .map { newsDTO ->
                newsDTO.toArticlePreviewList()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { articles ->
                    itemsData.postValue(articles)
                },
                onError = {
                    Timber.d("message timber error ${it}")
                })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}