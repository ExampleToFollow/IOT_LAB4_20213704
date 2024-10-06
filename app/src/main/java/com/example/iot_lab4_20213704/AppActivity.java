package com.example.iot_lab4_20213704;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_app);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Seteamos le fragmento en la vista

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_total);
        NavController controller = navHostFragment.getNavController();
        ((Button) findViewById(R.id.ligas)).setOnClickListener(view2->{
            controller.navigate(R.id.fragment_lista);
        });
        ((Button) findViewById(R.id.posiciones)).setOnClickListener(view2->{
            controller.navigate(R.id.fragment_posiciones);
        });

        ((Button) findViewById(R.id.resultados)).setOnClickListener(view2->{
            controller.navigate(R.id.fragment_resultados);
        });


    }


    //Primer intento de appActivity
    public void ligas(View view){
        Fragment miFragmento = new ListaFragment();  // Reemplaza con tu propio fragmento
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmento_total, miFragmento);
        fragmentTransaction.commit();
    }

    public void resultados(View view){
        Fragment miFragmento = new ResultadosFragment();  // Reemplaza con tu propio fragmento
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmento_total, miFragmento);
        fragmentTransaction.commit();
    }
    public void posiciones(View view){
        Fragment miFragmento = new PosicionesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmento_total, miFragmento);
        fragmentTransaction.commit();
    }



















}