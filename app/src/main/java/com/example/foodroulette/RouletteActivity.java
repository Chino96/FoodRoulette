package com.example.foodroulette;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.ChartHighlighter;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;
import java.util.Random;


public class RouletteActivity extends AppCompatActivity {
    Random random;
    ImageView wheel;
    ArrayList<String> names;
    TextView restaurantView;
    String selected;
    PieChart pieChart;

    private static final float FACTOR =4.86f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        names = bundle.getStringArrayList("selected");

        pieChart = findViewById(R.id.piechart);
        pieChart.setRotationAngle((float)(360 - (360/names.size())));
        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(60f);


        addDataSet(names, pieChart);

        Button btnStart = findViewById(R.id.btnStart);
        restaurantView = findViewById(R.id.restaurantView);
        restaurantView.setText("");

        random = new Random();
        btnStart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int rand = random.nextInt(names.size());
                selected = names.get(rand);
                pieChart.spin(7000, (float)(360 - (360/names.size())), (float)((360 - (360/names.size()) + ((rand*60)) + 3600)), Easing.EaseInOutCirc);
                int i = 0;
                restaurantView.setText(rand+"");

            }

        });


    }

    private void addDataSet(ArrayList<String> names, PieChart pieChart) {

        ArrayList<PieEntry> yEntries = new ArrayList<>();

        for(int i=names.size()-1;i>=0;i--){
            yEntries.add(new PieEntry(1, i+""));
        }

        PieDataSet pieDataSet = new PieDataSet(yEntries,"Food Choices");


        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(0);
        pieDataSet.setHighlightEnabled(true);



        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(Color.RED);
        colors.add(Color.BLACK);
        pieDataSet.setColors(colors);

        PieData pd = new PieData(pieDataSet);

        pieChart.setData(pd);
        pieChart.invalidate();

    }
}
