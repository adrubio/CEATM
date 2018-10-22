package com.itt.ceatm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class atleta_estadisticas extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG="atleta_estatisticas";

    private LineChart estadisticas_chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_atleta_estadisticas, container, false);
        LinearLayoutManager linear = new LinearLayoutManager(this.getActivity());
        linear.setOrientation(LinearLayoutManager.VERTICAL);

        estadisticas_chart = (LineChart)v.findViewById(R.id.ct_atleta_estadisticas);

//        estadisticas_chart.setOnChartGestureListener(atleta_estadisticas.this);
//        estadisticas_chart.setOnChartValueSelectedListener(atleta_estadisticas.this);

//        estadisticas_chart.setDragEnabled(true);
        estadisticas_chart.getAxisRight().setEnabled(false);
//          estadisticas_chart.getLegend().setEnabled(false);
        estadisticas_chart.getDescription().setEnabled(false);




        //estadisticas_chart.setHighlightPerTapEnabled(true);
//
        ArrayList<Entry> puntuaciones1 = new ArrayList<>();
        ArrayList<Entry> puntuaciones2 = new ArrayList<>();
//        List<Entry> valsComp1 = new ArrayList<Entry>();
//        List<Entry> valsComp2 = new ArrayList<Entry>();

        puntuaciones1.add(new Entry(0,60f));
        puntuaciones1.add(new Entry(1,30f));
        puntuaciones1.add(new Entry(2,50f));
        puntuaciones1.add(new Entry(3,20f));
        puntuaciones1.add(new Entry(4,75f));
        puntuaciones1.add(new Entry(5,10f));


        puntuaciones2.add(new Entry(0,30f));
        puntuaciones2.add(new Entry(1,50f));
        puntuaciones2.add(new Entry(2,50f));
        puntuaciones2.add(new Entry(3,20f));
        puntuaciones2.add(new Entry(4,30f));
        puntuaciones2.add(new Entry(5,70f));

        float promedio_valor = 30f;
        float excelente_valor = 60f;


//        Entry c1e1 = new Entry(0f, 20f); // 0 == quarter 1
//        valsComp1.add(c1e1);
//        Entry c1e2 = new Entry(1f, 30f); // 1 == quarter 2 ...
//        valsComp1.add(c1e2);
//    
//        Entry c2e1 = new Entry(0f, 10f); // 0 == quarter 1
//        valsComp2.add(c2e1);

        LineDataSet set1 = new LineDataSet(puntuaciones1, "Puntuacion 1");
        LineDataSet set2 = new LineDataSet(puntuaciones2, "Puntuacion 2");
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
//        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        //Transparency

        set1.setColor(Color.GRAY);
        //set1.setColors(new int[] { R.color.colorPrimary, R.color.colorPrimario, R.color.color_negro, R.color.colorAccent }, atleta_estadisticas.this.getContext());
//        set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set1.setLineWidth(5f);
        set1.setCircleColor(Color.RED);
        set1.setCircleColorHole(Color.RED);
        set1.setCircleRadius(10f);
        set1.setHighLightColor(Color.GREEN);
        set1.setValueTextSize(14f);
        set1.setValueTextColor(Color.RED);
        set1.setValueTypeface(Typeface.DEFAULT_BOLD);
        set1.setFillAlpha(50);


        //Limit Line Promedio
        LimitLine promedio = new LimitLine(promedio_valor, "Promedio");
        promedio.setLineColor(Color.BLUE);
        promedio.setLineWidth(3f);
        promedio.enableDashedLine(80f, 20f, 1f);
        promedio.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        promedio.setTextSize(12F);
        promedio.setTextColor(Color.BLUE);

        //Limit Line Excalente
        LimitLine excelente = new LimitLine(excelente_valor, "Excelente");
        excelente.setLineColor(Color.BLUE);
        excelente.setLineWidth(3f);
        excelente.enableDashedLine(80f, 20f, 1f);
        excelente.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        excelente.setTextSize(12F);
        excelente.setTextColor(Color.BLUE);


        //Axis
        YAxis izq_Axis = estadisticas_chart.getAxisLeft();
        izq_Axis.removeAllLimitLines();
        izq_Axis.addLimitLine(promedio);
        izq_Axis.addLimitLine(excelente);
        //izq_Axis.enableGridDashedLine(5f,3f,5f);
        izq_Axis.setDrawLimitLinesBehindData(true);
        izq_Axis.setAxisMaximum(80f);
        izq_Axis.setAxisMinimum(10f);




//       ArrayList<LineDataSet> dataSets = new ArrayList<>();
        // use the interface ILineDataSet
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);
        //dataSets.add(set2);
        //dataSets.add(setComp2);


        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4", "Q5", "Q6" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = estadisticas_chart.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Darle margen a los labels de X con respecto al chart
        xAxis.setYOffset(15f);




        LineData data = new LineData(dataSets);
        estadisticas_chart.setData(data);
        //estadisticas_chart.setViewPortOffsets(40f,40f,40f,40f);
        //Margenes
//        estadisticas_chart.setExtraBottomOffset(10f);
//        estadisticas_chart.setExtraLeftOffset(50f);

        estadisticas_chart.getXAxis().setSpaceMin(0.5f);
        //Margen entre el ultimo valor del Xaxis y el rightAxis
        //estadisticas_chart.getXAxis().setSpaceMax(1f);
        estadisticas_chart.getXAxis().setLabelRotationAngle(-45f);
        //estadisticas_chart.getXAxis().setAxisMinimum(0.5f);

        //Maxima cantidad de datos en vista: 2f = 2 datos
        estadisticas_chart.setVisibleXRangeMaximum(3f);

        //Ocultar la grid vertical
        estadisticas_chart.getXAxis().setDrawGridLines(false);
        //Ocultar la grid horizontal
        //estadisticas_chart.getAxisLeft().setDrawGridLines(false);

        //estadisticas_chart.moveViewToX(4);
        //estadisticas_chart.setExtraBottomOffset(-80f);

        estadisticas_chart.invalidate(); // refresh

        return v;
    }


    public static atleta_estadisticas newInstance() {
        Bundle bundle = new Bundle();
        atleta_estadisticas fragment = new atleta_estadisticas();
        fragment.setArguments(bundle);

        return fragment;
    }





    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {



    }

    @Override
    public void onNothingSelected() {

    }
}
