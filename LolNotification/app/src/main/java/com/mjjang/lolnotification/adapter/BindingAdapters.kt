package com.mjjang.lolnotification.adapter

import android.widget.CheckBox
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mjjang.lolnotification.R
import com.mjjang.lolnotification.manager.AppPreference

@BindingAdapter("logoImage")
fun bindImageFromTeamName(view: ImageView, teamName: String?) {
    if (!teamName.isNullOrEmpty()) {
        val resName : String = "@drawable/${teamName.toLowerCase()}"
        val resID = view.resources.getIdentifier(resName, null, view.context.packageName)
        view.setImageResource(resID)
    } else {
        view.setImageResource(R.drawable.t1)
    }
}

@BindingAdapter("recomChecked")
fun bindRecomChecked(view: CheckBox, id: String?) {
    if (!id.isNullOrEmpty()) {
        val result = AppPreference.getRecomChecked(id)
        view.isChecked = result == 2
    } else {
        view.isChecked = false
    }
}

@BindingAdapter("notRecomChecked")
fun bindNotRecomChecked(view: CheckBox, id: String?) {
    if (!id.isNullOrEmpty()) {
        val result = AppPreference.getRecomChecked(id)
        view.isChecked = result == 1
    } else {
        view.isChecked = false
    }
}