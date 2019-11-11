package me.parzibyte.crudsqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CuellodeBotellaActivity extends AppCompatActivity {
    Double c1 = 0.0, c2 = 0.0;
    TextView textView ;
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                return;
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
                textView = (TextView)findViewById(R.id.textView11);
                textView.setText(""+c1);
                Toast.makeText(getApplicationContext(),
                        ""+c1+" -- "+c2, Toast.LENGTH_SHORT).show();
                
            }
        });
    }

}
