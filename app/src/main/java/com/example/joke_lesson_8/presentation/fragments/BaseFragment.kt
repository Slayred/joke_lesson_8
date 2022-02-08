package com.example.joke_lesson_8.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.interfaces.Communication
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.BaseViewModel
import com.example.joke_lesson_8.presentation.FavoriteDataView
import com.example.joke_lesson_8.presentation.JokeApp

abstract class BaseFragment<T>: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    protected abstract fun getViewModel(app: JokeApp): BaseViewModel<T>

    protected abstract fun getCommunication(app: JokeApp): BaseCommunication<T>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as JokeApp
        val viewModel = getViewModel(application)
        val communication = getCommunication(application)
        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataViewFragment)
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleViewFragment)


    }

    @StringRes
    protected abstract fun checkBoxText(): Int

    @StringRes
    protected abstract fun actionButtonText(): Int
}