package ro.andrei.scoalaauto.Clase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ro.andrei.scoalaauto.Activitati.R;

/**
 * Created by Adrian on 1/7/2016.
 */
//aceasta o folosim pentru a descarca imaginea si dupa ce imaginea este descarcata o punem in listviev
//dam ca parametru un string si anume URL ul si obtinem ca rezultat un Bitmap obj
//weak reference sunt folosite pentru mapping
public class ImageDownloader extends AsyncTask<String,Void,Bitmap> {
    public final WeakReference<ImageView> imageViewReference;

    public ImageDownloader(ImageView imageView) {
        imageViewReference=new WeakReference<ImageView>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return downloadBitmap(params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(isCancelled()){
            bitmap=null;
        }
        if(imageViewReference!=null){
            ImageView imageView=imageViewReference.get();
            if(imageView!=null){
                if(bitmap!=null){
                    imageView.setImageBitmap(bitmap);
                }else{
                    Drawable loader=imageView.getContext().getResources().getDrawable(R.drawable.loader);
                    imageView.setImageDrawable(loader);
                }
            }
        }
    }

    private Bitmap downloadBitmap(String url) {
        HttpURLConnection urlConnection=null;
        try{
            URL uri=new URL(url);
            urlConnection=(HttpURLConnection)uri.openConnection();

           /* int statusCode=urlConnection.getResponseCode();
            if(statusCode!=HttpStatus.SC_OK){
                return null;
            }*/

            InputStream inputStream=urlConnection.getInputStream();
            if(inputStream!=null){
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
        return null;
    }
}
