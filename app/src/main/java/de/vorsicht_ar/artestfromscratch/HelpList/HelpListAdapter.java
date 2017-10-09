package de.vorsicht_ar.artestfromscratch.HelpList;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.vorsicht_ar.artestfromscratch.R;

/**
 * Created by Caro on 04.10.2017.
 */

public class HelpListAdapter extends ArrayAdapter {

    private ArrayList<HelpListItem> helpList = new ArrayList<>();
    public HelpListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<HelpListItem> objects) {
        super(context, resource, objects);
        this.helpList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.help_list_items, null);
        TextView hintText = (TextView) v.findViewById(R.id.hint_text);
        ImageView hintImage = (ImageView) v.findViewById(R.id.hint_image);
        hintText.setText(helpList.get(position).getName());
        hintImage.setImageResource(helpList.get(position).getDrawable());

        return v;
    }
}
