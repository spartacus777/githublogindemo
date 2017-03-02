package github.android.kizema.githublistrepo.control;

import org.greenrobot.eventbus.EventBus;

import github.android.kizema.githublistrepo.events.LoginEvent;
import github.android.kizema.githublistrepo.events.RepoEvent;
import github.android.kizema.githublistrepo.model.Repo;
import github.android.kizema.githublistrepo.model.RepoHelper;
import github.android.kizema.githublistrepo.util.SessionManager;

/**
 * Can be used for test purposes, instead network-based controlled
 */
public class TestController implements BaseController{

    @Override
    public void login(final String header, final String name){
        SessionManager.saveToken(header, name);

        LoginEvent loginEvent = new LoginEvent();
        EventBus.getDefault().postSticky(loginEvent);
    }

    @Override
    public void listRepos(final String header, final String name, boolean shouldGoToServer){

        RepoEvent loginEvent = new RepoEvent();
        loginEvent.repos = RepoHelper.getAll();

        EventBus.getDefault().post(loginEvent);

        if (!shouldGoToServer){
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                RepoEvent loginEvent = new RepoEvent();
                loginEvent.repos = RepoHelper.getAll();

                Repo demo = new Repo();
                demo.setId("demoId");
                demo.setName("Demo name");
                loginEvent.repos.add(demo);
            }
        });

    }

}

