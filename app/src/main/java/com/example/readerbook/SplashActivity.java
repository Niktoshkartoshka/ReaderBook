package com.example.readerbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Класс, отображающий загрузочный экран приложения.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Выполняет инициализацию всех фрагментов, переход к следующему Activity.
     *
     * @param savedInstanceState Хранит состояние приложения, которое может быть снова передано в onCreate().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
