package github.android.kizema.githublistrepo.control;

public interface BaseController {

    void login(final String header, final String name);

    void listRepos(final String header, final String name, boolean shouldGoToServer);
}
