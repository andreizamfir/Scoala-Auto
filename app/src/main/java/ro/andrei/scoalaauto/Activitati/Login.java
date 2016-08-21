package ro.andrei.scoalaauto.Activitati;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ro.andrei.scoalaauto.Clase.DbAdapter;
import ro.andrei.scoalaauto.Clase.Utilizator;

public class Login extends AppCompatActivity {
    public Utilizator utilizator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public void activity_meniu(View view) {
        EditText numeED=(EditText)findViewById(R.id.idUtilizator);
        String username=numeED.getText().toString();
        EditText parolaED=(EditText)findViewById(R.id.idParola);
        String parola= parolaED.getText().toString();
        final DbAdapter adapter=new DbAdapter(this);
        adapter.openConnection();
        utilizator=adapter.select(username,parola);
        if(utilizator!=null){
            Toast.makeText(getApplicationContext(),utilizator.getNume()+" "+utilizator.getParola()+
                    " "+utilizator.getUsername(),Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), Meniu.class);
            intent.putExtra("username",username);

            startActivity(intent);
        }else
        {
            Toast.makeText(getApplicationContext(),"Nu existi in baza de date",Toast.LENGTH_LONG).show();

            Intent intent=new Intent(getApplicationContext(),Inregistrare.class);
            startActivity(intent);
        }

    }

    public void activity_inregistrare(View view) {
        Intent intent = new Intent(getApplicationContext(), Inregistrare.class);
        startActivity(intent);
    }
}
