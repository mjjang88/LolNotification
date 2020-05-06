package com.mjjang.lolnotification.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mjjang.lolnotification.R

@BindingAdapter("logoImage")
fun bindImageFromTeamName(view: ImageView, teamName: String?) {
    if (!teamName.isNullOrEmpty()) {
        val resName : String = "${view.context.packageName}:@drawable/${teamName.toLowerCase()}"
        val resID = view.resources.getIdentifier(resName, null, null)
        view.setImageResource(resID)
    } else {
        view.setImageResource(R.drawable.t1)
    }
}