package com.fprojects.trainingnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

public class StartPresenter extends AppCompatActivity {

    private StartActivity startActivity;
    StartPresenter( final StartActivity newActivity ) { startActivity = newActivity; }

    /*public void onStartWorkoutBtnClicked() {
        finish();
        Intent toWorkoutActivity = new Intent( getApplicationContext(),  WorkoutActivity.class);
        toWorkoutActivity.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity( toWorkoutActivity );
    }*/

}
