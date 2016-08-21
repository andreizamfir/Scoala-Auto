package ro.andrei.scoalaauto.Clase;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Adrian on 1/4/2016.
 */
public class DbAdapter {
    MySqlLiteHelper dbHelper;
    SQLiteDatabase db;

    public DbAdapter(Context ctx) {
        dbHelper = new MySqlLiteHelper(ctx, "Inregistraredb", null, 1);
    }

    public void openConnection(){
        db = dbHelper.getWritableDatabase();
    }

    public void closeConnection(){
        db.close();
        dbHelper.close();
    }

    public long insert(Utilizator u){
        ContentValues values = new ContentValues();
        values.put("nume", u.getNume());
        values.put("email", u.getEmail());
        values.put("username", u.getUsername());
        values.put("parola", u.getParola());
        values.put("testeTrecute", u.getTesteTrecute());
        values.put("testePicate", u.getTestePicate());

        return db.insert("users", null, values);
    }

    public Utilizator select(String username,String parola){
        String[] coloane={"nume","email","username","parola","testePicate","testeTrecute"};
        String conditie="username" + "='" + username + "' AND " + "parola" + "='" + parola+"'";

        Cursor c=db.query("users", coloane, conditie, null, null, null, null, null);
        if(c.moveToFirst())
        {
            Utilizator u=new Utilizator(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getInt(5));
            c.close();
            db.close();
            return u;
        }

        return null;
    }

    public Utilizator selectDupaUSERNAME(String username){
        String[] coloane={"nume","email","username","parola","testePicate","testeTrecute"};
        String conditie="username" + "='" + username + "'";

        Cursor c=db.query("users", coloane, conditie, null, null, null, null, null);
        if(c.moveToFirst())
        {
            Utilizator u=new Utilizator(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getInt(5));
            c.close();
            db.close();
            return u;
        }

        return null;
    }

    public void updateTesteTrecute(String username){
        String sql="UPDATE users SET testeTrecute = testeTrecute + "+1+" WHERE username='"+username+"'";

        try{
            db.beginTransaction();
            SQLiteStatement statement=db.compileStatement(sql);
            statement.execute();
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void updateTestePicate(String username){
        String sql="UPDATE users SET testePicate = testePicate +"+1+" WHERE username='"+username+"'";

        try{
            db.beginTransaction();
            SQLiteStatement statement=db.compileStatement(sql);
            statement.execute();
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void resetTesteTrecute(String username){
        String sql="UPDATE users SET testeTrecute = "+0+" WHERE username='"+username+"'";

        try{
            db.beginTransaction();
            SQLiteStatement statement=db.compileStatement(sql);
            statement.execute();
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    public void resetTestePicate(String username){
        String sql="UPDATE users SET testePicate = "+0+" WHERE username='"+username+"'";

        try{
            db.beginTransaction();
            SQLiteStatement statement=db.compileStatement(sql);
            statement.execute();
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    private class MySqlLiteHelper extends SQLiteOpenHelper {

        public MySqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table users (nume text not null, email text not null, username text not null primary key," +
                    " parola not null, testePicate integer, testeTrecute integer);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists users;");
            onCreate(db);
        }
    }

}
