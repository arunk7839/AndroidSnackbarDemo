package com.c1ctech.androidsnackbardemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SnackbarActivityJava extends AppCompatActivity implements View.OnClickListener {

    private Button btnSimpleSnackbar, btnSnackbarWithAction, btnCustomSnackbar;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        //get parent view by its id
        constraintLayout = findViewById(R.id.constraintLayout);

        //get child views of constraintLayout by its id
        btnSimpleSnackbar = findViewById(R.id.btn_simple_snackbar);
        btnSnackbarWithAction = findViewById(R.id.btn_snackbar_with_action);
        btnCustomSnackbar = findViewById(R.id.btn_snackbar_with_custom_view);

        //setting listener to each button
        btnSimpleSnackbar.setOnClickListener(this);
        btnSnackbarWithAction.setOnClickListener(this);
        btnCustomSnackbar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btn_simple_snackbar:
                Snackbar.make(constraintLayout, "Simple Snackbar ", Snackbar.LENGTH_LONG).show();
                break;

            case R.id.btn_snackbar_with_action:
                Snackbar snackbarWithAction = Snackbar.make(constraintLayout, "Contact removed", Snackbar.LENGTH_LONG);

                //get snackbar root view
                View snackbarView = snackbarWithAction.getView();

                //getting child views of snackbar
                TextView snackbarText = (TextView) snackbarView.findViewById(R.id.snackbar_text);
                TextView snackbarActionText = (TextView) snackbarView.findViewById(R.id.snackbar_action);

                //change snackbar text color
                snackbarText.setTextColor((Color.CYAN));

                //change snackbar action button text color
                snackbarActionText.setTextColor((Color.RED));

                snackbarWithAction.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Snackbar snackbar = Snackbar.make(constraintLayout, "The contact is restored", Snackbar.LENGTH_LONG);

                        View snackbarView = snackbar.getView();

                        //change snackbar background color
                        snackbarView.setBackgroundColor(getResources()
                                .getColor(R.color.green));

                        TextView snackbarText = (TextView) snackbarView.findViewById(R.id.snackbar_text);

                        //change snackbar text color
                        snackbarText.setTextColor((Color.WHITE));

                        //change snackbar text gravity
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                            snackbarText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        else {
                            snackbarText.setGravity(Gravity.CENTER_HORIZONTAL);
                        }
                        //show snackbar
                        snackbar.show();
                    }
                });
                //show snackbar
                snackbarWithAction.show();
                break;

            case R.id.btn_snackbar_with_custom_view:

                Snackbar customSnackBar = Snackbar.make(constraintLayout, "", Snackbar.LENGTH_LONG);

                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) customSnackBar.getView();

                //get created custom layout of snackbar
                View customSnackBarView = getLayoutInflater().inflate(R.layout.custom_snackbar, (ViewGroup) findViewById(R.id.custom_snackbar_container));

                //getting views from snackbar custom layout
                TextView textView = (TextView) customSnackBarView.findViewById(R.id.tv_custom_view);
                ImageView imageView = (ImageView) customSnackBarView.findViewById(R.id.iv_custom_view);

                //set text in snackbar custom layout textview
                textView.setText(getResources().getText(R.string.no_external_storage_available));

                layout.setPadding(0, 0, 0, 0);

                //add snackbar custom layout
                layout.addView(customSnackBarView, 0);

                //show snackbar
                customSnackBar.show();
                break;

        }
    }
}