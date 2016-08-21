package ro.andrei.scoalaauto.Activitati;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import ro.andrei.scoalaauto.Clase.CustomListAdapter;
import ro.andrei.scoalaauto.Clase.ListItem;

public class Indicatoare extends AppCompatActivity {

    String categorie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicatoare);
        categorie=this.getIntent().getStringExtra("categoria");
        ArrayList<ListItem> listaIndicatoare=getLista();

        final ListView listView=(ListView)findViewById(R.id.listviewIndicatoare);
        listView.setAdapter(new CustomListAdapter(this, listaIndicatoare));
    }

    private ArrayList<ListItem> getLista() {
        ArrayList<ListItem> listaTemporala=new ArrayList<ListItem>();
        String[] urlImagini=null;
        String[] textIndicatoare=null;
        String[] titluIndicatoare=null;
        if(categorie.equals("categoria1")) {
            urlImagini = getResources().getStringArray(R.array.images_array_01);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_01);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_01);
        }
        else if(categorie.equals("categoria2")) {
            urlImagini = getResources().getStringArray(R.array.images_array_02);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_02);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_02);
        }
        else if(categorie.equals("categoria3")) {
            urlImagini = getResources().getStringArray(R.array.images_array_03);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_03);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_03);
        }
        else if(categorie.equals("categoria4")) {
            urlImagini = getResources().getStringArray(R.array.images_array_04);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_04);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_04);
        }
        else if(categorie.equals("categoria5")) {
            urlImagini = getResources().getStringArray(R.array.images_array_05);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_05);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_05);
        }
        else if(categorie.equals("categoria6")) {
            urlImagini = getResources().getStringArray(R.array.images_array_06);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_06);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_06);
        }
        else if(categorie.equals("categoria7")) {
            urlImagini = getResources().getStringArray(R.array.images_array_07);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_07);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_07);
        }
        else if(categorie.equals("categoria8")) {
            urlImagini = getResources().getStringArray(R.array.images_array_08);
            textIndicatoare = getResources().getStringArray(R.array.textIndicatoare_array_08);
            titluIndicatoare = getResources().getStringArray(R.array.titluIndicatoare_array_08);
        }
        for(int i=0;i<textIndicatoare.length;i++){
            ListItem item=new ListItem();
            item.setUrl(urlImagini[i]);
            item.setTextIndicator(textIndicatoare[i]);
            item.setTitluIndicator(titluIndicatoare[i]);

            listaTemporala.add(item);
        }
        return listaTemporala;
    }
}
