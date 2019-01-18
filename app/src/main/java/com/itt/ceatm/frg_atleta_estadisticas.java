package com.itt.ceatm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class frg_atleta_estadisticas extends Fragment implements OnChartGestureListener, OnChartValueSelectedListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener, View.OnClickListener{

    private static final String TAG="atleta_estatisticas";
    //Lista de atletas
    public ArrayList<datos_lista_estadisticas> lista_est;
    public RecyclerView rcc_lista_estadisticas;
    TextView tv_atletas_rango_hasta, tv_atletas_rango_desde;
    public adt_atleta_estadisticas adaptador_lista_estadisticas;
    public Button btn_atleta_estadisticas_rango_de, btn_atleta_estadisticas_rango_a;

    public LineChart chart_atleta_estadisticas_puntaje;
    private BarChart  chart_atleta_estadisticas_posicion;

    public ArrayList<Entry> puntuaciones1;
    public ArrayList<BarEntry> posiciones;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_atleta_estadisticas, container, false);

        btn_atleta_estadisticas_rango_de = (Button)view.findViewById(R.id.btn_Atleta_Estadisticas_Desde);
        btn_atleta_estadisticas_rango_a = (Button)view.findViewById(R.id.btn_Atleta_Estadisticas_Hasta);
        tv_atletas_rango_hasta = (TextView)view.findViewById(R.id.tv_Atletas_Rango_Hasta);
        tv_atletas_rango_desde = (TextView)view.findViewById(R.id.tv_Atletas_Rango_Desde);

        btn_atleta_estadisticas_rango_de.setOnClickListener(this);
        btn_atleta_estadisticas_rango_a.setOnClickListener(this);

        LinearLayoutManager linear = new LinearLayoutManager(this.getActivity());
        linear.setOrientation(LinearLayoutManager.VERTICAL);



        //data();

        chart_atleta_estadisticas_puntaje = (LineChart)view.findViewById(R.id.chart_Atleta_Estadisticas_Puntaje);
        chart_atleta_estadisticas_posicion = (BarChart)view.findViewById(R.id.chart_Atleta_Estadisticas_Posicion);

        Iniciar_Grafica_Puntajes();
        Iniciar_Grafica_Posiciones();

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

    public void cargar_valores_Puntajes(){

        puntuaciones1 = new ArrayList<>();
//---------------------------------------------------------------------------------------
        //puntuaciones1.add(new Entry(lista.getCompetencia_Index(),lista.getPuntuacion()));
        puntuaciones1.add(new Entry(0,20f));
        puntuaciones1.add(new Entry(1,30f));
        puntuaciones1.add(new Entry(2,50f));
        puntuaciones1.add(new Entry(3,20f));
        puntuaciones1.add(new Entry(4,75f));
        puntuaciones1.add(new Entry(5,40f));
    }

    public void Iniciar_Grafica_Puntajes(){
        cargar_valores_Puntajes();
        chart_atleta_estadisticas_puntaje.setDragEnabled(true);
        chart_atleta_estadisticas_puntaje.getAxisRight().setEnabled(false);
//          chart_atleta_estadisticas_puntaje.getLegend().setEnabled(false);
        chart_atleta_estadisticas_puntaje.getDescription().setEnabled(false);
        //chart_atleta_estadisticas_puntaje.setHighlightPerTapEnabled(true);

        float promedio_valor = 30f;
        float excelente_valor = 60f;

        LineDataSet set1 = new LineDataSet(puntuaciones1, "Puntuacion 1");


        set1.setColor(Color.GRAY);
        //set1.setColors(new int[] { R.color.colorPrimary, R.color.colorPrimario, R.color.color_negro, R.color.colorAccent }, frg_atleta_estadist   icas.this.getContext());
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

        YAxis yAxis = chart_atleta_estadisticas_puntaje.getAxisLeft();
        yAxis.removeAllLimitLines();
        yAxis.addLimitLine(promedio);
        yAxis.addLimitLine(excelente);
        //izq_Axis.enableGridDashedLine(5f,3f,5f);
        yAxis.enableGridDashedLine(10f,10f,0);
        yAxis.setDrawLimitLinesBehindData(true);

        //Valores maximos y minimos del chart
        yAxis.setAxisMinimum(10);
        yAxis.setAxisMaximum(100);
        //This prevents fot the last label to show
        //yAxis.setGranularity(1f);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1);

        //Formatter------------------------

        final String[] quarters = new String[] { "Q1", "Q2", "Q3", "Q4", "Q5", "Q6" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };

        XAxis xAxis = chart_atleta_estadisticas_puntaje.getXAxis();
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
//            xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Darle margen a los labels de X con respecto al chart
        xAxis.setYOffset(15f);


        LineData data = new LineData(dataSets);
        chart_atleta_estadisticas_puntaje.setData(data);

        chart_atleta_estadisticas_puntaje.getXAxis().setSpaceMin(0.5f);
        //Margen entre el ultimo valor del Xaxis y el rightAxis
        //chart_atleta_estadisticas_puntaje.getXAxis().setSpaceMax(1f);
        chart_atleta_estadisticas_puntaje.getXAxis().setLabelRotationAngle(-45f);

        //Maxima cantidad de datos en vista: 2f = 2 datos
        chart_atleta_estadisticas_puntaje.setVisibleXRangeMinimum(2f);
        //Minimo Yaxis valores (para que no de zoom hasta decimales). 20f = 20 numeros maximo de zoom en el Yaxis
        chart_atleta_estadisticas_puntaje.setVisibleYRangeMinimum(30f, YAxis.AxisDependency.RIGHT);
        //Se posiciona en el ultimo valor de las estadisticas
        chart_atleta_estadisticas_puntaje.moveViewToX(puntuaciones1.size() - 1);

        //Ocultar la grid vertical
        chart_atleta_estadisticas_puntaje.getXAxis().setDrawGridLines(false);
        //Ocultar la grid horizontal
        //chart_atleta_estadisticas_puntaje.getAxisLeft().setDrawGridLines(false);

        chart_atleta_estadisticas_puntaje.invalidate(); // refresh
    }

    public void cargar_valores_Posiciones(){

        posiciones = new ArrayList<>();
//-------------------------------------------------------------------
        //puntuaciones1.add(new Entry(lista.getCompetencia_Index(),lista.getPuntuacion()));
        posiciones.add(new BarEntry(0,1f));
        posiciones.add(new BarEntry(1,2f));
        posiciones.add(new BarEntry(2,5f));
        posiciones.add(new BarEntry(3,3f));
        posiciones.add(new BarEntry(4,3f));
        posiciones.add(new BarEntry(5,1f));
    }


    public void Iniciar_Grafica_Posiciones(){
        cargar_valores_Posiciones();
        chart_atleta_estadisticas_posicion.setDragEnabled(true);
        chart_atleta_estadisticas_posicion.getAxisRight().setEnabled(false);
        chart_atleta_estadisticas_posicion.getLegend().setEnabled(false);
        chart_atleta_estadisticas_posicion.getDescription().setEnabled(false);
        chart_atleta_estadisticas_posicion.setExtraOffsets(0, 10, 0, 0);
        //chart_atleta_estadisticas_posicion.setHighlightPerTapEnabled(true);

        BarDataSet puntajes_set = new BarDataSet(posiciones, "Posiciones");

        puntajes_set.setBarBorderColor(R.color.colorPrimario);
        puntajes_set.setValueFormatter(new MyValueFormatter());

        //Ocultar los valores de cada barra
        //puntajes_set.setDrawValues(false);
//        //puntajes_set.setColors(new int[] { R.color.colorPrimary, R.color.colorPrimario, R.color.color_negro, R.color.colorAccent }, frg_atleta_estadist   icas.this.getContext());
////        puntajes_set.setColors(ColorTemplate.VORDIPLOM_COLORS);
//        puntajes_set.setLineWidth(5f);
//        puntajes_set.setCircleColor(Color.RED);
//        puntajes_set.setCircleColorHole(Color.RED);
//        puntajes_set.setCircleRadius(10f);
//        puntajes_set.setHighLightColor(Color.GREEN);
//        puntajes_set.setValueTextSize(14f);
//        puntajes_set.setValueTextColor(Color.RED);
//        puntajes_set.setValueTypeface(Typeface.DEFAULT_BOLD);
//        puntajes_set.setFillAlpha(50);

//
//      //
        YAxis yAxis = chart_atleta_estadisticas_posicion.getAxisLeft();
//        yAxis.removeAllLimitLines();
//        yAxis.addLimitLine(promedio);
//        yAxis.addLimitLine(excelente);
//        //izq_Axis.enableGridDashedLine(5f,3f,5f);
//        yAxis.enableGridDashedLine(10f,10f,0);
//        yAxis.setDrawLimitLinesBehindData(true);
//
//        //Valores maximos y minimos del chart
            yAxis.setAxisMinimum(0);
            yAxis.setAxisMaximum(10);
        //Obtienen los valores mas altos y bajos de DataSet y se establecen como MAximos y minimos
//        yAxis.setAxisMinValue(puntajes_set.getYMin());
//        yAxis.setAxisMaxValue(puntajes_set.getYMax());

            yAxis.setInverted(true);
        //yAxis.setStartAtZero(false);// Forzar que se vea el 0


//        //This prevents fot the last label to show
          yAxis.setGranularity(1f);
//
//
//        //Formatter------------------------------------------------

        final String[] quarters = new String[] { "J1", "J2", "J3", "J4", "J5", "J6" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            public int getDecimalDigits() {  return 0; }
        };
        //------------------------------------------------
//
            XAxis xAxis = chart_atleta_estadisticas_posicion.getXAxis();
            xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
            xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLUE);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

