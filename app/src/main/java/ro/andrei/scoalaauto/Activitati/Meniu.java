package ro.andrei.scoalaauto.Activitati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Meniu extends Activity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu);
        setTitle("Scoala Auto Meniu");

        username=this.getIntent().getStringExtra("username");

        Toast.makeText(getApplicationContext(),"Bine ati venit "+username,Toast.LENGTH_LONG).show();
    }

    public void activity_legislatie(View view) {
        Intent intent = new Intent(getApplicationContext(), MeniuLegislatie.class);
        startActivity(intent);
    }

    public void activity_mediu_invatare(View view) {
        Intent intent = new Intent(getApplicationContext(), MediuInvatare.class);
        startActivity(intent);
    }

    public void activity_indicatoare(View view) {
        Intent intent = new Intent(getApplicationContext(), MeniuIndicatoare.class);
        startActivity(intent);
    }

    public void activity_chestionare(View view) {
        Intent intent = new Intent(getApplicationContext(), Chestionare.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }

    public void activity_rezultate(View view) {
        Intent intent = new Intent(getApplicationContext(), Rezultate.class);
        intent.putExtra("username",username);
        startActivity(intent);
    }
}
