package github.android.kizema.githublistrepo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.android.kizema.githublistrepo.Logger;
import github.android.kizema.githublistrepo.R;
import github.android.kizema.githublistrepo.SessionManager;
import github.android.kizema.githublistrepo.TopicAdapter;
import github.android.kizema.githublistrepo.control.Controller;
import github.android.kizema.githublistrepo.events.RepoEvent;
import github.android.kizema.githublistrepo.model.Repo;

public class MainActivity extends BaseActivity {

    public static Intent createIntent(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        return intent;
    }

    @BindView(R.id.rvRepos)
    RecyclerView rvRepos;

    private TopicAdapter topicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI(){
        topicAdapter = new TopicAdapter();
        rvRepos.setAdapter(topicAdapter);
        LinearLayoutManager mChatLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRepos.setLayoutManager(mChatLayoutManager);
        rvRepos.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        Controller.getInstance().listRepos(SessionManager.getToken(), SessionManager.getUsername());
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    //TODO remive
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(RepoEvent event) {
        if (!event.isSuccess){
            Toast.makeText(this, event.errorMsg, Toast.LENGTH_SHORT).show();
            return;
        }

        for (Repo r : event.repos){
            Log.d(Logger.TAG, r.name);
        }

        topicAdapter.update(event.repos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            SessionManager.saveToken(null, null);
            startLoginActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startLoginActivity(){
        Intent intent = LoginActivity.createIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
