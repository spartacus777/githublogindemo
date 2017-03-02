package github.android.kizema.githublistrepo.events;

import java.util.List;

import github.android.kizema.githublistrepo.model.Repo;

public class RepoEvent extends BaseEvent{
    public List<Repo> repos;
}