//        //Darle margen a los labels de X con respecto al chart
            xAxis.setYOffset(15f);
//

//
            BarData puntajes_data = new BarData(puntajes_set);
            chart_atleta_estadisticas_posicion.setData(puntajes_data);

            //Esto es para tipo offset pero horizontal
//        chart_atleta_estadisticas_posicion.getXAxis().setSpaceMin(0.5f);

//        //Margen entre el ultimo valor del Xaxis y el rightAxis
//        //chart_atleta_estadisticas_posicion.getXAxis().setSpaceMax(1f);
//        chart_atleta_estadisticas_posicion.getXAxis().setLabelRotationAngle(-45f);
//
//        //Maxima cantidad de datos en vista: 2f = 2 datos
            chart_atleta_estadisticas_posicion.setVisibleXRangeMinimum(5f);
            chart_atleta_estadisticas_posicion.setVisibleXRangeMaximum(6f);


//        //Minimo Yaxis valores (para que no de zoom hasta decimales). 20f = 20 numeros maximo de zoom en el Yaxis
            chart_atleta_estadisticas_posicion.setVisibleYRangeMinimum(7f, YAxis.AxisDependency.LEFT);

//        //Se posiciona en el ultimo valor de las estadisticas
//        chart_atleta_estadisticas_posicion.moveViewToX(puntuaciones1.size() - 1);
//
//        //Ocultar la grid vertical
          chart_atleta_estadisticas_posicion.getXAxis().setDrawGridLines(false);
        //Ocultar la grid horizontal
        //chart_atleta_estadisticas_posicion.getAxisLeft().setDrawGridLines(false);

        chart_atleta_estadisticas_posicion.invalidate(); // refresh
    }

    public class MyValueFormatter implements IValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0"); // No usar decimales
        }

        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            // write your logic here

            int valor = Math.round(value);
            String sufijo = "";

            //Switch para saber que sufijo ponerle al Label con respecto al valor de posicion
            switch (valor){

                case 1:
                    sufijo = "ro";
                    break;
                case 2:
                    sufijo = "do";
                    break;
                case 3:
                    sufijo = "ro";
                    break;
                case 4:
                    sufijo = "to";
                    break;
                case 5:
                    sufijo = "to";
                    break;
                case 6:
                    sufijo = "ro";
                    break;
                case 7:
                    sufijo = "mo";
                    break;
                case 8:
                    sufijo = "vo";
                    break;
                case 9:
                    sufijo = "no";
                    break;
                case 10:
                    sufijo = "mo";
                    break;
            }

            return mFormat.format(value) + sufijo;
        }
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
//        Toast.makeText(getContext(), "Fecha xd", Toast.LENGTH_SHORT).show();
//
//        tv_atletas_rango_desde.setText(String.format("%d / %d / %d", dayOfMonth, monthOfYear, year));
//
//        switch (view.getId()) {
//            case R.id.btn_Atleta_Estadisticas_Desde:
//                tv_atletas_rango_desde.setText(String.format("%d / %d / %d", dayOfMonth, monthOfYear, year));
//                break;
//
//            case R.id.btn_Atleta_Estadisticas_Hasta:
//                tv_atletas_rango_hasta.setText(String.format("%d / %d / %d", dayOfMonth, monthOfYear, year));
//                break;
//        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        //Prueba de que si llega
        //Toast.makeText(getContext(), "Fecha xd", Toast.LENGTH_SHORT).show();
        monthOfYear++; //Para aumentarle 1, ya que se obtiene un valor del 0 al 11

        switch (view.getTag()) {
            case "Desde":
                tv_atletas_rango_desde.setText(String.format("%d / %d / %d", dayOfMonth, monthOfYear, year));
                break;

            case "Hasta":
                tv_atletas_rango_hasta.setText(String.format("%d / %d / %d", dayOfMonth, monthOfYear, year));
                break;
        }
    }

    @Override
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Atleta_Estadisticas_Desde:
                Calendar now = Calendar.getInstance();

                DatePickerDialog fecha_dialogo_de = DatePickerDialog.newInstance( frg_atleta_estadisticas.this,

                        //Se establece que se muestra un promedio de edad y se pone en medio en mes y el dia para que sea mas facil ir a los extremos (Enero y Diciembre)
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH)// Inital day selection
                );

                fecha_dialogo_de.showYearPickerFirst(true);
                fecha_dialogo_de.setTitle("¿Desde qué fecha?");
                fecha_dialogo_de.show(getActivity().getFragmentManager(), "Desde");;
                fecha_dialogo_de.setAccentColor(getResources().getColor(R.color.colorPrimario));

                //Fecha Minima
                Calendar minimo = Calendar.getInstance();
                minimo.set(2016,0,1);
                fecha_dialogo_de.setMinDate(minimo);

                //Fecha de nacimiento maxima
