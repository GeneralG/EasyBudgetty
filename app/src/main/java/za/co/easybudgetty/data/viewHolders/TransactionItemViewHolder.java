package za.co.easybudgetty.data.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import za.co.easybudgetty.R;

/**
 * Created by tyler on 2017/04/11.
 */

public class TransactionItemViewHolder extends RecyclerView.ViewHolder  {
    public TextView date, amount, location;

    public TransactionItemViewHolder(View view)
    {
        super(view);
        date = (TextView) view.findViewById(R.id.tvTransactionDate);
        amount = (TextView) view.findViewById(R.id.tvAmount);
        location = (TextView) view.findViewById(R.id.tvPurchaseLocation);
    }
}
