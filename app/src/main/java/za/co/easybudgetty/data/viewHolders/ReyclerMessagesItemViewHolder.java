package za.co.easybudgetty.data.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import za.co.easybudgetty.R;
import za.co.easybudgetty.data.adapters.RecyclerMessageItemsAdapter;

/**
 * Created by tyler on 2017/05/08.
 */

public class ReyclerMessagesItemViewHolder extends RecyclerView.ViewHolder {

    public ExpandableListView elvBudgets;
    public Button btnDebitor, btnAmount;

    public ReyclerMessagesItemViewHolder(View view) {
        super(view);
        btnAmount = (Button)view.findViewById(R.id.btnAmount);
        btnDebitor = (Button)view.findViewById(R.id.btnDebitor);
        elvBudgets = (ExpandableListView)view.findViewById(R.id.elvBudgets);

        RecyclerMessageItemsAdapter rmia = new RecyclerMessageItemsAdapter();
        elvBudgets.setAdapter(rmia);

        //get the selected ID and set it so that the user can see the correct budget.
        elvBudgets.getSelectedId();

    }
}


