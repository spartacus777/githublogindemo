package github.android.kizema.githublistrepo.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("stargazers_count")
    public String stargazersCount;

    @SerializedName("updated_at")
    public String updatedAt;
}
