package com.fprojects.trainingnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private StartPresenter presenterStart = new StartPresenter( this );

    private static DataBase dataBase;
    static SQLiteDatabase sqLiteDatabase;

    Button startWorkoutActivityBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dataBase = new DataBase(this);
        sqLiteDatabase = dataBase.getWritableDatabase();

        startWorkoutActivityBtn = (Button) findViewById(R.id.startWorkoutActivityBtn);
        startWorkoutActivityBtn.setOnClickListener(this);

        initStartListActivityBtnListener();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startWorkoutActivityBtn:
                Intent toWorkoutActivity = new Intent(this, WorkoutActivity.class);
                startActivity(toWorkoutActivity);
                Cursor cursor = sqLiteDatabase.query("Contact", null, null, null, null, null, null);
                if ( !cursor.moveToFirst() ) DataBaseHandler.addFirstRowInTable();
                break;
            default:
                break;
        }
    }

    private void initStartListActivityBtnListener() {
        final Button startListActivityBtn = findViewById( R.id.startListActivityBtn );
        startListActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.startListActivityBtn:
                        Intent toListActivity = new Intent(StartActivity.this, ListActivity.class);
                        startActivity(toListActivity);
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
