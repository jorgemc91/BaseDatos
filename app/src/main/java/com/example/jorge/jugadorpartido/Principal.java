package com.example.jorge.jugadorpartido;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Principal extends Activity {

    private GestorJugador gj;
    private Adaptador ca;
    private GestorPartido gp;
    private AdaptadorPartido cap;
    private EditText etnombre;
    private EditText ettelefono;
    private EditText etfecha;
    private ListView lv;
    private ListView lvPartido;
    private Long[]id=new Long[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        gj=new GestorJugador(this);
        gp=new GestorPartido(this);
        etnombre = (EditText)findViewById(R.id.etNombre);
        ettelefono = (EditText)findViewById(R.id.etTelefono);
        etfecha = (EditText)findViewById(R.id.etFnac);
        lv = (ListView)findViewById(R.id.listView);
        lvPartido = (ListView)findViewById(R.id.listView2);
    }

    @Override
    protected void onResume() {
        gj.open();
        gp.open();
        super.onResume();
        final ListView lv= (ListView) findViewById(R.id.listView);
        final ListView lvP= (ListView) findViewById(R.id.listView2);
        Cursor c = gj.getCursor(null,null,null);
        Cursor cp = gp.getCursor(null,null,null);
        ca = new Adaptador(this, c);
        cap = new AdaptadorPartido(this, cp);
        lv.setAdapter(ca);
        lvP.setAdapter(cap);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Cursor c = (Cursor) lv.getItemAtPosition(pos);
                Jugador j = GestorJugador.getRow(c);
                gj.delete(j);
                ca.changeCursor(gj.getCursor(null, null, null));
                return false;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Cursor c = (Cursor) lv.getItemAtPosition(pos);
                Jugador j = GestorJugador.getRow(c);
                id[0] = j.getId();
                tostada("La valoración media es de "+gp.valoracionMedia(id[0].toString())+"");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        gj.close();
        gp.close();
    }

    public void alta(View v){
        String nombre,telefono,fecha;
        nombre=etnombre.getText().toString();
        telefono=ettelefono.getText().toString();
        fecha=etfecha.getText().toString();
        Jugador j=new Jugador(nombre,telefono,fecha);
        long id=gj.insert(j);
        ca.getCursor().close();
        ca.changeCursor(gj.getCursor(null,null,null));

        Toast.makeText(this, "Jugador insertado, id:" + id, Toast.LENGTH_SHORT).show();
    }

    public void ver(View v){
        gj.open();
        gp.open();
        Cursor c = gj.getCursor(null,null,null);
        Cursor cp = gp.getCursor(null,null,null);
        ca = new Adaptador(this, c);
        cap = new AdaptadorPartido(this, cp);
        lv= (ListView) findViewById(R.id.listView);
        lvPartido = (ListView) findViewById(R.id.listView2);
        lv.setAdapter(ca);
        lvPartido.setAdapter(cap);
    }

    public void tostada(String mensaje){
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}
