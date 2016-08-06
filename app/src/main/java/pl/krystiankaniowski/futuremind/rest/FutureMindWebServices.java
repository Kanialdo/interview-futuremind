package pl.krystiankaniowski.futuremind.rest;

import pl.krystiankaniowski.futuremind.model.rest.DataContainer;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FutureMindWebServices {

    // =============================================================================================
    //      FINALS
    // =============================================================================================

    String URL = "http://pinky.futuremind.com/";

    // =============================================================================================
    //      INTERFACE
    // =============================================================================================

    @GET("~dpaluch/{test}/")
    Call<DataContainer> getData(@Path("test") String testName);

}
