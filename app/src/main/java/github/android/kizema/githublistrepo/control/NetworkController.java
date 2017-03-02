package github.android.kizema.githublistrepo.control;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import github.android.kizema.githublistrepo.network.GithubHelper;
import github.android.kizema.githublistrepo.util.Logger;
import github.android.kizema.githublistrepo.util.SessionManager;
import github.android.kizema.githublistrepo.events.LoginEvent;
import github.android.kizema.githublistrepo.events.RepoEvent;
import github.android.kizema.githublistrepo.model.Repo;
import github.android.kizema.githublistrepo.model.RepoHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkController implements BaseController{

    private static final int SUCCESS_CODE = 200;

    public NetworkController(){}

    @Override
    public void login(final String header, final String name){
        Call<Void> user = GithubHelper.getInstance().getService().login(header);
        Log.d(Logger.TAG, "REQUEST: " + user.request().url().toString());

        user.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful() && response.code() == SUCCESS_CODE) {
                    Log.d(Logger.TAG, "onResponse Response: " + response.toString());
                    SessionManager.saveToken(header, name);

                    LoginEvent loginEvent = new LoginEvent();

                    EventBus.getDefault().postSticky(loginEvent);
                } else {
                    onFailure(call, new Throwable("Wrong credentials"));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(Logger.TAG, "onFailure Response: " + t.toString());

                LoginEvent loginEvent = new LoginEvent(t.toString());

                EventBus.getDefault().postSticky(loginEvent);
            }
        });
    }

    @Override
    public void listRepos(final String header, final String name, boolean shouldGoToServer){

        RepoEvent loginEvent = new RepoEvent();
        loginEvent.repos = RepoHelper.getAll();

        EventBus.getDefault().post(loginEvent);

        if (!shouldGoToServer){
            return;
        }

        Call<List<Repo>>  user = GithubHelper.getInstance().getService().listRepos(header, name);
        Log.d(Logger.TAG, "REQUEST: " + user.request().url().toString());

        user.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful() && response.code() == SUCCESS_CODE) {
                    Log.d(Logger.TAG, "Response: " + response.toString());

                    RepoHelper.save(response.body());

                    RepoEvent loginEvent = new RepoEvent();
                    loginEvent.repos = response.body();

                    EventBus.getDefault().post(loginEvent);
                } else {
                    onFailure(call, new Throwable("Wrong credentials"));
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.d(Logger.TAG, "Response: " + t.toString());

                RepoEvent loginEvent = new RepoEvent(t.toString());

                EventBus.getDefault().post(loginEvent);
            }
        });
    }

}
