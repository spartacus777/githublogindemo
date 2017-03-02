package github.android.kizema.githublistrepo.model;

import java.util.List;

import github.android.kizema.githublistrepo.App;

public class RepoHelper {

    public static void save(List<Repo> repoList){
        App.getDaoSession().getRepoDao().insertOrReplaceInTx(repoList);
    }

    public static List<Repo> getAll(){
        DaoSession daoSession = App.getDaoSession();
        RepoDao confDao = daoSession.getRepoDao();
        return confDao.queryBuilder().list();
    }
}
