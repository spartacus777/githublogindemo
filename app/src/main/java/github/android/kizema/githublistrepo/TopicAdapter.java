package github.android.kizema.githublistrepo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import github.android.kizema.githublistrepo.model.Repo;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<Repo> topics;

    public TopicAdapter(List<Repo> conferences) {
        this.topics = conferences;
    }

    public TopicAdapter() {
    }

    public void update(List<Repo> conferences){
        this.topics = conferences;
        notifyDataSetChanged();
    }

    public static class TopicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName)
        TextView tvName;

        @BindView(R.id.tvStars)
        TextView tvStars;

        @BindView(R.id.tvDate)
        TextView tvDate;

        public TopicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new TopicViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, final int position) {
        Repo model = topics.get(position);

        holder.tvName.setText(model.name);
        holder.tvStars.setText(model.stargazersCount);
        holder.tvDate.setText(model.updatedAt);
    }

    @Override
    public int getItemCount() {
        if (topics == null){
            return 0;
        }

        return topics.size();
    }

    public void clear(){
        topics.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty(){
        if (topics == null || topics.size() == 0) {
            return true;
        }

        return false;
    }

}


