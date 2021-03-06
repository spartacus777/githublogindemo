package github.android.kizema.githublistrepo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.android.kizema.githublistrepo.App;
import github.android.kizema.githublistrepo.R;
import github.android.kizema.githublistrepo.adapters.RepoAdapter;
import github.android.kizema.githublistrepo.events.RepoEvent;
import github.android.kizema.githublistrepo.util.AppRecyclerView;
import github.android.kizema.githublistrepo.util.SessionManager;

public class MainActivity extends BaseActivity {

    public static Intent createIntent(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        return intent;
    }

    @BindView(R.id.rvRepos)
    AppRecyclerView rvRepos;

    @BindView(R.id.pbEmpty)
    ProgressBar pbEmpty;

    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;


    private RepoAdapter repoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupUI();
    }

    private void setupUI(){
        repoAdapter = new RepoAdapter();
        rvRepos.setAdapter(repoAdapter);
        LinearLayoutManager mChatLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRepos.setLayoutManager(mChatLayoutManager);
        rvRepos.setHasFixedSize(true);
        rvRepos.setEmptyView(pbEmpty);

        swipeLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorPrimary));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                App.getController().listRepos(SessionManager.getToken(), SessionManager.getUsername(), true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

        App.getController().listRepos(SessionManager.getToken(), SessionManager.getUsername(), getShouldGoToServer());
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(RepoEvent event) {
        swipeLayout.setRefreshing(false);
        if (!event.isSuccess){
            Toast.makeText(this, event.errorMsg, Toast.LENGTH_SHORT).show();
            return;
        }

        repoAdapter.update(event.repos);
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
