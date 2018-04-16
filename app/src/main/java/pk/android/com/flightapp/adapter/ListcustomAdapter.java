package pk.android.com.flightapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

import pk.android.com.flightapp.R;
import pk.android.com.flightapp.model.ListItems;

/**
 * Created by pc on 27-Feb-18.
 */

public class ListcustomAdapter extends ArrayAdapter<ListItems> {
    private Context context;
    private ArrayList<ListItems> mListitems;

    public ListcustomAdapter(Context context, int resource, ArrayList<ListItems> objects) {
        super(context, resource, objects);
        this.context = context;
        this.mListitems = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View itemView = convertView;
        if (itemView == null)

        {
            LayoutInflater inflater = LayoutInflater.from(context);
            itemView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.flightNumber = itemView.findViewById(R.id.text_flight_number);
            holder.dateTime = itemView.findViewById(R.id.text_date);


            itemView.setTag(holder);
        } else {
            holder = (ViewHolder) itemView.getTag();
        }

        ListItems item = mListitems.get(position);

        holder.flightNumber.setText(String.format( "%s", item.getFlightNumber()));
        holder.dateTime.setText(String.format("%s",item.getDateTime()));


        return itemView;
    }


    private class ViewHolder {

        private TextView dateTime;
        private TextView flightNumber;


    }


}