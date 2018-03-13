package com.hundredvisions.discover.discovernewberg;

/**
 * Created by winikkc on 2/28/18.
 */

public class Model {
    public static final int IMAGE_TYPE = 1;
    public String title, subtitle, Image;
    public int id, type;

    public Model(int mid, int mtype, String mtitle, String msubtitle, String image) {
        this.id = mid;
        this.title = mtitle;
        this.subtitle = msubtitle;
        this.type = mtype;
        this.Image = image;
    }

}
