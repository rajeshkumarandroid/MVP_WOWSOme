package wowsome.com.wowsome.utils;

import android.content.Context;
import android.net.NetworkInfo;

/**
 * Created by Rajesh Kumar on 09-05-2018.
 */
public class ConnectivityManager {

    public static ConnectivityManager connectivityManager;


    public static ConnectivityManager getInstance(){
        if(null==connectivityManager){
            connectivityManager = new ConnectivityManager();
        }

        return connectivityManager;
    }






    public boolean isConnectingToInternet(Context context) {
        android.net.ConnectivityManager connectivity = (android.net.ConnectivityManager)
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (NetworkInfo anInfo : info)
                    if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }
}
