package pk.android.com.flightapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import pk.android.com.flightapp.adapter.ListcustomAdapter;
import pk.android.com.flightapp.data.DatabaseHelper;
import pk.android.com.flightapp.model.ListItems;

public class FlightListActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    ArrayList<ListItems> mArray = new ArrayList<>();
    ListcustomAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);
        listView = (ListView) findViewById(R.id.flight_list);
        Button addFlight = (Button) findViewById(R.id.add_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        addFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), AddFlightActivity.class));
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        myDB = new DatabaseHelper(this);
        mArray = myDB.getListContents();
        setupRecycler();

    }

    private void setupRecycler() {


        adapter = new ListcustomAdapter(this, R.layout.list_item, mArray);
        Collections.reverse(mArray);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (myDB.removeItem(mArray.get(position))) {
                    mArray.remove(mArray.get(position));
                    adapter.notifyDataSetChanged();
                    Toast.makeText(getBaseContext(), "item Removed", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
