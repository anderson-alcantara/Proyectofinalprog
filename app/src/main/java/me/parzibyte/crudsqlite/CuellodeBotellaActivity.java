package me.parzibyte.crudsqlite;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewAnimator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CuellodeBotellaActivity extends AppCompatActivity {
    Double c1 = 0.0, c2 = 0.0;
    Double suma = 0.0;
    TextView textView, textView2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuellode_botella);
        Button boton = (Button)findViewById(R.id.boton_botella);
        final Spinner spinner = (Spinner) findViewById(R.id.s1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.s2);
        final ProductoController dbBackend = new ProductoController(CuellodeBotellaActivity.this);
        String[] spinnerLists = dbBackend.llenarSpinner("Procesador");
        String[] spinnerLists2 = dbBackend.llenarSpinner("Tarjeta Gráfica");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CuellodeBotellaActivity.this,android.R.layout.simple_spinner_item, spinnerLists);
        ArrayAdapter<String> spinnerAdapter2 = new ArrayAdapter<String>(CuellodeBotellaActivity.this,android.R.layout.simple_spinner_item, spinnerLists2);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner2.setAdapter(spinnerAdapter2);
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0) {
                    Toast.makeText(getApplicationContext(), "has pulsado " +
                                    parent.getItemAtPosition(position) + "-" + (position - 1),
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Elige una posición valida", Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                c1 = Double.parseDouble(dbBackend.obtenerCalificacion(spinner.getSelectedItem().toString()));
                c2 = Double.parseDouble(dbBackend.obtenerCalificacion(spinner2.getSelectedItem().toString()));
                DecimalFormat df = new DecimalFormat("0.00");
                String res = df.format(((c2-c1)*10));
                Double resta = Double.parseDouble(res);
                textView = (TextView)findViewById(R.id.textView11);
                textView2 = (TextView)findViewById(R.id.textView13);
                if(resta<0){resta = resta * (-1);}
                textView.setText(""+resta+" %");
                if((resta>10.00) &&(c2>c1)) {
                    textView2.setTextColor(Color.RED);
                    textView2.setText("Tu procesador es demasiado débil para esta gráfica");
                }else if((resta<10.00) &&(c2>c1)) {
                    textView2.setTextColor(Color.GREEN);
                    textView2.setText("Tu procesador y tarjeta gráfica trabajan bien");
                }

                if((resta>10.00) &&(c1>c2)) {
                    textView2.setTextColor(Color.RED);
                    textView2.setText("Tu gráfica es demasiado débil para este procesador");
                }else if((resta<10.00) &&(c1>c2)) {
                    textView2.setTextColor(Color.GREEN);
                    textView2.setText("Tu gráfica y procesador trabajan bien");
                }
            }
        });
    }

}
