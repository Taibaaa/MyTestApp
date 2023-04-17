package com.test.interview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.interview.models.TestModel

class DataViewModel : ViewModel() {

    private val PAGE_SIZE = 50
    private var currentPage = 0

    private val initialItems = mutableListOf<TestModel>()

    private val items = MutableLiveData<List<TestModel>>(emptyList())

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    init {
        generateDummyData()
        loadMoreItems()
    }

    private fun generateDummyData() {
        for (i in 0 until 500) {
            if (i % 2 == 0) {
                initialItems.add(TestModel("String Data ${i + 1}", -1))
            } else {
                initialItems.add(TestModel("Int Data", i+1))
            }
        }
    }

    fun getItems(): LiveData<List<TestModel>> {
        return items
    }

    fun loadMoreItems() {
        val start = currentPage * PAGE_SIZE
        val end = start + PAGE_SIZE

        if (end <= initialItems.size) {
            val newList = mutableListOf<TestModel>()
            newList.addAll(items.value ?: emptyList())
            newList.addAll(initialItems.subList(start, end))
            items.value = newList
            currentPage++
            if (currentPage % 10 == 0) {
                _toastMessage.value = "New data added"
            }
        }
    }
}


