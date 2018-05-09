package wowsome.com.wowsome.model.loadImages;

import java.util.ArrayList;

/**
 * Created by Rajesh Kumar on 09-05-2018.
 */
public class ImageRoot {
    private String regionCode;

    private String etag;

    private ArrayList<Items> items;



    private String nextPageToken;

    private String kind;

    public String getRegionCode ()
    {
        return regionCode;
    }

    public void setRegionCode (String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public ArrayList<Items> getItems ()
    {
        return items;
    }

    public void setItems (ArrayList<Items> items)
    {
        this.items = items;
    }



    public String getNextPageToken ()
    {
        return nextPageToken;
    }

    public void setNextPageToken (String nextPageToken)
    {
        this.nextPageToken = nextPageToken;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [regionCode = "+regionCode+", etag = "+etag+", items = "+items+", pageInfo = , nextPageToken = "+nextPageToken+", kind = "+kind+"]";
    }
}
