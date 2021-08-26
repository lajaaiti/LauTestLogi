package fr.sam.logintest;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button b;
    ProgressDialog dialog;
    JSONParser parser=new JSONParser();

   // ArrayList<String> testList = new ArrayList<String>();
    int success;
    String Nom, Prenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.mail);
        pass=findViewById(R.id.pass);
        b=findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Login().execute();
            }
        });


    }
    class Login extends AsyncTask<String,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog=new ProgressDialog(MainActivity.this);
            dialog.setMessage("Patientez SVP");
            dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            HashMap<String,String> map=new HashMap<String,String>();

            map.put("email",email.getText().toString());

            map.put("pass",pass.getText().toString());

            JSONObject object=parser.makeHttpRequest("http://82.66.81.89/drfwebpart/API/user.php","POST",map);

            try {
                success = object.getInt("success");
                if(success == 1){
                    JSONArray user = object.getJSONArray("user");
                    for(int i =0;i<user.length();i++){
                        JSONObject u = user.getJSONObject(i);


                         Nom = u.getString("nom");
                         Prenom = u.getString("prenom");

                        //testList.add(u.toString());

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.cancel();

            if(success==1)
            {
                Intent intent = new Intent(MainActivity.this, ProfilUser.class);
                intent.putExtra("n",Nom);
                intent.putExtra("P", Prenom);
                startActivity(intent);

               // AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
               // alert.setMessage("Login done successfully");
              //  alert.setNeutralButton("ok",null);
               // alert.show();

            }
            else
            {

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setMessage("adress email ou mot de passe incorrect !");
                alert.setNeutralButton("ok",null);
                alert.show();
            }

        }
    }
}