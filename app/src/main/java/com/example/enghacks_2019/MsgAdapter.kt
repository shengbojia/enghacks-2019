package com.example.enghacks_2019
/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.enghacks_2019.cache.ExternalMsg
import com.example.enghacks_2019.databinding.ItemMsgBinding

class MsgAdapter(
) : ListAdapter<ExternalMsg, MsgAdapter.MsgHolder>(MsgDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgHolder =
        MsgHolder(
            ItemMsgBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
            )
        )

    override fun onBindViewHolder(holder: MsgHolder, position: Int) {
        val msg = getItem(position)
        holder.apply {
            bind(msg)
            itemView.tag = msg
        }
    }

    class MsgHolder(
        private val binding: ItemMsgBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(msg: ExternalMsg) {
            binding.apply {
                onClickListener = View.OnClickListener {
                    // TODO
                }
                this.msg = msg
                executePendingBindings()
            }
        }
    }
}

private class MsgDiffCallBack: DiffUtil.ItemCallback<ExternalMsg>() {
    override fun areItemsTheSame(oldItem: ExternalMsg, newItem: ExternalMsg): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ExternalMsg, newItem: ExternalMsg): Boolean {
        return oldItem == newItem
    }
}

*/