package github.android.kizema.githublistrepo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.android.kizema.githublistrepo.R;
import github.android.kizema.githublistrepo.model.Repo;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Repo> repos;

    public RepoAdapter() {}

    public void update(List<Repo> conferences){
        this.repos = conferences;
        notifyDataSetChanged();
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvStars)
        TextView tvStars;

        @BindView(R.id.tvDate)
        TextView tvDate;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new RepoViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(final RepoViewHolder holder, final int position) {
        Repo model = repos.get(position);

        holder.tvName.setText(model.name);
        holder.tvStars.setText(model.stargazersCount);
        holder.tvDate.setText(model.updatedAt);
    }

    @Override
    public int getItemCount() {
        if (repos == null){
            return 0;
        }

        return repos.size();
    }

    public void clear(){
        repos.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty(){
        if (repos == null || repos.size() == 0) {
            return true;
        }

        return false;
    }

}


