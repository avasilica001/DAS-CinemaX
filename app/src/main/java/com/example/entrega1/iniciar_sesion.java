package com.example.entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class iniciar_sesion extends AppCompatActivity {

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        EditText usuario=findViewById(R.id.is_t_usuario);
        EditText contrasenia=findViewById(R.id.is_t_contrasenia);
        Button iniciars=findViewById(R.id.is_b_iniciarsesion);
        Button registrarse=findViewById(R.id.is_b_registrarse);

        iniciars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db=new DBHelper(iniciar_sesion.this);
                Cursor c=db.existeUsuario(usuario.getText().toString().trim());

                if (c.getCount()==0) {
                    Toast.makeText(context,"No se ha encontrado el usuario", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor c2=db.existeUsuarioContrasenia(usuario.getText().toString().trim(),contrasenia.getText().toString().trim());
                    if(c2.getCount()==0){
                        Toast.makeText(context,"Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent intent=new Intent(iniciar_sesion.this, MainActivity.class);
                        iniciar_sesion.this.startActivity(intent);
                    }

                }

            }
        });

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(iniciar_sesion.this, registrarse.class);
                iniciar_sesion.this.startActivity(intent);
            }
        });



    }
}