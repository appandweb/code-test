
package es.voghdev.prjdagger2.datasource.api.retrofit;

import es.voghdev.prjdagger2.datasource.api.GetUsersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetUsersRetrofitRequest {
    @GET("/")
    Call<GetUsersResponse> getRandomUsers(@Query("results") int maxUsers, @Query("seed") int page);
}
