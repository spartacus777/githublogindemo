package github.android.kizema.githublistrepo;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

import github.android.kizema.githublistrepo.control.BaseController;
import github.android.kizema.githublistrepo.control.NetworkController;
import github.android.kizema.githublistrepo.model.DaoMaster;
import github.android.kizema.githublistrepo.model.DaoSession;

public class App extends Application {

    private static Context appContext;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        appContext = getApplicationContext();
        initGreenDao();
    }

    public static Context getAppContext(){
        return appContext;
    }

    private static void initGreenDao(){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(appContext, "db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }

    public static BaseController getController(){
        //for test purposes we can inject here TestControlled
        return new NetworkController();
    }
}
