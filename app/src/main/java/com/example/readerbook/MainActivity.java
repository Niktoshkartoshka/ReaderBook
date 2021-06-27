package com.example.readerbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Класс, в котором организована основная логика приложения.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Элемент управления, отображающий список элементов.
     */
    ListView list_view;

    /**
     * Список файлов формата pdf.
     */
    public static ArrayList<File> fileList = new ArrayList<>();

    /**
     * Адаптер между ListView и данными (fileList).
     */
    Adapter obj_adapter;

    /**
     * Код запроса разрешения.
     */
    public static int REQUEST_PERMISSION = 1;

    /**
     * Логическое обозначение того, было ли дано разрешение.
     */
    boolean boolean_permisions;

    /**
     * Абстрактное представление файлов и каталогов.
     */
    File dir;

    /**
     * Кнопка для вызова всплывающего меню.
     */
    Button buttonPopup;


    /**
     * Выполняет инициализацию всех фрагментов.
     *
     * @param savedInstanceState Хранит состояние приложения, которое может быть снова передано в onCreate().
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list_view = (ListView) findViewById(R.id.listView_pdf);
        dir = new File(Environment.getExternalStorageDirectory().toString());
        permission_fn();
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Обрабатывает нажатие на пункт списка.
             * @param parent ListView, где произошел щелчок.
             * @param view Непосредственно нажатый пункт.
             * @param position Положение данного пункта в ListView.
             * @param id ID нажатого элемента.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File file = (File) list_view.getItemAtPosition(position);
                String name = file.getName();
                if (name.endsWith(".pdf")) {
                    Intent intent = new Intent(getApplicationContext(), PDFviewer.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                } else if (name.endsWith(".fb2")) {
                    Intent intent = new Intent(getApplicationContext(), FB2.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            }
        });
        buttonPopup = (Button) findViewById(R.id.buttonPopup);
        buttonPopup.setOnClickListener(new View.OnClickListener() {
            /**
             * Обработка нажатия кнопки со всплывающим меню.
             * @param v В данном контексте - кнопка.
             */
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(MainActivity.this, buttonPopup);
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.one:
                                Collections.sort(fileList, FileComparator.compareFilesByName);
                                list_view.setAdapter(null);
                                obj_adapter = new Adapter(getApplicationContext(), fileList);
                                list_view.setAdapter(obj_adapter);
                                return true;
                            case R.id.two:
                                Collections.sort(fileList, FileComparator.compareFilesByDate);
                                list_view.setAdapter(null);
                                obj_adapter = new Adapter(getApplicationContext(), fileList);
                                list_view.setAdapter(obj_adapter);
                                return true;
                            case R.id.three:
                                Collections.sort(fileList, FileComparator.compareFilesBySize);
                                list_view.setAdapter(null);
                                obj_adapter = new Adapter(getApplicationContext(), fileList);
                                list_view.setAdapter(obj_adapter);
                                return true;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    /**
     * Метод для осуществления запроса разрешения к памяти.
     */
    private void permission_fn() {
        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION);
            }
        } else {
            boolean_permisions = true;
            ArrayList<File> getfile = getfile(dir);
            obj_adapter = new Adapter(getApplicationContext(), fileList);
            list_view.setAdapter(obj_adapter);
        }
    }

    /**
     * Метод, используемый для получения результата запроса разрешений.
     *
     * @param requestCode  Код запроса.
     * @param permissions  Запрошенные разрешения.
     * @param grantResults Результаты предоставления соответствующих разрешений.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                boolean_permisions = true;
                getfile(dir);
                obj_adapter = new Adapter(getApplicationContext(), fileList);
                list_view.setAdapter(obj_adapter);
            } else {
                Toast.makeText(this, "Пожалуйста, дайте разрешение для доступа к памяти устройства", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Метод, ищущий в памяти устройства файлы с расширением pdf.
     *
     * @param dir Абстрактное представление файлов и каталогов.
     * @return Список файлов с расширением pdf.
     */
    public ArrayList<File> getfile(File dir) {
        File[] listFile = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {
                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);
                } else {
                    boolean boolean_file = false;
                    if (listFile[i].getName().endsWith(".pdf")) { // || listFile[i].getName().endsWith(".fb2")
                        for (int j = 0; j < fileList.size(); j++) {
                            if (fileList.get(j).getName().equals(listFile[i].getName())) {
                                boolean_file = true;
                            } else {
                            }
                        }
                        if (boolean_file) {
                            boolean_file = false;
                        } else {
                            fileList.add(listFile[i]);
                        }
                    }
                }
            }
        }
        return fileList;
    }
}
