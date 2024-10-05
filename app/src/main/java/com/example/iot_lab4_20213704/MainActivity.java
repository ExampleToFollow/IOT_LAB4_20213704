package com.example.iot_lab4_20213704;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void ingresar(View view){
        //Validar conexión a internet
        ConnectivityManager  conectividad = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean tieneInternet = false;
        if(conectividad !=null){
            NetworkCapabilities capabilities = conectividad.getNetworkCapabilities(conectividad.getActiveNetwork());
            if(capabilities != null){
                Intent intent = new Intent(this,  AppActivity.class);
                startActivity(intent);
            }else{
                //Se muestra dialog
                showAlertDialog();
            }
        }
    }

    private void showAlertDialog() {
        // Crear un AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Establecer el título, mensaje y botones
        builder.setTitle("No hay conexión a internet");
        builder.setMessage("Conectate a internet");

        // Botón "Aceptar"
        builder.setPositiveButton("Configuración ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(intent);
            }
        });

        // Mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}