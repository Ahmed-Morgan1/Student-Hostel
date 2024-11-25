package com.example.studenthostel.base

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<TypeItemList, VB : ViewBinding>(private val animationEffect: Int? = null) :
    RecyclerView.Adapter<BaseAdapter<TypeItemList, VB >.ViewHolder>() {

    private var _items: MutableList<TypeItemList> = mutableListOf()
    val items get() = _items.toList()

    inner class ViewHolder(val binding: VB) : RecyclerView.ViewHolder(binding.root)

    abstract fun getBinding(parent: ViewGroup, viewType: Int): VB

    abstract fun bindData(binding: VB, item: TypeItemList, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = getBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _items[position]
        bindData(holder.binding, item!!, position)
        if (animationEffect != null)
            holder.itemView.animation =
                AnimationUtils.loadAnimation(holder.itemView.context, animationEffect)
    }

    override fun getItemCount(): Int = _items.size

    fun addItemToList(item: TypeItemList)  {
        _items.add(item)
        notifyItemChanged(_items.size - 1)
    }

    fun changeData(newItems: List<TypeItemList>) {
        val newDiffUtils = BaseDiffUtils(oldItem = this._items.toMutableList(), newItem = newItems)
        val diff = DiffUtil.calculateDiff(newDiffUtils)
        this._items = newItems.toMutableList()
        diff.dispatchUpdatesTo(this)
    }

    fun removeItem(position: Int) {
        _items.removeAt(position)
        notifyItemChanged(position)
    }

    fun removeItem(item: TypeItemList) {
        val index = _items.indexOf(item)
        if (index != -1)
            _items.removeAt(index)
        return
    }

}