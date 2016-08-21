package ro.andrei.scoalaauto.Activitati;

import android.graphics.Color;
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

import ro.andrei.scoalaauto.Clase.ChestionarJSON;

public class MediuInvatare extends AppCompatActivity implements View.OnClickListener{

    public Integer i=0;
    public Integer raspunsuriCorecte=0;
    public Integer raspunsuriGresite=0;

    public Button btnVariantaA;
    public Button btnVariantaB;
    public Button btnVariantaC;
    public Button btnTrimiteRaspuns;
    public TextView tvRaspunsuri;
    public TextView tvCorecte;
    public TextView tvGresite;

    public ChestionarJSON chestionarPrecedent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediu_invatare);

        tvCorecte=(TextView)findViewById(R.id.tvCorecte);
        tvGresite= (TextView) findViewById(R.id.tvGresite);
        btnVariantaA=(Button)findViewById(R.id.btnVariantaA);
        btnVariantaB=(Button)findViewById(R.id.btnVariantaB);
        btnVariantaC=(Button)findViewById(R.id.btnVariantaC);
        btnTrimiteRaspuns=(Button)findViewById(R.id.btnTrimiteRaspuns);

        tvCorecte.setTextColor(getResources().getColor(R.color.verdeChestionar));
        tvGresite.setTextColor(getResources().getColor(R.color.rosuChestionar));

        btnVariantaA.setOnClickListener(this);
        btnVariantaB.setOnClickListener(this);
        btnVariantaC.setOnClickListener(this);
        btnTrimiteRaspuns.setOnClickListener(this);

        invisibile();
        chestionarPrecedent=new ChestionarJSON();
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
        findViewById(R.id.tvSpatiu).setVisibility(View.INVISIBLE);
        btnVariantaA.setVisibility(View.INVISIBLE);
        btnVariantaB.setVisibility(View.INVISIBLE);
        btnVariantaC.setVisibility(View.INVISIBLE);
    }

    public void visibile(){
        findViewById(R.id.tvSpatiu).setVisibility(View.VISIBLE);
        btnVariantaA.setVisibility(View.VISIBLE);
        btnVariantaB.setVisibility(View.VISIBLE);
        btnVariantaC.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnVariantaA: {
                if (btnVariantaA.isActivated()) {
                    btnVariantaA.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaA.setActivated(false);
                } else {
                    btnVariantaA.setBackgroundColor(Color.YELLOW);
                    btnVariantaA.setActivated(true);
                }
                break;
            }

            case R.id.btnVariantaB: {
                if (btnVariantaB.isActivated()) {
                    btnVariantaB.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaB.setActivated(false);
                } else {
                    btnVariantaB.setBackgroundColor(Color.YELLOW);
                    btnVariantaB.setActivated(true);
                }
                break;
            }

            case R.id.btnVariantaC: {
                if (btnVariantaC.isActivated()) {
                    btnVariantaC.setBackgroundResource(android.R.drawable.btn_default);
                    btnVariantaC.setActivated(false);
                } else {
                    btnVariantaC.setBackgroundColor(Color.YELLOW);
                    btnVariantaC.setActivated(true);
                }
                break;
            }

            case R.id.btnTrimiteRaspuns:{

                if(btnTrimiteRaspuns.getText().equals("START")) {
                    JSON obj=new JSON();
                    obj.execute(i);
                    i++;
                    visibile();
                    btnTrimiteRaspuns.setText("NEXT");
                }
                else {

                    if(i<3)
                    {
                        JSON obj = new JSON();
                        obj.execute(i);}
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

                        setContentView(R.layout.chestionarfinal);
                        TextView raspCorecteTV=(TextView)findViewById(R.id.testFinalRC);
                        TextView raspGresiteTV=(TextView)findViewById(R.id.testFinalRG);
                        raspCorecteTV.setText(raspunsuriCorecte.toString());
                        raspGresiteTV.setText(raspunsuriGresite.toString());

                        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layRez);
                        //desenareGrafic(raspunsuriCorecte, raspunsuriGresite);
                    }
                    i++;
                }
            }
        }
    }

    public class JSON extends AsyncTask<Integer, Void, ChestionarJSON> {

        private Integer idIntrebare;

        @Override
        protected ChestionarJSON doInBackground(Integer... params) {

            idIntrebare=params[0];
            ChestionarJSON chestionar = new ChestionarJSON();

            try {
                URL url = new URL("https://api.myjson.com/bins/1e99r");
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
            TextView nrIntrebare=(TextView)findViewById(R.id.tvNrIntrebare);
            TextView intrebare=(TextView)findViewById(R.id.tvIntrebare);
            TextView raspunsuri = (TextView) findViewById(R.id.tvRaspunsuri);

            idIntrebare+=1;
            nrIntrebare.setText("Intrebarea #"+idIntrebare.toString());
            String textIntrebare=chestionarJSON.getIntrebare();
            String textRaspunsuri= "\n"+"A."+chestionarJSON.getRaspunsA()
                    +"\n"+"B."+chestionarJSON.getRaspunsB()
                    +"\n"+"C."+chestionarJSON.getRaspunsC();
            intrebare.setText(textIntrebare);
            raspunsuri.setText(textRaspunsuri);

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

                //TREBUIE PUSA O CONDITIE SA SE OPREASCA ATUNCI CAND NU MAI SUNT INTREBARI PENTRU CA CONTIUNUA SA INCREMENTEZE
                //NUMARUL DE R CORECTE SAU R GRESITE LA APASAREA BUTONULUI
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
