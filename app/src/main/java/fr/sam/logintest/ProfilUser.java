package fr.sam.logintest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;

public class ProfilUser extends AppCompatActivity {
    TextView nom, prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_user);

        prenom = findViewById(R.id.prenom);
        nom = findViewById(R.id.nom);

        //Bundle extras = getIntent().getExtras();

        //String name = extras.getString("nom");
       // String name1 = extras.getString("prenom");

        Intent in = getIntent();

        String name = in.getStringExtra("n");
        String name1 = in.getStringExtra("P");



       prenom.setText("laurent: " + name1);
       nom.setText("anouar: " + name);


    }
}