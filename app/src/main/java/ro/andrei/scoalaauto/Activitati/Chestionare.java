package ro.andrei.scoalaauto.Activitati;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import ro.andrei.scoalaauto.Clase.ChestionarJSON;
import ro.andrei.scoalaauto.Clase.DbAdapter;

public class Chestionare extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout layout;
    private ShapeDrawable shape;
    private Boolean schimbat=false;

    private ArrayList<Integer> lista = new ArrayList<>();

    public Integer i=0;
    public Integer raspunsuriCorecte=0;
    public Integer raspunsuriGresite=0;

    public Button btnVariantaA;
    public Button btnVariantaB;
    public Button btnVariantaC;
    public Button btnTrimiteRaspuns;
    public TextView tvCorecte;
    public TextView tvGresite;

    public ChestionarJSON chestionarPrecedent;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chestionare);

        lista=genereazaElemente();
        username=this.getIntent().getStringExtra("username");

        tvCorecte=(TextView)findViewById(R.id.corecte);
        tvGresite= (TextView) findViewById(R.id.gresite);
        btnVariantaA=(Button)findViewById(R.id.butonA);
        btnVariantaB=(Button)findViewById(R.id.butonB);
        btnVariantaC=(Button)findViewById(R.id.butonC);
        btnTrimiteRaspuns=(Button)findViewById(R.id.next);

        btnVariantaA.setOnClickListener(this);
        btnVariantaB.setOnClickListener(this);
        btnVariantaC.setOnClickListener(this);
        btnTrimiteRaspuns.setOnClickListener(this);

        chestionarPrecedent=new ChestionarJSON();
        invisibile();

        tvCorecte.setTextColor(getResources().getColor(R.color.verdeChestionar));
        tvGresite.setTextColor(getResources().getColor(R.color.rosuChestionar));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.butonA: {
                if (btnVariantaA.isActivated()) {
                    btnVariantaA.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaA.setActivated(false);
                } else {
                    btnVariantaA.setBackgroundColor(Color.YELLOW);
                    btnVariantaA.setActivated(true);
                }
                break;
            }

            case R.id.butonB: {
                if (btnVariantaB.isActivated()) {
                    btnVariantaB.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaB.setActivated(false);
                } else {
                    btnVariantaB.setBackgroundColor(Color.YELLOW);
                    btnVariantaB.setActivated(true);
                }
                break;
            }

            case R.id.butonC: {
                if (btnVariantaC.isActivated()) {
                    btnVariantaC.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaC.setActivated(false);
                } else {
                    btnVariantaC.setBackgroundColor(Color.YELLOW);
                    btnVariantaC.setActivated(true);
                }
                break;
            }

            case R.id.next:{

                if(btnTrimiteRaspuns.getText().equals("START")) {
                    JSON obj=new JSON();
                    obj.execute(lista.get(i));
                    i++;
                    visibile();
                    btnTrimiteRaspuns.setText("NEXT");
                }
                else {
                    if(i<26 && raspunsuriGresite!=4)
                    {
                        JSON obj = new JSON();
                        obj.execute(lista.get(i));

                    }else if(raspunsuriGresite==4){
                        setContentView(R.layout.chestionarfinal);
                        tvCorecte.setText(raspunsuriCorecte.toString());
                        tvGresite.setText(raspunsuriGresite.toString());

                        final DbAdapter adapter=new DbAdapter(this);
                        adapter.openConnection();

                        adapter.updateTestePicate(username);
                        TextView raspCorecteTV=(TextView)findViewById(R.id.testFinalRC);
                        TextView raspGresiteTV=(TextView)findViewById(R.id.testFinalRG);
                        raspunsuriGresite++;
                        raspCorecteTV.setText(raspunsuriCorecte.toString());
                        raspGresiteTV.setText(raspunsuriGresite.toString());

                        layout = (RelativeLayout) findViewById(R.id.layRez);
                        desenareGrafic(raspunsuriCorecte, raspunsuriGresite);

                        Button backToMenu = (Button) findViewById(R.id.backToMenu);
                        backToMenu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });

                        Button incepeTest=(Button)findViewById(R.id.testFinalBtn);
                        incepeTest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), Chestionare.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            }
                        });
                    }

                    else {
                        Boolean vA = false;
                        Boolean vB = false;
                        Boolean vC = false;
                        if (btnVariantaA.isActivated())
                            vA = true;
                        if (btnVariantaB.isActivated())
                            vB = true;
                        if (btnVariantaC.isActivated())
                            vC = true;

                        if (chestionarPrecedent.getVariantaA().equals(vA) &&
                                chestionarPrecedent.getVariantaB().equals(vB) &&
                                chestionarPrecedent.getVariantaC().equals(vC))
                            raspunsuriCorecte++;
                        else
                            raspunsuriGresite++;

                        tvCorecte.setText(raspunsuriCorecte.toString());
                        tvGresite.setText(raspunsuriGresite.toString());

                        final DbAdapter adapter=new DbAdapter(this);
                        adapter.openConnection();

                        if(raspunsuriCorecte>21)
                        {
                            adapter.updateTesteTrecute(username);
                        }
                        else
                        {
                            adapter.updateTestePicate(username);
                        }
                        TextView raspCorecteTV=(TextView)findViewById(R.id.testFinalRC);
                        TextView raspGresiteTV=(TextView)findViewById(R.id.testFinalRG);
                        raspCorecteTV.setText(raspunsuriCorecte.toString());
                        raspGresiteTV.setText(raspunsuriGresite.toString());

                        layout = (RelativeLayout) findViewById(R.id.layRez);
                        desenareGrafic(raspunsuriCorecte, raspunsuriGresite);

                        Button backToMenu = (Button) findViewById(R.id.backToMenu);
                        backToMenu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });

                        Button incepeTest=(Button)findViewById(R.id.testFinalBtn);
                        incepeTest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), Chestionare.class);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            }
                        });
                    }
                    i++;
                }
            }
        }
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

    public void deselectare(){
        btnVariantaA.setActivated(false);
        btnVariantaB.setActivated(false);
        btnVariantaC.setActivated(false);

        btnVariantaA.setBackgroundResource(android.R.drawable.btn_default);
        btnVariantaB.setBackgroundResource(android.R.drawable.btn_default);
        btnVariantaC.setBackgroundResource(android.R.drawable.btn_default);
    }

    public void invisibile(){
        findViewById(R.id.slash).setVisibility(View.INVISIBLE);
        btnVariantaA.setVisibility(View.INVISIBLE);
        btnVariantaB.setVisibility(View.INVISIBLE);
        btnVariantaC.setVisibility(View.INVISIBLE);
    }

    public void visibile(){
        findViewById(R.id.slash).setVisibility(View.VISIBLE);
        btnVariantaA.setVisibility(View.VISIBLE);
        btnVariantaB.setVisibility(View.VISIBLE);
        btnVariantaC.setVisibility(View.VISIBLE);
    }

    public ArrayList genereazaElemente() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> listaReturnata=new ArrayList<Integer>();
        for (int i=0; i<49; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        for (int i=0; i<26; i++) {
            listaReturnata.add(list.get(i));
        }
        return listaReturnata;
    }

    public class JSON extends AsyncTask<Integer, Void, ChestionarJSON> {

        private Integer idIntrebare;

        @Override
        protected ChestionarJSON doInBackground(Integer... params) {

            idIntrebare=params[0];
            ChestionarJSON chestionar = new ChestionarJSON();

            try {
                URL url = new URL("https://api.myjson.com/bins/usd9");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                InputStream input = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String text = "";
                String linie;

                while ((linie = reader.readLine()) != null) {
                    text += linie;
                }

                JSONObject json = new JSONObject(text);
                JSONArray intrebari = json.getJSONArray("Chestionare");
                JSONObject element = intrebari.getJSONObject(idIntrebare);
                chestionar.setIntrebare(element.getString("intrebare"));
                chestionar.setRaspunsA(element.getString("raspunsA"));
                chestionar.setRaspunsB(element.getString("raspunsB"));
                chestionar.setRaspunsC(element.getString("raspunsC"));

                if (element.getString("variantaA").equals("TRUE"))
                    chestionar.setVariantaA(true);
                else
                    chestionar.setVariantaA(false);

                if (element.getString("variantaB").equals("TRUE"))
                    chestionar.setVariantaB(true);
                else
                    chestionar.setVariantaB(false);

                if (element.getString("variantaC").equals("TRUE"))
                    chestionar.setVariantaC(true);
                else
                    chestionar.setVariantaC(false);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return chestionar;
        }

        @Override
        protected void onPostExecute(ChestionarJSON chestionarJSON) {
            TextView nrIntrebare=(TextView)findViewById(R.id.intrebareNumar);
            TextView intrebare=(TextView)findViewById(R.id.intrebareChestionar);
            TextView raspunsA= (TextView) findViewById(R.id.raspunsA);
            TextView raspunsB= (TextView) findViewById(R.id.raspunsB);
            TextView raspunsC= (TextView) findViewById(R.id.raspunsC);

            idIntrebare+=1;
            nrIntrebare.setText("Intrebarea #"+i.toString());
            String textIntrebare=chestionarJSON.getIntrebare();
            String rA=chestionarJSON.getRaspunsA();
            String rB=chestionarJSON.getRaspunsB();
            String rC=chestionarJSON.getRaspunsC();

            intrebare.setText(textIntrebare);
            raspunsA.setText("A. " + rA);
            raspunsB.setText("B. " + rB);
            raspunsC.setText("C. " + rC);

            if(i>1) {
                Boolean vA = false;
                Boolean vB = false;
                Boolean vC = false;
                if (btnVariantaA.isActivated())
                    vA = true;
                if (btnVariantaB.isActivated())
                    vB = true;
                if (btnVariantaC.isActivated())
                    vC = true;

                if (chestionarPrecedent.getVariantaA().equals(vA) &&
                        chestionarPrecedent.getVariantaB().equals(vB) &&
                        chestionarPrecedent.getVariantaC().equals(vC))
                    raspunsuriCorecte++;
                else
                    raspunsuriGresite++;
            }

            chestionarPrecedent.setVariantaA(chestionarJSON.getVariantaA());
            chestionarPrecedent.setVariantaB(chestionarJSON.getVariantaB());
            chestionarPrecedent.setVariantaC(chestionarJSON.getVariantaC());

            tvCorecte.setText(raspunsuriCorecte.toString());
            tvGresite.setText(raspunsuriGresite.toString());

            deselectare();
            super.onPostExecute(chestionarJSON);
        }
    }
}
