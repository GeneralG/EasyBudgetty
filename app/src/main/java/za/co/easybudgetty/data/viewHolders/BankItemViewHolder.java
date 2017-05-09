package za.co.easybudgetty.data.viewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Spinner;

import java.util.List;

import za.co.easybudgetty.data.adapters.BankItemAdapter;
import za.co.easybudgetty.data.helpers.DBManager;

/**
 * Created by tyler on 2017/05/04.
 */

public class BankItemViewHolder extends RecyclerView.ViewHolder {

    public Spinner bankNames;

    private List<Object> Banks;

    public BankItemViewHolder(View v)
    {
        super(v);
        DBManager db = new DBManager(v.getContext());
        BankItemAdapter bia = new BankItemAdapter(db);
        bankNames.setAdapter(bia);
    }
}
