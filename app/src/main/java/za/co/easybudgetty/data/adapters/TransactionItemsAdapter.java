package za.co.easybudgetty.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

import za.co.easybudgetty.R;
import za.co.easybudgetty.data.TransactionContract;
import za.co.easybudgetty.data.viewHolders.TransactionItemViewHolder;

/**
 * Created by tyler on 2017/04/11.
 */

public class TransactionItemsAdapter extends RecyclerView.Adapter<za.co.easybudgetty.data.viewHolders.TransactionItemViewHolder> {

    private static final String TAG = TransactionItemsAdapter.class.getCanonicalName();
    private List<TransactionContract.TransactionsEntry> items;

    @Override
    public TransactionItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_transactionlistitem, parent, false);
        return new TransactionItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
