package com.fprojects.trainingnotepad;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DataBaseHandler extends AppCompatActivity {

    private static WorkoutActivity workoutActivity;
    DataBaseHandler( final WorkoutActivity newActivity ) { workoutActivity = newActivity; }

    public static void insertValuesInTable() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutStart", 1);
        contentValues.put("date", workoutActivity.getTrainingDateEditT());
        contentValues.put("myWeight", workoutActivity.getMyWeightEditText());
        contentValues.put("exercise", workoutActivity.getExerciseEditT());
        contentValues.put("exerciseWeight", workoutActivity.getExerciseWeightEditT());
        contentValues.put("approaches", WorkoutPresenter.getCountApproach1stExercise());
        contentValues.put("repeats", WorkoutPresenter.getCountRepeat1stExercise());

        StartActivity.sqLiteDatabase.insert("Contact", null, contentValues);

        Log.d("log", "Exercise inserted");
    }

    public static String showDataBaseTable() {
        String showString = "";
        Cursor cursor = StartActivity.sqLiteDatabase.query("Contact", null, null, null, null, null, null);
        if (cursor.moveToFirst()){
            do {
                Log.d("log", "id " + cursor.getInt(cursor.getColumnIndex("id")) +
                        " myWeight " + cursor.getString(cursor.getColumnIndex("myWeight")) +
                        " exercise " + cursor.getInt(cursor.getColumnIndex("exercise")));

                showString = showString + (String) (
                        "Workout Start: " + cursor.getString(cursor.getColumnIndex("workoutStart")) +
                                " Workout End: " + cursor.getString(cursor.getColumnIndex("workoutEnd")) +
                                " Date: " + cursor.getString(cursor.getColumnIndex("date")) +
                                " My Weight: " + cursor.getString(cursor.getColumnIndex("myWeight" )) +
                                " Exercise: " + cursor.getString(cursor.getColumnIndex("exercise")) +
                                " Exercise weight: " + cursor.getString(cursor.getColumnIndex("exerciseWeight")) +
                                " Approaches: " + cursor.getString(cursor.getColumnIndex("approaches")) +
                                " Repeats: " + cursor.getString(cursor.getColumnIndex("repeats")) + "\n"
                );

            } while (cursor.moveToNext());
        } else {
            Log.d("log", "Table not found");
        }
        return showString;
    }

    public static String showWorkoutList() {
        String showString = "";
        Cursor cursor = StartActivity.sqLiteDatabase.query("Contact", null, null, null, null, null, null);
        if (cursor.moveToFirst() && !cursor.isLast()){
            cursor.moveToNext();
            do {

                cursor.moveToPrevious();//Получаем данные о дате и собственном весе в данной тренировке, если это начало новой тренировки
                if ( cursor.getString(cursor.getColumnIndex("workoutStart")).equals("0") ) {
                    cursor.moveToNext();
                    if ( cursor.getString(cursor.getColumnIndex("workoutStart")).equals("1") ) { //Если эта строка относится к тренировке, записываем дату и собственный вес
                        showString = showString + "\n" + cursor.getString(cursor.getColumnIndex("date")) + "\n" + cursor.getString(cursor.getColumnIndex("myWeight")) + "\n";
                    }
                } else cursor.moveToNext();

                if ( cursor.getString(cursor.getColumnIndex("workoutStart")).equals("1") ) { //Если эта строка относится к тренировке, записываем данные
                    showString = showString + (String) (
                            cursor.getString(cursor.getColumnIndex("exercise")) +
                                    " " + cursor.getString(cursor.getColumnIndex("exerciseWeight")) +
                                    " x " + cursor.getString(cursor.getColumnIndex("approaches")) +
                                    " x " + cursor.getString(cursor.getColumnIndex("repeats")) + "\n"
                    );
                }

            } while (cursor.moveToNext());
        } else {
            Log.d("log", "Table not found");
        }
        return showString;
    }

    public static String showContinuedWorkout() {
        String showString = "";
        Cursor cursor = StartActivity.sqLiteDatabase.query("Contact", null, null, null, null, null, null);
        if (cursor.moveToLast()) {
            while (cursor.getString(cursor.getColumnIndex("workoutStart")).equals("1") && !cursor.isFirst()) {
                cursor.moveToPrevious(); //Двигаем курсор к началу последней тренировки
            }

            cursor.moveToNext(); //Двигаем курсор ниже, так как текущая строка не содержит данных о тренировке
            showString = cursor.getString(cursor.getColumnIndex("date")) + "\n" + cursor.getString(cursor.getColumnIndex("myWeight")) + "\n";
            cursor.moveToPrevious(); //Возвращаем курсор, чтобы нормально пройтись по таблице

            do {
                cursor.moveToNext();
                if (!cursor.getString(cursor.getColumnIndex("exercise")).equals("0")){
                    showString = showString + (String) (
                                cursor.getString(cursor.getColumnIndex("exercise")) +
                                " " + cursor.getString(cursor.getColumnIndex("exerciseWeight")) +
                                " x " + cursor.getString(cursor.getColumnIndex("approaches")) +
                                " x " + cursor.getString(cursor.getColumnIndex("repeats")) + "\n"
                    );
                }
            } while (cursor.getString(cursor.getColumnIndex("workoutStart")).equals("1") && !cursor.isLast());
        } else {
            Log.d("log", "Table not found");
        }
        Log.d("log", "Строка вывода: " + showString);
        return showString;
    }

    public static void deleteDataBase() {
        StartActivity.sqLiteDatabase.delete("Contact", null, null);
        StartActivity.sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + "Contact" + "'"); //установка id на 1
        Log.d("log", "Table deleted");
        addFirstRowInTable();
    }

    public static void addStartWorkoutLabelToDataBase() {           //добавляет в таблицу строку с меткой о начале тренировки
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutStart", 1);
        StartActivity.sqLiteDatabase.insert("Contact", null, contentValues);
    }

    public static void addEndWorkoutLabelToDataBase() {             //добавляет в таблицу строку с меткой о конце тренировки
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutEnd", 1);
        StartActivity.sqLiteDatabase.insert("Contact", null, contentValues);
    }

    public static void addFirstRowInTable() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("workoutStart", 0);
        StartActivity.sqLiteDatabase.insert("Contact", null, contentValues);
        Log.d("log", "Добавлена первая строка");
    }



}
