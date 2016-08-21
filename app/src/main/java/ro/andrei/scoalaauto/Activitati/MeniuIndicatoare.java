package ro.andrei.scoalaauto.Activitati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MeniuIndicatoare extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_indicatoare);
    }

    public void categoria1(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria1);
        intent.putExtra("categoria", "categoria1");
        startActivity(intent);
    }

    public void categoria2(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria2);
        intent.putExtra("categoria","categoria2");
        startActivity(intent);
    }

    public void categoria3(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria3);
        intent.putExtra("categoria","categoria3");
        startActivity(intent);
    }

    public void categoria4(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria4);
        intent.putExtra("categoria","categoria4");
        startActivity(intent);
    }

    public void categoria5(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria5);
        intent.putExtra("categoria","categoria5");
        startActivity(intent);
    }

    public void categoria6(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria6);
        intent.putExtra("categoria","categoria6");
        startActivity(intent);
    }

    public void categoria7(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria7);
        intent.putExtra("categoria","categoria7");
        startActivity(intent);
    }

    public void categoria8(View view){
        Intent intent=new Intent(getApplicationContext(),Indicatoare.class);
        Button b1=(Button)findViewById(R.id.categoria8);
        intent.putExtra("categoria","categoria8");
        startActivity(intent);
    }
}
