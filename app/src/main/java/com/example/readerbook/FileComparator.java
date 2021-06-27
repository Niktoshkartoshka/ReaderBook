package com.example.readerbook;

import java.io.File;
import java.util.Comparator;

/**
 * Класс, организующий сортировку списка файлов по трем параметрам:
 * алфавиту, дате изменения, размеру.
 */
public class FileComparator {

    /**
     * Сортировка файлов по размеру.
     */
    public static Comparator<File> compareFilesBySize = new Comparator<File>() {
        /**
         * Сравнивает два файла для упорядочения по размеру.
         * @param f1 Первый файл для сравнения.
         * @param f2 Второй файл для сравнения.
         * @return Отрицательное целое число, ноль или положительное целое число,
         *         если значение первого аргумента меньше, равно или больше второго.
         */
        @Override
        public int compare(File f1, File f2) {
            return Long.compare(f1.length(), f2.length());
        }
    };

    /**
     * Сортировка файлов по дате изменения.
     */
    public static Comparator<File> compareFilesByDate = new Comparator<File>() {
        /**
         * Сравнивает два файла для упорядочения по дате изменения.
         * @param f1 Первый файл для сравнения.
         * @param f2 Второй файл для сравнения.
         * @return Отрицательное целое число, ноль или положительное целое число,
         *         если значение первого аргумента меньше, равно или больше второго.
         */
        @Override
        public int compare(File f1, File f2) {
            return Long.compare(f1.lastModified(), f2.lastModified());
        }
    };

    /**
     * Сортировка файлов по алфавиту.
     */
    public static Comparator<File> compareFilesByName = new Comparator<File>() {
        /**
         * Сравнивает два файла для упорядочения по алфавиту.
         * @param f1 Первый файл для сравнения.
         * @param f2 Второй файл для сравнения.
         * @return Отрицательное целое число, ноль или положительное целое число,
         *         если значение первого аргумента меньше, равно или больше второго.
         */
        @Override
        public int compare(File f1, File f2) {
            String fileName1 = f1.getName().toUpperCase();
            String fileName2 = f2.getName().toUpperCase();
            return fileName1.compareTo(fileName2);
        }
    };
}
