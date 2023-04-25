package com.example.foodsaver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.color.MaterialColors
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        setContentView(R.layout.activity_add_item)

        val toolbar = findViewById<MaterialToolbar>(R.id.Toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Add New Item"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())

        window.sharedElementEnterTransition = buildTransitions()
        window.sharedElementExitTransition = buildTransitions()
        window.sharedElementReenterTransition = buildTransitions()

        val button = findViewById<MaterialButton>(R.id.date_picker_actions)
        val submit = findViewById<MaterialButton>(R.id.submit)
        val input = findViewById<TextInputEditText>(R.id.inputValue)
        var date = ""


        button.setOnClickListener {
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

            materialDatePicker.addOnPositiveButtonClickListener { selection ->
                date = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(selection))
            }
            materialDatePicker.show(supportFragmentManager, "tag")
        }

        submit.setOnClickListener{
            val newItem = Item(UUID.randomUUID().toString(), input.text.toString(),"http://dummyimage.com/100x100.png/ff4444/ffffff", date )
            ItemDataHolder.insertItem(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy")), newItem)

            finish()


        }

        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun buildTransitions(): MaterialContainerTransform {
        return MaterialContainerTransform().apply {
            addTarget(R.id.container)
            setAllContainerColors(MaterialColors.getColor(findViewById(R.id.container), com.google.android.material.R.attr.colorSurface))
            duration = 400
            pathMotion = MaterialArcMotion()
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
        }

    }
}