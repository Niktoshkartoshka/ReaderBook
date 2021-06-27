package com.example.readerbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.readerbook.R;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

/**
 * Класс, отображающий содержимое файла формата pdf на экране мобильного устройства.
 */
public class PDFviewer extends AppCompatActivity {

    /**
     * Содержимое файла формата pdf.
     */
    PDFView pdfView;

    /**
     * Значение положения файла в общем списке.
     */
    int position = -1;

    /**
     * Выполняет инициализацию всех фрагментов.
     *
     * @param savedInstanceState Хранит состояние приложения, которое может быть снова передано в onCreate().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfviewer);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        position = getIntent().getIntExtra("position", -1);

        displayPDF();

    }

    /**
     * Выводит содержимое файла на экран.
     */
    private void displayPDF() {
        pdfView.fromFile(MainActivity.fileList.get(position)).enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this)).load();
    }
}