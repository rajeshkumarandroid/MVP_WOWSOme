package wowsome.com.wowsome.model.loadImages;

/**
 * Created by Rajesh Kumar on 09-05-2018.
 */
public class Default {
    private String height;

    private String width;

    private String url;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [height = "+height+", width = "+width+", url = "+url+"]";
    }
}
