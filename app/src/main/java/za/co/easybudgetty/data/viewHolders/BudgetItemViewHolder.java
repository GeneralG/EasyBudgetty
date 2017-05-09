package za.co.easybudgetty.data.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import za.co.easybudgetty.R;

/**
 * Created by tyler on 2017/04/07.
 */

public class BudgetItemViewHolder extends RecyclerView.ViewHolder {

    public TextView title, total, amountSpent, amountRemaining;

    public BudgetItemViewHolder(View view)
    {
        super(view);
        title = (TextView) view.findViewById(R.id.title);
        total = (TextView) view.findViewById(R.id.total);
        amountSpent = (TextView) view.findViewById(R.id.amountSpent);
        amountRemaining = (TextView) view.findViewById(R.id.amountRemaining);
    }
}
