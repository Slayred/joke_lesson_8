package com.example.joke_lesson_8.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.domain.interfaces.FavoriteItemClickListener
import com.example.joke_lesson_8.model.BaseCommunication
import com.example.joke_lesson_8.presentation.*
import com.example.joke_lesson_8.presentation.viewModels.BaseViewModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<V: BaseViewModel<T>,T>: Fragment() {

    private lateinit var viewModel: BaseViewModel<T>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("BaseFragmentTag", "onCreatedView() ${javaClass.simpleName}")
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

   // protected abstract fun getViewModel(app: JokeApp): BaseViewModel<T>

    //protected abstract fun getCommunication(app: JokeApp): BaseCommunication<T>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            (requireActivity().application as JokeApp).viewModelsFactory
        ).get(getViewModelClass())
    }

    protected abstract fun getViewModelClass(): Class<V>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as JokeApp
        //val viewModel = getViewModel(application)
        //val communication = getCommunication(application)

        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataViewFragment)
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycleViewFragment)

        favoriteDataView.linkWith(viewModel)

        viewModel.observe(this) {
            state -> favoriteDataView.show(state)
        }
        val adapter = CommonDataRecyclerAdapter(object: FavoriteItemClickListener<T>{
            override fun changeId(id: T) {
                Snackbar.make(
                    favoriteDataView, R.string.remove_from_favorites,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.yes){
                    viewModel.changeItemStatus(id)
                }.show()
            }

        }, viewModel.communication)

        recyclerView.adapter = adapter
        viewModel.getItemList()
        viewModel.observeList(this) {
            adapter.update()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BaseFragmentTag", "onDestroy() ${javaClass.simpleName}")
    }

    @StringRes
    protected abstract fun checkBoxText(): Int

    @StringRes
    protected abstract fun actionButtonText(): Int
}