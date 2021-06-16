package me.mehadih.retrofitlivedatamvvmrecyclerviewdatabinding.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.transition.Slide
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.venkatesh.kardas.R
import com.venkatesh.kardas.activity.PopUpWindow
import com.venkatesh.kardas.model.User
import java.util.*


/**
 * Created By - Mehadi
 * Created On - 2/6/2020 : 1:23 PM
 * Email - hi@mehadih.me
 * Website - www.mehadih.me
 */
class UserAdapter(private val context: Context, private var list: MutableList<User>) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.album_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list.get(position)
        holder.albumTitleTv?.text = user.title

        Log.d("imageurlllll", "" + user.thumbnailUrl)

        holder.itemView.setOnClickListener {
            val user = list.get(position)

            val intent = Intent(holder.itemView.context, PopUpWindow::class.java)
            intent.putExtra("imageUrl", user.url)
            holder.itemView.context.startActivity(intent)
        }


        Glide.with(context)
            .load(user.thumbnailUrl + ".png")
            .error(R.drawable.ic_launcher_background)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    @Nullable e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    // log exception
                    Log.e("TAG", "Error loading image", e)
                    return false // important to return false so the error placeholder can be placed
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(holder.albumIv!!)


    }

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var albumTitleTv: AppCompatTextView? = null
        var albumIv: AppCompatImageView? = null


        init {
            albumTitleTv = view.findViewById(R.id.albumTitleTv)
            albumIv = view.findViewById(R.id.albumIv)

        }

    }

}