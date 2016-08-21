package ro.andrei.scoalaauto.Activitati;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Legislatie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislatie);
        String capitol=this.getIntent().getStringExtra("Capitol");
        TextView tvCap= (TextView) findViewById(R.id.titluCap);
        TextView tvContinut = (TextView) findViewById(R.id.continut);
        String cap = this.getIntent().getStringExtra("numar");
        if(cap.equals("unu"))
            tvContinut.setText(getResources().getString(R.string.capitolul1));
        else if (cap.equals("doi"))
            tvContinut.setText(getResources().getString(R.string.capitolul2));
        else if (cap.equals("trei"))
            tvContinut.setText(getResources().getString(R.string.capitolul3));
        else if (cap.equals("patru"))
            tvContinut.setText(getResources().getString(R.string.capitolul4));
        else if (cap.equals("cinci"))
            tvContinut.setText(getResources().getString(R.string.capitolul5));
        else if (cap.equals("sase"))
            tvContinut.setText(getResources().getString(R.string.capitolul6));
        else if (cap.equals("sapte"))
            tvContinut.setText(getResources().getString(R.string.capitolul7));
        else if (cap.equals("opt"))
            tvContinut.setText(getResources().getString(R.string.capitolul8));
        else if (cap.equals("noua"))
            tvContinut.setText(getResources().getString(R.string.capitolul9));
        else if (cap.equals("zece"))
            tvContinut.setText(getResources().getString(R.string.capitolul10));


        tvCap.setText(capitol);
    }

    public class LegislatieContinut extends AsyncTask<Integer,Void,String>
    {
        @Override
        protected String doInBackground(Integer... params) {
            String textCapitol = null;
            try {
                URL url=new URL("http://www.filedropper.com/legislatie");
                HttpURLConnection conexiuneXML=(HttpURLConnection)url.openConnection();
                InputStream input=conexiuneXML.getInputStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(input));
                String text="";
                String line="";
                while((line=reader.readLine())!=null)
                    text+=line;

                XmlPullParserFactory xmlppf=XmlPullParserFactory.newInstance();
                XmlPullParser xmlpp=xmlppf.newPullParser();
                xmlpp.setInput(new StringReader(text));
                int tag=xmlpp.getEventType();
                while(tag!=XmlPullParser.END_DOCUMENT)
                {
                    if(tag==XmlPullParser.START_TAG&&xmlpp.getName().equals("Capitol")){
                        textCapitol+=xmlpp.getAttributeValue(null, "numar");
                        tag=xmlpp.next();
                        if(tag==XmlPullParser.TEXT){
                            textCapitol+="-"+xmlpp.getText()+"\n";
                        }
                    }
                    tag=xmlpp.next();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
