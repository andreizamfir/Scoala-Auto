package ro.andrei.scoalaauto.Clase;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ro.andrei.scoalaauto.Activitati.R;

/**
 * Created by Adrian on 1/7/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<ListItem> lista;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context context,ArrayList<ListItem> lista){
        this.lista=lista;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.rowlayout,null);
            holder=new ViewHolder();
            holder.textIndicator=(TextView)convertView.findViewById(R.id.textIndicatorTV);
            holder.titluIndicator=(TextView)convertView.findViewById(R.id.titluIndicatorTV);
            holder.imageView=(ImageView)convertView.findViewById(R.id.pictogramaIV);


            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }

        ListItem item=lista.get(position);
        holder.textIndicator.setText(item.getTextIndicator());
        holder.titluIndicator.setText(item.getTitluIndicator());
        if(holder.imageView!=null){
            //aici apelam metoda pentru a descarca
            new ImageDownloader(holder.imageView).execute(item.getUrl());
        }

        return convertView;
    }

    static class ViewHolder{
        TextView textIndicator;
        TextView titluIndicator;
        ImageView imageView;
    }
}
