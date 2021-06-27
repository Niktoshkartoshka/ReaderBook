package com.example.readerbook;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class FB2 extends AppCompatActivity  {

    /*Context context;
    // FB2.ViewHolder viewHolder;
    // ArrayList<File> arrlist_fb2;

    //@SuppressLint("StaticFieldLeak")
    int position = -1;
    TextView textView = findViewById(R.id.textView2);

   private void displayFB2() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler(getApplicationContext());
        parser.parse(new File(Environment.getExternalStorageDirectory().toString()), handler);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout mainLayout = (LinearLayout) inflater.inflate(R.layout.activity_fb2viewer,null);
        setContentView(mainLayout);

        setContentView(R.layout.activity_fb2viewer);
        position = getIntent().getIntExtra("position", -1);
        *//*try
        {
            displayFB2();
        }
        catch(Exception pce) { Log.e("AndroidTestsActivity", "PCE "+pce.getMessage()); }*//*

        //textView = findViewById(R.id.textView2);
        textView.setMovementMethod(new ScrollingMovementMethod());
    }


    public class XMLHandler extends DefaultHandler {

        private ArrayList<TextView> theViews;
        private Context theContext;
        public XMLHandler(Context cont) {
            super();
            theViews = new ArrayList<TextView>();
            theContext = cont;
        }

        public String lastElementName, ElementName;


        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (lastElementName == null) {
                lastElementName = qName;
            }
            ElementName = qName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((!ElementName.equals(lastElementName))&&(qName.equals(ElementName)))
            {
                lastElementName = ElementName;
            }
            if (ElementName.equals("p")) {
                textView.setText("\n");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            if (ElementName.equals("first-name")) {
                if (lastElementName.equals("author")) {
                    information = "Автор: "+ information+" ";
                    textView.setText(information);}
                else if (lastElementName.equals("translator")) {
                    information = "Переводчик: "+ information+" ";
                    textView.setText(information);}
            }

            if (ElementName.equals("last-name")) {
                if (lastElementName.equals("author") || lastElementName.equals("translator")) {
                    information += "\n\n";
                    textView.setText(information);
                }
            }

            if (ElementName.equals("book-title")) {
                textView.setTextSize(16);
                information += "\n\n";
                textView.setText(information);
                textView.setTextSize(12);
            }

            if (ElementName.equals("annotation")) {
                information += "\n\n";
                textView.setText(information);
            }

            if (ElementName.equals("strong")) {
                textView.setTextSize(16);
                information += "\n\n";
                textView.setText(information);
                textView.setTextSize(12);
            }
        }
    }*/
}
