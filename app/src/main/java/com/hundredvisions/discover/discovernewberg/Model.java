package com.hundredvisions.discover.discovernewberg;

/**
 * Created by winikkc on 2/28/18.
 */

public class Model {
    public static final int IMAGE_TYPE = 1;
    public String title, subtitle, Image;
    public int type;

    public Model(int mtype, String mtitle, String msubtitle, String image) {
        this.title = mtitle;
        this.subtitle = msubtitle;
        this.type = mtype;
        this.Image = image;
    }

}
