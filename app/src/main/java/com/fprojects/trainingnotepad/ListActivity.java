package com.fprojects.trainingnotepad;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    private ListPresenter presenterList = new ListPresenter( this );

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        updateWorkoutListTextV( DataBaseHandler.showWorkoutList() );
    }

    public void updateWorkoutListTextV( final String text ) {
        final TextView workoutListTextV = findViewById( R.id.workoutListTextV );
        workoutListTextV.setText( text );
    }

}
