package de.vorsicht_ar.artestfromscratch.HelpList;

/**
 * Created by Caro on 09.10.2017.
 */

public class HelpListItem {

    private String name;
    private int drawable;

    public HelpListItem(String name, int drawable){
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public int getDrawable() {
        return drawable;
    }
}
