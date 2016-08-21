package ro.andrei.scoalaauto.Activitati;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MeniuLegislatie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meniu_legislatie);
        setTitle(R.string.legislatie);
    }

    public void activity_cap1(View view) {
        Intent it= new Intent(getApplicationContext(),Legislatie.class);
        Button b1= (Button) findViewById(R.id.cap1_id);
        String capitol=b1.getText().toString();
        it.putExtra("numar", "unu");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap2(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b2= (Button) findViewById(R.id.cap2_id);
        String capitol=b2.getText().toString();
        it.putExtra("numar", "doi");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap3(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b3= (Button) findViewById(R.id.cap3_id);
        String capitol=b3.getText().toString();
        it.putExtra("numar", "trei");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap4(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b4= (Button) findViewById(R.id.cap4_id);
        String capitol=b4.getText().toString();
        it.putExtra("numar", "patru");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap5(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b5= (Button) findViewById(R.id.cap5_id);
        String capitol=b5.getText().toString();
        it.putExtra("numar", "cinci");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap6(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b6= (Button) findViewById(R.id.cap6_id);
        String capitol=b6.getText().toString();
        it.putExtra("numar", "sase");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap7(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b7= (Button) findViewById(R.id.cap7_id);
        String capitol=b7.getText().toString();
        it.putExtra("numar", "sapte");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap8(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b8= (Button) findViewById(R.id.cap8_id);
        String capitol=b8.getText().toString();
        it.putExtra("numar", "opt");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap9(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b9= (Button) findViewById(R.id.cap9_id);
        String capitol=b9.getText().toString();
        it.putExtra("numar", "noua");
        it.putExtra("Capitol",capitol);
        startActivity(it);
    }

    public void activity_cap10(View view) {
        Intent it=new Intent(getApplicationContext(),Legislatie.class);
        Button b10= (Button) findViewById(R.id.cap10_id);
        String capitol=b10.getText().toString();
        it.putExtra("numar", "zece");
        it.putExtra("Capitol", capitol);
        startActivity(it);
    }
}
