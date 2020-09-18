package com.c1ctech.androidsnackbardemo

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.SnackbarLayout

class SnackBarActivityKotlin : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snackbar)

        //get views by its id
        val btnSimpleSnackbar = findViewById<Button>(R.id.btn_simple_snackbar)
        val btnSnackbarWithAction = findViewById<Button>(R.id.btn_snackbar_with_action)
        val btnCustomSnackbar = findViewById<Button>(R.id.btn_snackbar_with_custom_view)

        //setting listener to each button
        btnSimpleSnackbar.setOnClickListener(this)
        btnSnackbarWithAction.setOnClickListener(this)
        btnCustomSnackbar.setOnClickListener(this)
    }

    override fun onClick(view: View) {

        //get parent view by its id
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        val id: Int = view.getId()
        when (id) {
            R.id.btn_simple_snackbar -> Snackbar.make(constraintLayout, "Simple Snackbar ", Snackbar.LENGTH_LONG).show()
            R.id.btn_snackbar_with_action -> {
                val snackbarWithAction = Snackbar.make(constraintLayout, "Contact removed", Snackbar.LENGTH_LONG)
                val snackbarView = snackbarWithAction.view   //get snackbar root view

                //getting child views of snackbar
                val snackbarText = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView
                val snackbarActionText = snackbarView.findViewById<View>(R.id.snackbar_action) as TextView

                //change snackbar text color
                snackbarText.setTextColor(Color.CYAN)

                //change snackbar action button text color
                snackbarActionText.setTextColor(Color.RED)
                snackbarWithAction.setAction("UNDO") {
                    val snackbar = Snackbar.make(constraintLayout, "The contact is restored", Snackbar.LENGTH_LONG)
                    val snackbarView = snackbar.view

                    //change snackbar background color
                    snackbarView.setBackgroundColor(resources.getColor(R.color.green))
                    val snackbarText = snackbarView.findViewById<View>(R.id.snackbar_text) as TextView

                    //change snackbar text color
                    snackbarText.setTextColor(Color.WHITE)

                    //change snackbar text gravity
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) snackbarText.textAlignment = View.TEXT_ALIGNMENT_CENTER else {
                        snackbarText.gravity = Gravity.CENTER_HORIZONTAL
                    }
                    //show snackbar
                    snackbar.show()
                }
                //show snackbar
                snackbarWithAction.show()
            }
            R.id.btn_snackbar_with_custom_view -> {
                val customSnackBar = Snackbar.make(constraintLayout, "", Snackbar.LENGTH_LONG)
                val layout = customSnackBar.view as SnackbarLayout

                //get created custom layout of snackbar
                val customSnackBarView = layoutInflater.inflate(R.layout.custom_snackbar, findViewById<View>(R.id.custom_snackbar_container) as? ViewGroup)

                //getting views from snackbar custom layout
                val textView = customSnackBarView.findViewById<TextView>(R.id.tv_custom_view)
                val imageView = customSnackBarView.findViewById<ImageView>(R.id.iv_custom_view)

                //set text in snackbar custom layout textview
                textView.text = resources.getText(R.string.no_external_storage_available)
                layout.setPadding(0, 0, 0, 0)

                //add snackbar custom layout
                layout.addView(customSnackBarView, 0)

                //show snackbar
                customSnackBar.show()
            }
        }
    }
}