package com.fprojects.trainingnotepad;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutActivity extends AppCompatActivity {

    private WorkoutPresenter presenterWorkout = new WorkoutPresenter( this );

    private static EditText trainingDateEditT, myWeightEditText, exerciseEditT, exerciseWeightEditT;

    private static String errors = "";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.activity_workout);

        trainingDateEditT = (EditText) findViewById(R.id.trainingDateEditT);
        myWeightEditText = (EditText) findViewById(R.id.myWeightEditT);
        exerciseEditT = (EditText) findViewById(R.id.exerciseEditT);
        exerciseWeightEditT = (EditText) findViewById(R.id.exerciseWeightEditT);

        initNumberOfApproachDecr1stBtnListener();
        initNumberOfApproachIncr1stBtnListener();
        initNumberOfRepeatDecr1stBtnListener();
        initNumberOfRepeatIncr1stBtnListener();
        initAddExerciseBtnListener();
        initEndWorkoutBtnListener();
        initDeleteTableBtnListener();
        initShowBtnListener();
    }

    private void initEndWorkoutBtnListener() {
        final Button endWorkoutBtn = findViewById( R.id.endWorkoutBtn );
        endWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.endWorkoutBtn:
                        Intent toStartActivity = new Intent(WorkoutActivity.this, StartActivity.class);
                        startActivity(toStartActivity);
                        break;
                    default:
                        break;
                }
            }
        });
        DataBaseHandler.addEndWorkoutLabelToDataBase();
    }

    //***************************Настройка кнопок подходов 1 упражнения

    private void initNumberOfApproachDecr1stBtnListener() {
        final Button numberOfApproachDecr1stBtn = findViewById(R.id.numberOfApproachDecr1Btn);
        numberOfApproachDecr1stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onNumberOfApproachDecr1stBtnClicked(); }
        });

    }

    private void initNumberOfApproachIncr1stBtnListener() {
        final Button numberOfApproachIncr1stBtn = findViewById( R.id.numberOfApproachIncr1stBtn );
        numberOfApproachIncr1stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onNumberOfApproachIncr1stBtnClicked(); }
        });
    }

    public void updateNumberOfApproach1stTextV( final int counter ) {
        final TextView numberOfApproach1stTextV = findViewById(R.id.numberOfApproach1stTextV);
        String text = Integer.toString( counter );
        numberOfApproach1stTextV.setText( text );
    }

//***************************Конец настройки кнопок подходов 1 упражнения

//***************************Настройка кнопок повторений 1 упражнения

    private void initNumberOfRepeatDecr1stBtnListener() {
        final Button numberOfRepeatDecr1stBtn = findViewById( R.id.numberOfRepeatDecr1stBtn );
        numberOfRepeatDecr1stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onNumberOfRepeatDecr1stBtnClicked(); }
        });
    }

    private void initNumberOfRepeatIncr1stBtnListener() {
        final Button numberOfRepeatIncr1stBtn = findViewById( R.id.numberOfRepeatIncr1stBtn );
        numberOfRepeatIncr1stBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onNumberOfRepeatIncrBtnClicked(); }
        });
    }

    public void updateNumberOfRepeat1stTextV( final int counter ) {
        final TextView numberOfRepeat1stTextV = findViewById( R.id.numberOfRepeat1stTextV );
        String text = Integer.toString( counter );
        numberOfRepeat1stTextV.setText( text );
    }

//***************************Конец настройки числа повторений 1 упражнения

//***************************Настройка кнопок работы с базой данных

    private void initAddExerciseBtnListener() {
        final Button addExerciseBtn = findViewById( R.id.addExerciseBtn );
        addExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onAddExerciseBtnClicked(); }
        });
    }

    /*private void initEndWorkoutBtnListener() {
        final Button endWorkoutBtn = findViewById( R.id.endWorkoutBtn );
        endWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onEndWorkoutBtnClicked(); }
        });
    }*/

    private void initDeleteTableBtnListener() {
        final Button deleteTableBtn = findViewById( R.id.deleteTableBtn );
        deleteTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onDeleteTableBtnClicked(); }
        });
    }

    private void initShowBtnListener() {
        final Button showBtn = findViewById( R.id.showBtn );
        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { presenterWorkout.onShowBtnClicked(); }
        });
    }

//***************************Конец настройки кнопок работы с базой данных

//***************************Настройка полей ввода данных

    public static String getTrainingDateEditT() {
        final String date = trainingDateEditT.getText().toString();
        return date;
    }

    public static double getMyWeightEditText() {
        double myWeight = 0;
        if (!myWeightEditText.getText().toString().equals("")) {
            try { myWeight = Double.parseDouble( myWeightEditText.getText().toString() ); }
            catch (Exception e) { errors = errors + " Ошибка myWeight: " + e.getMessage(); }
        } else myWeight = 0;
        return myWeight;
    }

    public static String getExerciseEditT() {
        final String exercise = exerciseEditT.getText().toString();
        return exercise;
    }

    public static double getExerciseWeightEditT() {
        double exerciseWeight = 0;
        if ( !exerciseEditT.getText().toString().equals("") ) {
            try { exerciseWeight = Double.parseDouble(exerciseWeightEditT.getText().toString()); }
            catch (Exception e) { errors = errors + " Ошибка exerciseWeight: " + e.getMessage(); }
        } else exerciseWeight = 0;
        return exerciseWeight;
    }

//***************************Конец настройки полей вывода данных

    public void updateShowTextView( final String text ) {
        final TextView showTextView = findViewById( R.id.showTextView );
        showTextView.setText( text );
    }

    public void updateExerciseEditT( final String text ) {
        final EditText exerciseEditT = findViewById( R.id.exerciseEditT );
        exerciseEditT.setText( text );
    }

    public void updateExerciseWeightEditT( final String text ) {
        final EditText exerciseWeightEditT = findViewById( R.id.exerciseWeightEditT );
        exerciseWeightEditT.setText( text );
    }

}
