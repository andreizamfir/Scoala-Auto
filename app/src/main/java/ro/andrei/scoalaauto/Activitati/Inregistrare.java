package ro.andrei.scoalaauto.Activitati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ro.andrei.scoalaauto.Clase.DbAdapter;
import ro.andrei.scoalaauto.Clase.Utilizator;

public class Inregistrare extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);
        final Button inregistrarebtn=(Button)findViewById(R.id.InregistrareBtn);
        inregistrarebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inregistrare(v);
            }
        });

    }

    //de adaugat o conditie care sa verifice daca parolele sunt identice(la apasarea butonului)
    public void inregistrare(View viev){
        final DbAdapter adapter=new DbAdapter(this);
        adapter.openConnection();

        EditText nume=(EditText)findViewById(R.id.nume_inregstrare);
        EditText email=(EditText)findViewById(R.id.email_inregistrare);
        EditText username=(EditText)findViewById(R.id.utilizator_inregistrare);
        EditText parola=(EditText)findViewById(R.id.parola_inregistrare);
        EditText parolaReintrodusa = (EditText) findViewById(R.id.reintrodu_parola_inregistrare);

        if(parola.getText().toString().equals(parolaReintrodusa.getText().toString())){
            Utilizator u=new Utilizator(nume.getText().toString(),email.getText().toString(),
                    username.getText().toString(),parola.getText().toString());
            adapter.insert(u);
            Toast.makeText(getApplicationContext(),"Inregistrarea s-a facut cu succes",Toast.LENGTH_LONG).show();
            adapter.closeConnection();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Parolele nu sunt la fel!", Toast.LENGTH_SHORT).show();

    }


}
