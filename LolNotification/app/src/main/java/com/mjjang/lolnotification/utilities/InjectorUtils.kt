package com.mjjang.lolnotification.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.mjjang.lolnotification.data.AppDatabase
import com.mjjang.lolnotification.data.MatchRepository
import com.mjjang.lolnotification.viewmodels.MatchListViewModelFactory

object InjectorUtils {

    private fun getMatchRepository(context: Context): MatchRepository {
        return MatchRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).matchDao()
        )
    }

    fun provideMatchListViewModelFactory(fragment: Fragment): MatchListViewModelFactory {
        val repository = getMatchRepository(fragment.requireContext())
        return MatchListViewModelFactory(repository, fragment)
    }
}