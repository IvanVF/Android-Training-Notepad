package com.fprojects.trainingnotepad;

import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutPresenter extends AppCompatActivity {

    private WorkoutActivity workoutActivity;
    WorkoutPresenter( final WorkoutActivity newActivity ) { workoutActivity = newActivity; }

    private static int countApproach1stExercise = 0, countApproach2ndExercise = 0, countApproach3rdExercise = 0, countApproach4thExercise = 0, countApproach5thExercise = 0, countApproach6thExercise = 0, countApproach7thExercise = 0, countApproach8thExercise = 0, countApproach9thExercise = 0, countApproach10thExercise = 0;
    private static int countRepeat1stExercise = 0, countRepeat2ndExercise = 0, countRepeat3rdExercise = 0, countRepeat4thExercise = 0, countRepeat5thExercise = 0, countRepeat6thExercise = 0, countRepeat7thExercise = 0, countRepeat8thExercise = 0, countRepeat9thExercise = 0, countRepeat10thExercise = 0;

    public static int getCountApproach1stExercise() {
        return countApproach1stExercise;
    }

    public static int getCountRepeat1stExercise() {
        return countRepeat1stExercise;
    }
//***************************Настройка кнопок подходов 1 упражнения

    public void onNumberOfApproachDecr1stBtnClicked() {
        if ( countApproach1stExercise <=0 ) { countApproach1stExercise = 0; } else { countApproach1stExercise--; }
        workoutActivity.updateNumberOfApproach1stTextV( countApproach1stExercise );
    }

    public void onNumberOfApproachIncr1stBtnClicked() {
        countApproach1stExercise++;
        workoutActivity.updateNumberOfApproach1stTextV( countApproach1stExercise );
    }

//***************************Конец настройки кнопок подходов 1 упражнения

//***************************Настройка кнопок повторений 1 упражнения

    public void onNumberOfRepeatDecr1stBtnClicked() {
        if ( countRepeat1stExercise <=0 ) { countRepeat1stExercise = 0; } else { countRepeat1stExercise--; }
        workoutActivity.updateNumberOfRepeat1stTextV( countRepeat1stExercise );
    }

    public void onNumberOfRepeatIncrBtnClicked() {
        countRepeat1stExercise++;
        workoutActivity.updateNumberOfRepeat1stTextV( countRepeat1stExercise );
    }

//***************************Конец настройки кнопок повторений 1 упражнения

//***************************Настройка кнопок работы с базой данных

    public void onAddExerciseBtnClicked() {
        if ( !workoutActivity.getExerciseEditT().equals("") ) {
            try {
                DataBaseHandler.insertValuesInTable();
                workoutActivity.updateExerciseEditT("");
                workoutActivity.updateExerciseWeightEditT("");
                countApproach1stExercise = 0;
                countRepeat1stExercise = 0;
                workoutActivity.updateNumberOfApproach1stTextV(countApproach1stExercise);
                workoutActivity.updateNumberOfRepeat1stTextV(countRepeat1stExercise);
            } catch (Exception e) {
                Log.d("log", "Ошибка при добавлении упражнения в таблицу: " + e.getMessage());
            }

            try {
                workoutActivity.updateShowTextView(DataBaseHandler.showContinuedWorkout());
            } catch (Exception e) {
                Log.d("log", "Ошибка при отображении текущей тренировки: " + e.getMessage());
            }
        }
    }

    public void onEndWorkoutBtnClicked() {
        DataBaseHandler.addEndWorkoutLabelToDataBase();

    }

    public void onDeleteTableBtnClicked() {
        DataBaseHandler.deleteDataBase();
    }

    public void onShowBtnClicked() {
        try {
                workoutActivity.updateShowTextView( DataBaseHandler.showDataBaseTable() );
                Log.d( "log", "Данные извлечены" );
            }
        catch ( Exception e ) { Log.d( "log", "Ошибка при извлечении данных из таблицы: " + e.getMessage() ); }


    }

//***************************Конец настройки кнопок работы с базой данных

}
