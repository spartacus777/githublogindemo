package github.android.kizema.githublistrepo.events;

public class BaseEvent {
    public boolean isSuccess;
    public String errorMsg;

    public BaseEvent(){
        isSuccess = true;
    }

    public BaseEvent(String errorMsg){
        isSuccess = false;
        this.errorMsg = errorMsg;
    }
}
