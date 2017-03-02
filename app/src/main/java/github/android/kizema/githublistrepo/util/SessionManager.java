package github.android.kizema.githublistrepo.util;

import android.content.Context;
import android.content.SharedPreferences;

import github.android.kizema.githublistrepo.App;

public class SessionManager {

    private static final String SESSION_ID = "sessionId";
    private static final String USERNAME = "username";
    private static final String PREFERENCE_NAME = "PREFERENCE_NAME";

    public static void saveToken(String token, String userName){
        SharedPreferences sharedPref = App.getAppContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_ID, token);
        editor.putString(USERNAME, userName);
        editor.commit();
    }

    public static String getToken(){
        SharedPreferences sharedPref = App.getAppContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(SESSION_ID, null);
    }

    public static String getUsername(){
        SharedPreferences sharedPref = App.getAppContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(USERNAME, null);
    }

}
