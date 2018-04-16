package pk.android.com.flightapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

import pk.android.com.flightapp.data.DatabaseHelper;
import pk.android.com.flightapp.model.ListItems;

public class AddFlightActivity extends AppCompatActivity
{

    TextView date;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mydb= new DatabaseHelper(this);
        final EditText  etFlightnum =(EditText)findViewById(R.id.edittext_flight_num);
        date=(TextView)findViewById(R.id.tvDate);


        final Calendar myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener datepicker = new DatePickerDialog.OnDateSetListener()
        {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth)
            {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date.setText(DateFormat.getDateInstance().format(myCalendar.getTime()));

            }

        };


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new DatePickerDialog(AddFlightActivity.this, datepicker, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Button addFlight=(Button)findViewById(R.id.add_flight);

        addFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                if(etFlightnum.getText().toString().isEmpty()||date.getText().toString().equalsIgnoreCase("Date"))
                {
                    Toast.makeText(AddFlightActivity.this, "Please input data", Toast.LENGTH_SHORT).show();

                }

                else
                {
                    ListItems item= new ListItems();
                    item.setFlightNumber(etFlightnum.getText().toString());
                    item.setDateTime(date.getText().toString());
                    mydb.addData(item);
                    Toast.makeText(AddFlightActivity.this, "Data succesfully added", Toast.LENGTH_SHORT).show();
                    etFlightnum.setText(null);
                    date.setText("Date");
                }



            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
