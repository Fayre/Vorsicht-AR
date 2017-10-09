package de.vorsicht_ar.artestfromscratch.HelpList;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.vorsicht_ar.artestfromscratch.R;

/**
 * Created by Caro on 28.09.2017.
 */

public class Help extends Activity {
    private ListView listView;
    private TextView hintText;
    private ArrayList<HelpListItem> helpList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_window);

        // customize size
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm); //stores new metrics

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * 0.8), (int) (height * 0.8));

        hintText = (TextView) findViewById(R.id.text_hint);

        // setup list view
        listView = (ListView) findViewById(R.id.list_hints);
        helpList.add(new HelpListItem("Hofnärrin", R.drawable.icon_hofnarr));
        helpList.add(new HelpListItem("Bäckerin", R.drawable.icon_farmer));
        helpList.add(new HelpListItem("Dieb", R.drawable.icon_thief));

        HelpListAdapter helpListAdapter = new HelpListAdapter(this, R.layout.help_list_items, helpList);
        listView.setAdapter(helpListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        hintText.setText(R.string.hint0);
                        break;
                    case 1:
                        hintText.setText(R.string.hint1);
                        break;
                    case 2:
                        hintText.setText(R.string.hint2);
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
