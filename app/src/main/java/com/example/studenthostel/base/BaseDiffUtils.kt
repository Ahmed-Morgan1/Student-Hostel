package com.example.studenthostel.base

import androidx.recyclerview.widget.DiffUtil

class BaseDiffUtils<T>(private val oldItem: List<T>, private val newItem: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldItem.size

    override fun getNewListSize(): Int = newItem.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] === newItem[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldItem[oldItemPosition] === newItem[newItemPosition]
}