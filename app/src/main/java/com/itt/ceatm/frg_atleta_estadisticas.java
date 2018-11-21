package com.itt.ceatm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class frg_atleta_estadisticas extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener {

    private static final String TAG="atleta_estatisticas";
    //Lista de atletas
    public ArrayList<datos_lista_estadisticas> lista_est;
    public RecyclerView rcc_lista_estadisticas;
    public adt_atleta_estadisticas adaptador_lista_estadisticas;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_atleta_estadisticas, container, false);

        rcc_lista_estadisticas = (RecyclerView)view.findViewById(R.id.rcc_atletas_estadisticas);
        LinearLayoutManager linear = new LinearLayoutManager(this.getActivity());
        linear.setOrientation(LinearLayoutManager.VERTICAL);
        rcc_lista_estadisticas.setLayoutManager(linear);
        data();
        iniciar_adaptador_estadisticas();
        return view;
    }


    public static frg_atleta_estadisticas newInstance() {
        Bundle bundle = new Bundle();
        frg_atleta_estadisticas fragment = new frg_atleta_estadisticas();
        fragment.setArguments(bundle);

        return fragment;
    }


    public void data(){
        lista_est = new ArrayList<>();
        //Cantidad de graficas,
        //lista_est.add(new datos_lista_estadisticas("R4",3,50));
        //First graphic
        lista_est.add(new datos_lista_estadisticas("R1",0,40));
        lista_est.add(new datos_lista_estadisticas("R2",1,40));
        lista_est.add(new datos_lista_estadisticas("R3",2,30));
        lista_est.add(new datos_lista_estadisticas("R4",3,50));
        lista_est.add(new datos_lista_estadisticas("R5",4,70));
        lista_est.add(new datos_lista_estadisticas("R6",5,30));

        //Second graphic
        lista_est.add(new datos_lista_estadisticas("Q1",0,50));
        lista_est.add(new datos_lista_estadisticas("Q2",1,30));
        lista_est.add(new datos_lista_estadisticas("Q3",2,20));
        lista_est.add(new datos_lista_estadisticas("Q4",3,70));
        lista_est.add(new datos_lista_estadisticas("Q5",4,50));
        lista_est.add(new datos_lista_estadisticas("Q6",5,40));
    }

    public void iniciar_adaptador_estadisticas(){
        adaptador_lista_estadisticas = new adt_atleta_estadisticas(lista_est);
        rcc_lista_estadisticas.setAdapter(adaptador_lista_estadisticas);
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
