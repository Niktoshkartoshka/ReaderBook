package com.example.readerbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Класс с пользовательским адаптером.
 * Выводит в ListView для каждого файла название, дату изменения и размер.
 */
public class Adapter extends ArrayAdapter<File> {

    /**
     * Текущий контекст приложения.
     */
    Context context;

    /**
     * Сохраняет ссылки на необходимые в элементе списка шаблоны.
     */
    ViewHolder viewHolder;

    /**
     * Список файлов с расширением pdf.
     */
    ArrayList<File> arrlist_pdf;

    /**
     * Конструктор персонализированного адаптера.
     * @param context Текущий контекст.
     * @param arrlist_pdf Список файлов, который нужно вывести в ListView.
     */
    public Adapter(Context context, ArrayList<File> arrlist_pdf) {
        super(context, R.layout.adapt, arrlist_pdf);
        this.context = context;
        this.arrlist_pdf = arrlist_pdf;
    }

    /**
     * Позволяет различать типы View.
     * @param position Положение элемента в данных адаптера, тип View которого мы хотим узнать.
     * @return Возвращает положение элемента в данных адаптера.
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * Возвращает количество типов View, которые будут созданы методом {@link #getView}.
     * @return Возвращает количество типов View, которые будут созданы в данном адаптере.
     */
    @Override
    public int getViewTypeCount() {
        if (arrlist_pdf.size() > 0) {
            return arrlist_pdf.size();
        } else return 1;
    }

    /**
     * Получает View, которое отображает на определенной позиции в списке.
     * @param position Положение элемента в данных адапетра.
     * @param convertView Старое View, которое возможно использовать
     * @param parent ViewGroup, к которому будет прикрепленно созданное View
     * @return View, соответствующее данным в указанной позиции
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapt, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tv_fileName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
            viewHolder.tv_fileDate = (TextView) convertView.findViewById(R.id.time_name);
            convertView.setTag(viewHolder);
            viewHolder.tv_fileSize = (TextView) convertView.findViewById(R.id.size_name);
            convertView.setTag(viewHolder);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_fileName.setText(arrlist_pdf.get(position).getName());
        // new code
        viewHolder.tv_fileSize.setText(arrlist_pdf.get(position).length() / (1024) + " Kb");
        Date datatime = new Date(arrlist_pdf.get(position).lastModified());
        SimpleDateFormat format1;
        format1 = new SimpleDateFormat(
                "dd.MM.yyyy hh:mm");
        viewHolder.tv_fileDate.setText(format1.format(datatime));
        // new code
        String condition_name = arrlist_pdf.get(position).getName();
        if (condition_name.endsWith(".fb2")) {
            viewHolder.imageView.setImageResource(R.drawable.fb2);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.pdf3);
        }
        return convertView;
    }

    /**
     * Класс, в котором хранятся ссылки на необходимые в элементе списка шаблоны.
     */
    public class ViewHolder {
        TextView tv_fileName;
        ImageView imageView;
        TextView tv_fileSize;
        TextView tv_fileDate;
    }
}
