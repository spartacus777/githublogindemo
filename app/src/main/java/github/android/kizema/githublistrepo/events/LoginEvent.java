package github.android.kizema.githublistrepo.events;

public class LoginEvent extends BaseEvent {

    public LoginEvent(){
        super();
    }

    public LoginEvent(String errorMsg){
        super(errorMsg);
    }

}
