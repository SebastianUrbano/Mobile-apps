package com.example.basededatos.model.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basededatos.app.ManejoDeDatosApp;
import com.example.basededatos.model.driver.DBDriver;
import com.example.basededatos.model.entity.TaskList;

import java.util.ArrayList;
import java.util.Date;

public class CRUDTTasklist {

    public static void createTaskList(TaskList list){
        DBDriver driver = DBDriver.getInstance(ManejoDeDatosApp.getAppContext());

        SQLiteDatabase db = driver.getWritableDatabase();

        String sql = "INSERT INTO $TABLE ($ID, $NAME, $DATE) VALUES ('$VID','$VNAME', $VDATE)";
        sql = sql.replace("$TABLE",DBDriver.TABLE_TASKLIST)
        .replace("$ID", DBDriver.TASKLIST_ID)
        .replace("$NAME", DBDriver.TASKLIST_NAME)
        .replace("$DATE", DBDriver.TASKLIST_DATE)
        .replace("$VID", list.getId())
        .replace("$VNAME", list.getName()
        .replace("$VDATE", ""+list.getDate().getTime()));

        db.execSQL(sql);
        db.close();

    }

    public static ArrayList<TaskList> getAllTaskList(){
        DBDriver driver = DBDriver.getInstance(ManejoDeDatosApp.getAppContext());

        ArrayList<TaskList> taskLists = new ArrayList<>();
        SQLiteDatabase db = driver.getReadableDatabase();
        driver.getReadableDatabase();
        String sql = "SELECT * FROM "+ DBDriver.TABLE_TASKLIST;
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            do {
                String id = cursor.getString(cursor.getColumnIndex(DBDriver.TASKLIST_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBDriver.TASKLIST_NAME));
                long date = cursor.getLong(cursor.getColumnIndex(DBDriver.TASKLIST_DATE));

                Date datetime = new Date(date);

                TaskList list = new TaskList(id, name, datetime);
                taskLists.add(list);
            }
            while (cursor.moveToNext());

        }



        db.close();
        return taskLists;
    }

}
