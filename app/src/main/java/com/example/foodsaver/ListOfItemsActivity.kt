package com.example.foodsaver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.color.MaterialColors
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class ListOfItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.activity_list_of_items)

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = buildTransitions()
        window.sharedElementExitTransition = buildTransitions()
        window.sharedElementReenterTransition = buildTransitions()

        val toolbar = findViewById<MaterialToolbar>(R.id.Toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "List of the Items"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)

        val ListOfProducts = intent.getSerializableExtra("productsList") as List<Item>

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.floating_action)

        fab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this,  fab,  fab.transitionName )
            startActivity(intent, option.toBundle())
        }

        if (ListOfProducts != null) {
            val prodRecyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
            prodRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = ItemAdapter(ListOfProducts)
            }

            scrollListener(prodRecyclerView, fab)

        } else {
            // Handle the case when name is null or not of type List<Items>
        }


    }

    private fun scrollListener(prodRecyclerView: RecyclerView, fab: ExtendedFloatingActionButton) {

        prodRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // Scroll down
                if (dy > 20 && fab.isExtended) {
                    fab.shrink()
                    fab.visibility = View.VISIBLE
                }

                // Scroll up
                if (dy < -20 && !fab.isExtended) {
                    fab.extend()
                    fab.visibility = View.VISIBLE
                }

                // At the top
                if (!recyclerView.canScrollVertically(-1)) {
                    fab.extend()
                    fab.visibility = View.VISIBLE
                }

                // At the top
                if (!recyclerView.canScrollVertically(1)) {
                    fab.visibility = View.GONE
                }
            }
        })

    }

    private fun buildTransitions(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            addTarget(R.id.list_item_container)
            setAllContainerColors(MaterialColors.getColor(findViewById(R.id.list_item_container), com.google.android.material.R.attr.colorSurface))
            duration = 600
            pathMotion = MaterialArcMotion()
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
        }

    }
}