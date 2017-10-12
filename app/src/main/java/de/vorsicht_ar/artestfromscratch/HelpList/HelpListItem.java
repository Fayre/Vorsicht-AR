package de.vorsicht_ar.artestfromscratch.HelpList;

/**
 * Created by Caro on 09.10.2017.
 */

public class HelpListItem {

    /**
     * name of the person to find.
     */
    private String name;

    /**
     * R.id of the drawable of the icon for the person.
     */
    private int drawable;

    /**
     * true if the user found the person, otherwise false.
     */
    private boolean found;

    public HelpListItem(String name, int drawable){
        this.name = name;
        this.drawable = drawable;
        this.found = false;
    }



    public String getName() {
        return name;
    }

    public int getDrawable() {
        return drawable;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
