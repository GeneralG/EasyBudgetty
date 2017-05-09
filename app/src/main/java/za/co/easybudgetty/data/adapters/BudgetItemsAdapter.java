package za.co.easybudgetty.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import za.co.easybudgetty.R;
import za.co.easybudgetty.data.BudgetsContract;
import za.co.easybudgetty.data.BudgetsContract.BudgetEntry;
import za.co.easybudgetty.data.helpers.DBManager;
import za.co.easybudgetty.data.viewHolders.BudgetItemViewHolder;

/**
 * Created by tyler on 2017/04/07.
 */

public class BudgetItemsAdapter extends RecyclerView.Adapter<za.co.easybudgetty.data.viewHolders.BudgetItemViewHolder>{

    private static final String TAG = BudgetItemsAdapter.class.getCanonicalName();
    private List<BudgetEntry> items;

    public BudgetItemsAdapter(DBManager db) {
        BudgetsContract bc = new BudgetsContract();
        items = bc.getEntriesAsBudgetEntry(db);
    }

    @Override
    public BudgetItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_budgetlistitem, parent, false);
        return new BudgetItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BudgetItemViewHolder holder, int position) {
        Log.d(TAG, "Getting Item: " + String.valueOf(position));
        BudgetEntry entry = items.get(position);
        holder.title.setText(entry.getName());
        holder.amountRemaining.setText(String.valueOf(entry.getAmount() - entry.getTransactionsSummed()));
        holder.total.setText(String.valueOf(entry.getAmount()));
        holder.amountSpent.setText(String.valueOf(entry.getTransactionsSummed()));
        Log.d(TAG, "Binding: " + entry.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
