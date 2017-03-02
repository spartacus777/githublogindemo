package github.android.kizema.githublistrepo;

import java.util.List;

import github.android.kizema.githublistrepo.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Header("Authorization") String authorization, @Path("user") String user);


    @GET("authorizations")
    Call<Void> login(@Header("Authorization") String authorization);

}
