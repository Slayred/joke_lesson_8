package com.example.joke_lesson_8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.joke_lesson_8.R
import com.example.joke_lesson_8.domain.interfaces.FavoriteItemClickListener
import com.example.joke_lesson_8.presentation.viewPager.PagerAdapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

//    private lateinit var baseViewModel: BaseViewModel<Int>
//    private lateinit var quoteViewModel: BaseViewModel
//    private lateinit var recycleView: RecyclerView
//    private lateinit var adapter: CommonDataRecyclerAdapter<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        viewPager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) {
            tab, position ->
            tab.text = getString(if (position == 0) R.string.jokes else R.string.quotes)
        }.attach()
//        baseViewModel = (application as JokeApp).baseViewModel
//        val jokeCommunication = (application as JokeApp).jokeCommunication
//        recycleView = findViewById(R.id.recycleView)
//        val favoriteDataView = findViewById<FavoriteDataView>(R.id.favoriteDataView)
//        val observer: (t: List<CommonUIModel<Int>>) -> Unit ={
//            list -> adapter.show(list)
//        }


//        favoriteDataView.linkWith(baseViewModel)
//        baseViewModel.observe(this) { state ->
//            favoriteDataView.show(state)
//        }
//
//        adapter = CommonDataRecyclerAdapter(object : FavoriteItemClickListener<Int>{
//            override fun changeId(id: Int) {
//                //baseViewModel.changeItemStatus(it)
//                Snackbar.make(
//                    favoriteDataView,  R.string.remove_from_favorites,
//                    Snackbar.LENGTH_SHORT
//                ).setAction(R.string.yes){
//                    //val deleteItemPosition = baseViewModel.changeItemStatus(id)
//                    baseViewModel.changeItemStatus(id)
//                    //adapter.update(Pair(false, deleteItemPosition))
//                }.show()
//            }
//        }, jokeCommunication)
//        recycleView.adapter = adapter
//        baseViewModel.observeList(this) { list ->
//            adapter.show(list)
//        }
//        baseViewModel.observeList(this, {
//            list -> adapter.show(list)
//        })
//        baseViewModel.getItemList()




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