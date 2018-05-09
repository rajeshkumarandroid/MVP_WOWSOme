package wowsome.com.wowsome.model.loadImages;

/**
 * Created by Rajesh Kumar on 09-05-2018.
 */
public class Thumbnails {


    private High high;





    public High getHigh ()
    {
        return high;
    }

    public void setHigh (High high)
    {
        this.high = high;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [default = , high = "+high+", medium = ]";
    }
}
