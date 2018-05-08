package wowsome.com.wowsome.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Rajesh kumar on 13-07-2017.
 */

public interface ApiService  {



    @GET
    Call<String> getApiResultCity(@Url String action, @QueryMap Map<String, String> fields);

    @FormUrlEncoded
    @POST
    Call<String> getPost(@Url String url, @FieldMap Map<String, String> fields);

    @GET
    Observable<String> getData(@Url String action, @QueryMap Map<String, String> fields);

}
