package za.co.easybudgetty.data.adapters;

import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.ArrayList;

import za.co.easybudgetty.R;
import za.co.easybudgetty.data.BanksContract;
import za.co.easybudgetty.data.helpers.DBManager;
import za.co.easybudgetty.data.viewHolders.BankItemViewHolder;

/**
 * Created by tyler on 2017/05/04.
 */

public class BankItemAdapter implements SpinnerAdapter {

    private static final String TAG = BankItemAdapter.class.getCanonicalName();
    private ArrayList<Object> items;

    public BankItemAdapter(DBManager db)
    {
        BanksContract bc = new BanksContract();
        items = bc.getEntries(db);
    }

    public BankItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_banklistitem, parent, false);
        return new BankItemViewHolder(itemView);
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        Log.d(TAG,  "Method Not implemented");
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        Log.d(TAG,  "Method Not implemented");
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        BanksContract.BanksEntry b = (BanksContract.BanksEntry) items.get(position);
        return b;
    }

    @Override
    public long getItemId(int position) {
        BanksContract.BanksEntry b = (BanksContract.BanksEntry) items.get(position);
        return b.getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView text = new TextView(view.getContext());
        text.setText(items.get(position).toString());
        return text;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        if (items == null)
        {
            return true;
        }
        else
        {
            if (items.size() == 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    /*@Override
    public void onBindViewHolder(BankItemViewHolder holder, int position) {
        Log.d(TAG, "Getting Item: " + String.valueOf(position));
        BanksContract.BanksEntry entry = (BanksContract.BanksEntry)items.get(position);
        holder.bankNames();
        Log.d(TAG, "Binding: " + entry.getName());
    }*/

}