//                Calendar maximo = Calendar.getInstance();
                fecha_dialogo_de.setMaxDate(Calendar.getInstance());

                break;

            case R.id.btn_Atleta_Estadisticas_Hasta:
                Calendar now2 = Calendar.getInstance();


                DatePickerDialog fecha_dialogo_a = DatePickerDialog.newInstance( frg_atleta_estadisticas.this,

                        //Se establece que se muestra un promedio de edad y se pone en medio en mes y el dia para que sea mas facil ir a los extremos (Enero y Diciembre)
                        now2.get(Calendar.YEAR), // Initial year selection
                        now2.get(Calendar.MONTH), // Initial month selection
                        now2.get(Calendar.DAY_OF_MONTH)// Inital day selection
                );

                fecha_dialogo_a.showYearPickerFirst(true);
                fecha_dialogo_a.setTitle("¿Hasta qué fecha?");
                fecha_dialogo_a.show(getActivity().getFragmentManager(), "Hasta");;
                fecha_dialogo_a.setAccentColor(getResources().getColor(R.color.colorPrimario));

                //Fecha Minima
                Calendar minimo2 = Calendar.getInstance();
                minimo2.set(2016,0,1);
                fecha_dialogo_a.setMinDate(minimo2);

                //Fecha de nacimiento maxima
//                Calendar maximo = Calendar.getInstance();
                fecha_dialogo_a.setMaxDate(Calendar.getInstance());

                break;
        }
    }

}
