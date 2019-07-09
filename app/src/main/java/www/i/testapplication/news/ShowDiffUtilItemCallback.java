package www.i.testapplication.news;

import android.support.v7.util.DiffUtil;

public class ShowDiffUtilItemCallback extends DiffUtil.ItemCallback<Show> {
    @Override
    public boolean areItemsTheSame(Show oldItem, Show newItem) {
        return oldItem.title() == newItem.title();
    }

    @Override
    public boolean areContentsTheSame(Show oldItem, Show newItem) {
        return oldItem.description() == newItem.description();
    }
}
