package wowsome.com.wowsome.model;

/**
 * Created by Rajesh Kumar on 08-05-2018.
 */
public class Clouds {
    private String all;

    public String getAll ()
    {
        return all;
    }

    public void setAll (String all)
    {
        this.all = all;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [all = "+all+"]";
    }
}
