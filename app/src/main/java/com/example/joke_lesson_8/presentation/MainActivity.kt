package com.example.joke_lesson_8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.data.CommonDataModel


class MainActivity : AppCompatActivity() {

    private lateinit var baseViewModel: BaseViewModel
//    private lateinit var quoteViewModel: BaseViewModel
    private lateinit var recycleView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        baseViewModel = (application as JokeApp).baseViewModel
//        quoteViewModel = (application as JokeApp).quoteViewModel
        recycleView = findViewById(R.id.recycleView)
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.showJoke)
//        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.showQuote)
//
//        quoteFavoriteDataView.linkWith(quoteViewModel)
//        quoteViewModel.observe(this, {
//                state ->
//            quoteFavoriteDataView.show(state)
//        })


        favoriteDataView.linkWith(baseViewModel)
        baseViewModel.observe(this, { state ->
            favoriteDataView.show(state)
        })

        val adapter = CommonDataRecyclerAdapter()
        recycleView.adapter = adapter
        baseViewModel.observeList(this, {
            list -> adapter.show(list)
        })
        baseViewModel.getItemList()




//        baseViewModel.observe(this, {
//        })



//region oldListinerForFavoriteDataView
//        favoriteDataView.listenChanges { isChecked -> mainViewModel.chooseFavorites(isChecked) }
//
//        favoriteDataView.handleActionButton { mainViewModel.getJoke() }
//
//        favoriteDataView.handleChangeButton { mainViewModel.changeJokeStatus() }
//endregion





    }

}