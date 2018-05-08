package wowsome.com.wowsome.model;

/**
 * Created by Admin on 08-Dec-17.
 */

public class QuotesPojo {

    String id, message, image,fav;

    public String getId() {
        return id;
    }

    public String getFav() {
        return fav;
    }

    public void setFav(String fav) {
        this.fav = fav;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
