package com.example.joke_lesson_8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.data.CommonDataModel


class MainActivity : AppCompatActivity() {

    private lateinit var baseViewModel: BaseViewModel
    private lateinit var quoteViewModel: BaseViewModel
    private lateinit var recycleView: RecyclerView
    private lateinit var viewModel: BaseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseViewModel = (application as JokeApp).baseViewModel
        quoteViewModel = (application as JokeApp).quoteViewModel
        viewModel = (application as JokeApp).baseViewModel

        recycleView = findViewById(R.id.recycleView)

        val adapter = CommonDataRecyclerAdapter()

        recycleView.adapter = adapter

        viewModel.observeList(this, {list ->
            adapter.show(list)
        })
        viewModel.getItemList()


        val favoriteDataView = findViewById<FavoriteDataView>(R.id.showJoke)
        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.showQuote)



        quoteFavoriteDataView.linkWith(quoteViewModel)
        quoteViewModel.observe(this, {
            state ->
            quoteFavoriteDataView.show(state)
        })


        favoriteDataView.linkWith(baseViewModel)
        baseViewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })

//region oldListinerForFavoriteDataView
//        favoriteDataView.listenChanges { isChecked -> mainViewModel.chooseFavorites(isChecked) }
//
//        favoriteDataView.handleActionButton { mainViewModel.getJoke() }
//
//        favoriteDataView.handleChangeButton { mainViewModel.changeJokeStatus() }
//endregion





    }

}