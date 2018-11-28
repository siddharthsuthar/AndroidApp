package com.androidtutorialpoint.androidlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class myDbAdapter {

    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name,String email, String pass, String gender , String age)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.GENDER, gender);
        contentValues.put(myDbHelper.AGE, age);

        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        Log.d("CREATION" , "This is "+String.valueOf(id));
        return id;
    }


    public long insertSkills(String email,String description, String programmingLanguages, String tools , String frameworks, String databases)
    {

  //      Log.d("CREATION" , "inside insert skills form");
        SQLiteDatabase dbb = myhelper.getWritableDatabase();

        //Cursor cursor = dbb.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
          //      + myDbHelper.TABLE_NAME + "'", null);

//        Log.d("CREATION" , String.valueOf(cursor.getCount()));
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.DESCRIPTION, description);
        contentValues.put(myDbHelper.PROGRAMMING_LANGUAGES, programmingLanguages);
        contentValues.put(myDbHelper.TOOLS, tools);
        contentValues.put(myDbHelper.FRAMEWORKS, frameworks);
        contentValues.put(myDbHelper.DATABASES, databases);

        long id = dbb.insert(myDbHelper.TABLE_NAME_SKILLS, null , contentValues);

    //    Log.d("CREATION" , "This is "+String.valueOf(id));

        return id;
    }

    //Shilakha use this function to get the data from the profile. All you need to do now is set it up

//    public "Any Return type you need" getProfile(String Email){
//
//        String[] columns = {
//                myDbHelper.EMAIL
//        };
//        ArrayList<String> emails  = new ArrayList<>();
//        SQLiteDatabase db = myhelper.getReadableDatabase();
//        String selection = myDbHelper.EMAIL + " = ?";
//        String[] selectionArgs = {Email};
//        Cursor cursor = db.query(myDbHelper.TABLE_NAME_SKILLS
//                , //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);
//
//        while (cursor.moveToNext())
//        {  // here get everything you need
//            //String email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
//            //String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
//            //String programming_languages =cursor.getString(cursor.getColumnIndex(myDbHelper.PROGRAMMING_LANGUAGES));
//            //String tools =cursor.getString(cursor.getColumnIndex(myDbHelper.TOOLS));
//            //String frameworks=cursor.getString(cursor.getColumnIndex(myDbHelper.FRAMEWORKS));
//            //String databases=cursor.getString(cursor.getColumnIndex(myDbHelper.DATABASES));
//            //emails.add(email);
//        }
//        //return emails;
//
//
//    }


    public ArrayList<String> getEmails(String programmingLanguages){
        String[] columns = {
                myDbHelper.EMAIL
        };
        ArrayList<String> emails  = new ArrayList<>();
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selection = myDbHelper.PROGRAMMING_LANGUAGES + " = ?";
        String[] selectionArgs = {programmingLanguages};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME_SKILLS
                , //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);

        while (cursor.moveToNext())
        {
            String email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            //String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            //String programming_languages =cursor.getString(cursor.getColumnIndex(myDbHelper.PROGRAMMING_LANGUAGES));
            //String tools =cursor.getString(cursor.getColumnIndex(myDbHelper.TOOLS));
            //String frameworks=cursor.getString(cursor.getColumnIndex(myDbHelper.FRAMEWORKS));
            //String databases=cursor.getString(cursor.getColumnIndex(myDbHelper.DATABASES));
            emails.add(email);
        }
        return emails;
    }

    public String getData()
    {

        SQLiteDatabase db = myhelper.getWritableDatabase();

        Log.d("CREATION" ,"Inside the get data method");
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.EMAIL};
        Log.d("CREATION" ,columns[1]);
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);

        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));

