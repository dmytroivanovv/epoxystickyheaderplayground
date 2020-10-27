package com.dmytroivanovv.epoxystickyheaderplayground

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.dmytroivanovv.epoxystickyheaderplayground.holder.HeaderVO
import com.dmytroivanovv.epoxystickyheaderplayground.holder.HeaderWidgetModel_
import com.dmytroivanovv.epoxystickyheaderplayground.holder.ItemVO
import com.dmytroivanovv.epoxystickyheaderplayground.holder.ItemWidgetModel_
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var controller: StickyController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val showPositionsBtn: Button = findViewById(R.id.show_positions)
        val shuffleBtn: Button = findViewById(R.id.shuffle)
        val refreshBtn: Button = findViewById(R.id.refresh)
        val recyclerView: EpoxyRecyclerView = findViewById(R.id.recycler_view)

        controller = StickyController()
        val layoutManager =  CustomStickyHeaderLinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.setController(controller)

        var actualItems = ArrayList(items)

        showPositionsBtn.setOnClickListener {
            val text = "First visible item = ${layoutManager.findFirstCompletelyVisibleItemPosition()}, " +
                "Last visible item = ${layoutManager.findLastCompletelyVisibleItemPosition()}"
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
        }

        shuffleBtn.setOnClickListener {
            val random = Random(System.currentTimeMillis())
            actualItems = ArrayList(actualItems)
            actualItems.shuffle(random)
            updateList(
                controller = controller,
                items = actualItems
            )
        }

        refreshBtn.setOnClickListener {
            actualItems = ArrayList(actualItems)
            updateList(
                controller = controller,
                items = ArrayList(actualItems)
            )
        }

        updateList(
            controller = controller,
            items = ArrayList(actualItems)
        )
    }

    private fun updateList(controller: StickyController, items: List<Any>) {
        controller.addItems(items)
        controller.requestModelBuild()
    }

    class StickyController : AsyncEpoxyController() {
        private var items: ArrayList<Any> = ArrayList()

        override fun isStickyHeader(position: Int): Boolean {
            return items.getOrNull(position) is HeaderVO
        }

        fun addItems(items: List<Any>) {
            this.items.clear()
            this.items.addAll(items)
        }

        override fun buildModels() {
            items.forEach { item ->
                when (item) {
                    is HeaderVO -> HeaderWidgetModel_()
                        .id(item.id)
                        .viewObject(item)
                        .addTo(this)

                    is ItemVO -> ItemWidgetModel_()
                        .id(item.id)
                        .viewObject(item)
                        .addTo(this)
                }
            }
        }
    }

    private val items = listOf(
        HeaderVO(
            "header_1",
            "Grocery list 1"
        ),
        ItemVO(
            "item_1",
            "Tomatoes"
        ),
        ItemVO(
            "item_2",
            "Cucumbers"
        ),
        ItemVO(
            "item_3",
            "Romaine Letters"
        ),
        ItemVO(
            "item_4",
            "Beef meat"
        ),
        ItemVO(
            "item_5",
            "Pizza"
        ),
        ItemVO(
            "item_1q",
            "Tomatoes"
        ),
        ItemVO(
            "item_2q",
            "Cucumbers"
        ),
        ItemVO(
            "item_3q",
            "Romaine Letters"
        ),
        ItemVO(
            "item_4q",
            "Beef meat"
        ),
        ItemVO(
            "item_5q",
            "Pizza"
        ),
        HeaderVO(
            "header_2",
            "Grocery list 2"
        ),
        ItemVO(
            "item_6",
            "Salt"
        ),
        ItemVO(
            "item_7",
            "Green pepper"
        ),
        ItemVO(
            "item_8",
            "Red pepper"
        ),
        ItemVO(
            "item_6z",
            "Salt"
        ),
        ItemVO(
            "item_7z",
            "Green pepper"
        ),
        ItemVO(
            "item_8z",
            "Red pepper"
        ),
        ItemVO(
            "item_6m",
            "Salt"
        ),
        ItemVO(
            "item_7m",
            "Green pepper"
        ),
        ItemVO(
            "item_8m",
            "Red pepper"
        ),
        HeaderVO(
            "header_3",
            "Grocery list 3"
        ),
        ItemVO(
            "item_9",
            "Beer"
        ),
        ItemVO(
            "item_10",
            "Snack"
        ),
        ItemVO(
            "item_11",
            "Champagne"
        ),
        ItemVO(
            "item_12",
            "Vodka"
        ),
        ItemVO(
            "item_13",
            "Milk"
        ),
        ItemVO(
            "item_14",
            "Butter"
        )
    )
}