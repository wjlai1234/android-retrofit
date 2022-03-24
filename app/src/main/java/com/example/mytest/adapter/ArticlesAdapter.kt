package com.example.mytest.adapter




import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.articles.Articles
import com.example.mytest.articles.Result
import com.example.mytest.databinding.RecyclerViewBinding


class ArticlesAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    class ViewHolder(private val binding: RecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result, viewHolder: ViewHolder) {
            binding.result= result
//            binding.ivArticles.setOnClickListener {
//                val intent = Intent(viewHolder.itemView.context, ArticlesDetailsActivity::class.java)
//                intent.putExtra("object", hotel)
//                viewHolder.itemView.context.startActivity(intent)
//            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewBinding.inflate(LayoutInflater.from(viewGroup.context))
        return ViewHolder(binding)

    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.bind(results[position],viewHolder)
    override fun getItemCount() = results.size

}

