package com.example.asus.miscontactos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetalleContacto extends AppCompatActivity {


    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros = getIntent().getExtras(); //Este objeto Bundle permite recibir parámetros del MainActivity
        String nombre = parametros.getString(getResources().getString(R.string.pnombre)); //Esto equivale a solo poner el texto nombre
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email = parametros.getString(getResources().getString(R.string.pemail));

        TextView tvNombre = (TextView) findViewById(R.id.tvNombre);
        TextView tvTelefono = (TextView) findViewById(R.id.tvTelefono);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
    }

    public void llamar(View v) {
        String telefono = tvTelefono.getText().toString();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + telefono)));

    }

    public void enviarMail(View v){
        String email=tvEmail.getText().toString();
        Intent emailIntent=new Intent((Intent.ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);  //Con esto decido a quien envío el correo
        emailIntent.setType("message/rfc822");  //Con setType indico en que categoría están las aplicaciones de email
        startActivity(Intent.createChooser(emailIntent,"Email")); //Esto permite escoger que aplicación quiero q abra para mandar correo

    }
}
