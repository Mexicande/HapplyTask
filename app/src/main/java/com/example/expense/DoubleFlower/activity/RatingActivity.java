package com.example.expense.DoubleFlower.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.expense.DoubleFlower.R;

public class RatingActivity extends AppCompatActivity {
    public static void launch(Context context) {
        context.startActivity(new Intent(context, RatingActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }
}