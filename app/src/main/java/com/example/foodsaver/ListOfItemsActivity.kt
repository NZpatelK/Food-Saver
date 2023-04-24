package com.example.foodsaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
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

        super.onCreate(savedInstanceState)

        val ListOfProducts = intent.getSerializableExtra("productsList") as List<Item>

        if (ListOfProducts != null) {
            val prodRecyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
            prodRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = ItemAdapter(ListOfProducts)
            }
        } else {
            // Handle the case when name is null or not of type List<Items>
        }


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