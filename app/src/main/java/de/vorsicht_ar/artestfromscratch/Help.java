package de.vorsicht_ar.artestfromscratch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Caro on 28.09.2017.
 */

public class Help extends Activity{
    private ListView listView;
    private TextView hintText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_window);

        // customize size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm); //stores new metrics

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8),(int) (height*0.8));

        // setup list view
        listView = (ListView)findViewById(R.id.list_hints);
        hintText = (TextView)findViewById(R.id.text_hint);
        String[] values = new String[]{"Hint1", "Hint2", "Hint3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,
                android.R.id.text1, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                hintText.setText("Hier ist der Hinweis für " + listView.getItemAtPosition(position).toString());
            }
        });
    }
}
