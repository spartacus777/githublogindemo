package github.android.kizema.githublistrepo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import github.android.kizema.githublistrepo.R;
import github.android.kizema.githublistrepo.control.Controller;
import github.android.kizema.githublistrepo.events.LoginEvent;
import github.android.kizema.githublistrepo.util.SessionManager;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    EditText etName;

    @BindView(R.id.etPassword)
    EditText etPassword;

    @BindView(R.id.tvNext)
    TextView tvNext;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static Intent createIntent(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (SessionManager.getToken() != null){
            startMainActivity();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @OnClick(R.id.tvNext)
    public void onNextClicked() {

        String credentials = etName.getText().toString()  + ":" + etPassword.getText().toString();
        String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        final String header = "Basic " + base64EncodedCredentials;

        Controller.getInstance().login(header, etName.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
    }

    private void startMainActivity(){
        Intent intent = MainActivity.createIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(LoginEvent event) {
        progressBar.setVisibility(View.GONE);

        if (event.isSuccess){
            startMainActivity();
        } else {
            Toast.makeText(this, event.errorMsg, Toast.LENGTH_SHORT).show();
        }

        LoginEvent stickyEvent = EventBus.getDefault().getStickyEvent(LoginEvent.class);
        if(stickyEvent != null) {
            EventBus.getDefault().removeStickyEvent(stickyEvent);
        }
    }
}
