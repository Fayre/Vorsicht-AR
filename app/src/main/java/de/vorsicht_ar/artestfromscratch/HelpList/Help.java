package de.vorsicht_ar.artestfromscratch.HelpList;


import android.app.Activity;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import de.vorsicht_ar.artestfromscratch.R;

/**
 * Created by Caro on 28.09.2017.
 */

public class Help extends Activity {
    /**
     * the view containing the list.
     */
    private ListView listView;

    /**
     * the view containing the text for the specific person.
     */
    private TextView hintText;

    /**
     * list of all items.
     */
    private ArrayList<HelpListItem> helpList = new ArrayList<>();

    private final int HOFNAERRIN = 0;
    private final int BAECKERIN = 1;
    private final int DIEB = 2;

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
                // get current item
                HelpListItem item = helpList.get(position);
                ImageView status = (ImageView) view.findViewById(R.id.hint_statusicon);

                // set text depending on item and status
                switch (position) {
                    case HOFNAERRIN:
                        if (item.isFound()) {
                            hintText.setText(R.string.text0);
                            status.setImageResource(R.drawable.icon_help);
                        } else {
                            hintText.setText(R.string.hint0);
                            status.setImageResource(R.drawable.icon_tick);
                        }
                        item.setFound(!item.isFound());
                        break;
                    case BAECKERIN:
                        if (item.isFound()) {
                            hintText.setText(R.string.text1);
                            status.setImageResource(R.drawable.icon_help);
                        } else {
                            hintText.setText(R.string.hint1);
                            status.setImageResource(R.drawable.icon_tick);
                        }
                        item.setFound(!item.isFound());
                        break;
                    case DIEB:
                        if (item.isFound()) {
                            hintText.setText(R.string.text2);
                            status.setImageResource(R.drawable.icon_help);
                        } else {
                            hintText.setText(R.string.hint2);
                            status.setImageResource(R.drawable.icon_tick);
                        }
                        item.setFound(!item.isFound());
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        boolean[] hintStatus = savedInstanceState.getBooleanArray("hintStatus");
        if(hintStatus != null && hintStatus.length != 0){
            for(int i = 0; i < hintStatus.length; i++){
                helpList.get(i).setFound(hintStatus[i]);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        boolean[] hintStatus = new boolean[helpList.size()];
        for(int i = 0; i < helpList.size(); i++){

            hintStatus[i] = helpList.get(i).isFound();
        }
        outState.putBooleanArray("hintStatus", hintStatus);

        super.onSaveInstanceState(outState);
    }
}
