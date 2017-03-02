package github.android.kizema.githublistrepo.model;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;


@Entity(indexes = {
        @Index(value = "id", unique = true)
})
public class Repo {

    @Id
    @SerializedName("id")
    public String id;

    @NotNull
    @SerializedName("name")
    public String name;

    @SerializedName("stargazers_count")
    public String stargazersCount;

    @SerializedName("updated_at")
    public String updatedAt;

@Generated(hash = 965890687)
public Repo(String id, @NotNull String name, String stargazersCount,
        String updatedAt) {
    this.id = id;
    this.name = name;
    this.stargazersCount = stargazersCount;
    this.updatedAt = updatedAt;
}

@Generated(hash = 1082775620)
public Repo() {
}

public String getId() {
    return this.id;
}

public void setId(String id) {
    this.id = id;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public String getStargazersCount() {
    return this.stargazersCount;
}

public void setStargazersCount(String stargazersCount) {
    this.stargazersCount = stargazersCount;
}

public String getUpdatedAt() {
    return this.updatedAt;
}

public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
}

}
