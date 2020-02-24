package com.example.basededatos.model.driver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDriver extends SQLiteOpenHelper {


    private static DBDriver instance = null;
    public static synchronized DBDriver getInstance(Context context){
        if(instance == null){
            instance = new DBDriver(context);
        }
            return instance;

    }
    public static final String DB_NAME = "ManejoDeDatos";
    public static final int DB_VERSION = 1;

    // Table tasklist

    public static final String TABLE_TASKLIST = "tasklist";
    public static final String TASKLIST_ID = "id";
    public static final String TASKLIST_NAME = "name";
    public static final String TASKLIST_DATE= "date";


    // Table task

    public static final String TABLE_TASK = "task";
    public static final String TASK_ID = "id";
    public static final String TASK_NAME = "name";
    public static final String TASK_DESCRIPTION = "description";
    public static final String TASK_COMPLETE = "iscomplete";
    public static final String FK_TASKLIST_TASK = "tasklistid";




    private DBDriver(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_TASKLIST = "CREATE TABLE $TABLE($ID TEXT PRIMARY KEY, $NAME TEXT, $DATE INTEGER)";
        CREATE_TABLE_TASKLIST = CREATE_TABLE_TASKLIST.replace("$TABLE",TABLE_TASKLIST).replace("$ID", TASKLIST_ID).replace("$NAME",TASKLIST_NAME).replace("$DATE",TASKLIST_DATE);


        String CREATE_TABLE_TASK = "CREATE TABLE $TABLE($ID TEXT PRIMARY KEY, $NAME TEXT, $DESCRIPTION TEXT, $ISCOMPLETE INTEGER, $FK TEXT, FOREIGN KEY ($FK) REFERENCES $FTABLE($FID))";
        CREATE_TABLE_TASK = CREATE_TABLE_TASK
                .replace("$TABLE",TABLE_TASK)
                .replace("$ID", TASK_ID)
                .replace("$NAME",TASK_NAME)
                .replace("$DESCRIPTION",TASK_DESCRIPTION)
                .replace("$ISCOMPLETE",TASK_COMPLETE)
                .replace("$FK", FK_TASKLIST_TASK)
                .replace("$FTABLE",TABLE_TASKLIST)
                .replace("$FID",TASKLIST_ID);

        db.execSQL(CREATE_TABLE_TASKLIST);
        db.execSQL(CREATE_TABLE_TASK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TASK);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_TASKLIST);
        onCreate(db);


    }
}
