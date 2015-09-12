package com.cibertec.grupo7.pizzahut;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.cibertec.grupo7.pizzahut.adapter.reciclerview.RVLocales;
import com.cibertec.grupo7.pizzahut.adapter.spinner.SPUbigeos;
import com.cibertec.grupo7.pizzahut.dao.DataBaseHelper;
import com.cibertec.grupo7.pizzahut.dao.ProvinciasDao;
import com.cibertec.grupo7.pizzahut.entities.Locales;
import com.cibertec.grupo7.pizzahut.entities.Provincias;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RVLocales.RVLocalesAdapterCallBack {

    private SPUbigeos mSPUbigeos;
    private ArrayList<Provincias> lstProvincias, lstDistritos;
    private Spinner spProvincia,spDistrito;
    ProvinciasDao daoProvincia = new ProvinciasDao();
    private DataBaseHelper dataBaseHelper;
    private RecyclerView rvLocales;
    private RVLocales rvLocalesAdapter;
    private String provinciaSelect, distritoSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProvincia = (Spinner) findViewById(R.id.spProvincia);
        spDistrito = (Spinner) findViewById(R.id.spDistrito);
        rvLocales = (RecyclerView) findViewById(R.id.rvLocales);

        try {
            dataBaseHelper = new DataBaseHelper(MainActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        lstProvincias = new ArrayList<>();
        lstProvincias.addAll(daoProvincia.listProvinciasSpn());

        ArrayList<String> lenProvincias = new ArrayList<>();
        lenProvincias.add("Seleccione una Provincia");

        for (Provincias a : lstProvincias
        ){
            lenProvincias.add(a.getProvincia());
        }

        mSPUbigeos = new SPUbigeos(MainActivity.this, lenProvincias);
        spProvincia.setOnItemSelectedListener(spProvinciaOnItemSelectedListener);
        spProvincia.setAdapter(mSPUbigeos);
        spDistrito.setOnItemSelectedListener(spDistritoOnItemSelectedListener);
    }

    AdapterView.OnItemSelectedListener spProvinciaOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){
                Provincias provincias = new Provincias();
                provincias = lstProvincias.get(position-1);
                provinciaSelect = lstProvincias.get(position-1).getProvincia();
                lstDistritos = new ArrayList<>();
                lstDistritos.addAll(daoProvincia.listDistritosSpn(provincias));

                ArrayList<String> lenDistritos = new ArrayList<>();
                lenDistritos.add("Seleccione un Distrito");

                for (Provincias a : lstDistritos
                        ){
                    lenDistritos.add(a.getDistrito());
                }

                mSPUbigeos = new SPUbigeos(MainActivity.this, lenDistritos);
                spDistrito.setAdapter(mSPUbigeos);
            }
            else {
                mSPUbigeos = new SPUbigeos(MainActivity.this, new ArrayList<String>());
                spDistrito.setAdapter(mSPUbigeos);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener spDistritoOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(position != 0){
                distritoSelect = lstDistritos.get(position-1).getDistrito();
                Provincias provincia = new Provincias();
                provincia.setProvincia(provinciaSelect);
                provincia.setDistrito(distritoSelect);
                rvLocalesAdapter = new RVLocales(MainActivity.this,provincia);
                rvLocales.setHasFixedSize(true);
                rvLocales.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvLocales.setAdapter(rvLocalesAdapter);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocalClick(Locales local, int position) {

    }
}
