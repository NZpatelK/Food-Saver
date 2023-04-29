package com.example.foodsaver

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
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


/**
 * This activity page is designed to display a list of items that have the same expiration date.
 */
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

        @Suppress("DEPRECATION") val listOfProducts = intent.getSerializableExtra("productsList") as List<*>

        val fab = findViewById<ExtendedFloatingActionButton>(R.id.floating_action)

        // The click listener for this floating action button will navigate
        // the user to the page for adding a new item when the button is clicked.
        fab.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(this,  fab,  fab.transitionName )
            startActivity(intent, option.toBundle())
        }

        val prodRecyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
        prodRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            @Suppress("UNCHECKED_CAST")
            adapter = ItemAdapter(listOfProducts as List<Item>)
        }

        scrollListener(prodRecyclerView, fab)


    }

    //This is the back button function to return to the previous page.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                @Suppress("DEPRECATION")
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // This scroll listener function controls the scroll and floating action button size
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

                // At the Bottom
                if (!recyclerView.canScrollVertically(1)) {
                    fab.visibility = View.GONE
                }
            }
        })

    }

    //This is animation and transition function.
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