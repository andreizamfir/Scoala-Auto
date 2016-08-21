package ro.andrei.scoalaauto.Activitati;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ro.andrei.scoalaauto.Clase.DbAdapter;
import ro.andrei.scoalaauto.Clase.Utilizator;

public class Rezultate extends AppCompatActivity {
    private RelativeLayout layout;
    private ShapeDrawable shape;
    private Boolean schimbat=false;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultate);
        username=this.getIntent().getStringExtra("username");
        //Aici trebuie sa facem un select in care sa dam ca nume ,cel cu care ne-am logat

        final DbAdapter adapter=new DbAdapter(this);
        adapter.openConnection();
        Utilizator user=adapter.selectDupaUSERNAME(username);

        setTitle(R.string.rezultate);
        final TextView testeTrecute=(TextView)findViewById(R.id.testeTrecute);
        final TextView testePicate=(TextView)findViewById(R.id.testePicate);


        final int trecute = user.getTesteTrecute();
        final int picate = user.getTestePicate();

        testeTrecute.setText(String.valueOf(trecute));
        testePicate.setText(String.valueOf(picate));

        adapter.openConnection();
        Button btnReseteaza=(Button)findViewById(R.id.btnReseteaza);
        btnReseteaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.resetTestePicate(username);
                adapter.resetTesteTrecute(username);
                finish();
                startActivity(getIntent());
            }
        });

        Button btnSchimba = (Button) findViewById(R.id.btnSchimba);
        btnSchimba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desenarePieChart(trecute, picate);
            }
        });
        layout = (RelativeLayout) findViewById(R.id.layoutRezultate);

        desenareGrafic(trecute, picate);
    }

    private void loadActivity(){

    }

    private void desenareGrafic(int testeTrecute, int testePicate){
        float total = testeTrecute + testePicate;
        float corecteProcent = (testeTrecute*100)/total;
        float gresiteProcent = (testePicate*100)/total;

        Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);

        Paint p1 = new Paint();
        p1.setColor(Color.parseColor("#CD5C5C"));
        Paint p2 = new Paint();
        p2.setColor(Color.parseColor("#4CAF50"));
        Paint text = new Paint();
        text.setColor(Color.parseColor("#4E342E"));
        text.setTextSize(30);

        canvas.drawRect(170, 550, 220, 550 - corecteProcent * 5, p2);
        canvas.drawRect(260, 550, 310, 550 - gresiteProcent * 5, p1);
        canvas.drawText(String.valueOf(testeTrecute), 185, 545, text);
        canvas.drawText(String.valueOf(testePicate), 275, 545, text);

        layout.setBackgroundDrawable(new BitmapDrawable(bg));
    }

    private void desenarePieChart(int testeTrecute, int testePicate){
        if(schimbat==false) {
            Bitmap bg = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bg);

            float corecte, gresite;
            float suma = testeTrecute + testePicate;
            float pozitie = 0;

            corecte = (testeTrecute*360)/suma;
            gresite = (testePicate*360)/suma;

            shape = new ShapeDrawable(new ArcShape(pozitie, corecte));
            pozitie += corecte;
            shape.setBounds(50, 100, 400, 500);
            shape.getPaint().setColor(Color.parseColor("#4CAF50"));
            shape.draw(canvas);

            shape = new ShapeDrawable(new ArcShape(pozitie, gresite));
            pozitie += gresite;
            shape.setBounds(50, 100, 400, 500);
            shape.getPaint().setColor(Color.parseColor("#CD5C5C"));
            shape.draw(canvas);

            Paint text = new Paint();
            text.setColor(Color.parseColor("#4E342E"));
            text.setTextSize(50);

           /* canvas.drawText(String.valueOf(testeTrecute), 250, 400, text);
            canvas.drawText(String.valueOf(testePicate), 150, 220, text);
*/
            layout.setBackgroundDrawable(new BitmapDrawable(bg));
            schimbat=true;
        }
        else {
            desenareGrafic(testeTrecute, testePicate);
            schimbat = false;
        }
    }
}
