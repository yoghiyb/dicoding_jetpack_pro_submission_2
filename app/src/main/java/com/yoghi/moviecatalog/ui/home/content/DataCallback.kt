package com.yoghi.moviecatalog.ui.home.content

import com.yoghi.moviecatalog.data.DataModel

interface DataCallback {
    fun onItemClicked(data: DataModel)
}