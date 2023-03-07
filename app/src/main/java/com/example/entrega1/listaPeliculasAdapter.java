package com.example.entrega1;

import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class listaPeliculasAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private Activity activity;
    private ArrayList<String> ids=new ArrayList<>();
    private ArrayList<String> titulos=new ArrayList<>();
    private ArrayList<Integer> anios=new ArrayList<>();
    private ArrayList<String> urls=new ArrayList<>();
    private ArrayList<Float> valoraciones=new ArrayList<>();
    private ArrayList<String> descripciones=new ArrayList<>();

    LinearLayout l_lp;
    public listaPeliculasAdapter(Activity activity, Activity context, ArrayList<String> id, ArrayList<String> titulo, ArrayList<Integer> anio, ArrayList<String> url, ArrayList<Float> valoracion, ArrayList<String> descripcion) {
        super(context, R.layout.listapeliculas, titulo);

        this.activity=activity;
        this.context=context;
        this.ids=id;
        this.titulos=titulo;
        this.anios=anio;
        this.urls=url;
        this.valoraciones=valoracion;
        this.descripciones=descripcion;

    }

    public View getView(int p,View view,ViewGroup parent) {
        LayoutInflater inf=context.getLayoutInflater();
        View l=inf.inflate(R.layout.listapeliculas, parent,false);

        TextView t_titulo = l.findViewById(R.id.lp_tv_titulo);
        TextView t_anio= l.findViewById(R.id.lp_tv_anio);
        ImageView t_url= l.findViewById(R.id.lp_iv_portada);
        RatingBar r_valoracion= l.findViewById(R.id.lp_rb_valoracion);
        TextView t_descripcion = l.findViewById(R.id.lp_tv_descripcion);
        l_lp= l.findViewById(R.id.lp_l_pelicula);

        t_titulo.setText(String.valueOf(titulos.get(p)));
        t_anio.setText(String.valueOf(anios.get(p)));
        Glide.with(this.context).load(urls.get(p)).into(t_url);
        r_valoracion.setRating(valoraciones.get(p));
        t_descripcion.setText(String.valueOf(descripciones.get(p)));
        l_lp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,editar_pelicula.class);
                intent.putExtra("id",String.valueOf(ids.get(p)));
                intent.putExtra("titulo",String.valueOf(titulos.get(p)));
                intent.putExtra("anio",String.valueOf(anios.get(p)));
                intent.putExtra("url",String.valueOf(urls.get(p)));
                intent.putExtra("valoracion",String.valueOf(valoraciones.get(p)));
                intent.putExtra("descripcion",String.valueOf(descripciones.get(p)));
                activity.startActivityForResult(intent,1);
            }
        });

        return l;
    };
}