//            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
//            String gender =cursor.getString(cursor.getColumnIndex(myDbHelper.GENDER));
//            String age =cursor.getString(cursor.getColumnIndex(myDbHelper.AGE));
            buffer.append(cid+ "   " + name + "   " + "  " + email+" \n");
        }
        Log.d("CREATION" ,buffer.toString());
        return buffer.toString();

    }

    public boolean getLogin(String email , String password){

        // array of columns to fetch
        String[] columns = {
                myDbHelper.UID
        };
        SQLiteDatabase db = myhelper.getReadableDatabase();
        // selection criteria
        String selection = myDbHelper.EMAIL + " = ?" + " AND " + myDbHelper.MyPASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(myDbHelper.TABLE_NAME
                , //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public String getDataSkills()
    {

        SQLiteDatabase db = myhelper.getWritableDatabase();

        Log.d("CREATION" ,"Inside the get data method");
        String []columns = {myDbHelper.EMAIL ,myDbHelper.DESCRIPTION,myDbHelper.PROGRAMMING_LANGUAGES,myDbHelper.TOOLS
        ,myDbHelper.FRAMEWORKS,myDbHelper.DATABASES};
        //Log.d("CREATION" ,columns[0]);


        Cursor cursor =db.query(myDbHelper.TABLE_NAME_SKILLS,columns,null,null,null,null,null);

        //Log.d("CREATION" , String.valueOf(cursor.getCount()));

        StringBuffer buffer= new StringBuffer();

        while (cursor.moveToNext())
        {
            String email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            String description =cursor.getString(cursor.getColumnIndex(myDbHelper.DESCRIPTION));
            String programming_languages =cursor.getString(cursor.getColumnIndex(myDbHelper.PROGRAMMING_LANGUAGES));
            String tools =cursor.getString(cursor.getColumnIndex(myDbHelper.TOOLS));
            String frameworks=cursor.getString(cursor.getColumnIndex(myDbHelper.FRAMEWORKS));
            String databases=cursor.getString(cursor.getColumnIndex(myDbHelper.DATABASES));
            buffer.append(email+" "+description+" "+" "+programming_languages
                    +" "+tools+" "+frameworks+" "+databases+"\n");

        }
        Log.d("CREATION" ,buffer.toString());
        return buffer.toString();

    }

    public boolean checkSkills(String email){

        String[] columns = {
                myDbHelper.EMAIL
        };
        SQLiteDatabase db = myhelper.getReadableDatabase();
        String selection = myDbHelper.EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME
                , //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();
        Log.d("CREATION" ,"Inside check"+String.valueOf(cursorCount));
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }




    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "userDetails";   // Table Name
        private static final int DATABASE_Version = 2;    // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String MyPASSWORD= "Password";    // Column III
        private static final String EMAIL= "email";
        private static final String GENDER= "gender";
        private static final String AGE= "age";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"
                +EMAIL+" VARCHAR(255) ,"
                + MyPASSWORD+" VARCHAR(225),"+GENDER+" VARCHAR(25) ,"
                +AGE+" VARCHAR(25));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;


        // for second table by shilakha

        private static final String TABLE_NAME_SKILLS = "skills"; //skills table --shilakha
        private static final String PROGRAMMING_LANGUAGES = "programmingLanguages"; //skills programmingLanguages --shilakha
        private static final String DESCRIPTION = "description";//skills description --shilakha
        private static final String TOOLS = "tools";//skills tools --shilakha
        private static final String FRAMEWORKS = "frameworks";//skills frameworks --shilakha
        private static final String DATABASES = "databases";//skills databases --shilakha
        private static final String CREATE_TABLE_SKILLS = "CREATE TABLE "+TABLE_NAME_SKILLS+
                " ("+EMAIL+" PRIMARY KEY , "+DESCRIPTION+" VARCHAR(1000) ,"
                +PROGRAMMING_LANGUAGES+" VARCHAR(1000) ,"
                + TOOLS+" VARCHAR(1000) ,"+FRAMEWORKS+" VARCHAR(1000) ,"
                +DATABASES+" VARCHAR(1000));";









        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE_SKILLS); //Added by shilakha
                Log.d("CREATION","TABLE CREATED");
            } catch (Exception e) {
                Toast.makeText(context,
                        e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Toast.makeText(context,
                        "OnUpgrade", Toast.LENGTH_LONG).show();
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context,
                        e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


}
