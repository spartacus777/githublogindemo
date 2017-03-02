package github.android.kizema.githublistrepo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubHelper {

    private static GithubHelper instance;

    private GitHubService service;

    public static synchronized GithubHelper getInstance(){
        if (instance == null){
            instance = new GithubHelper();
        }

        return instance;
    }

    private GithubHelper(){
        initRetrofit();
    }

    private void initRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);
    }

    public GitHubService getService(){
        return service;
    }
}
