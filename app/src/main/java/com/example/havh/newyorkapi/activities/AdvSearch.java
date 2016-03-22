package com.example.havh.newyorkapi.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.havh.newyorkapi.R;
import com.example.havh.newyorkapi.models.Article;
import com.example.havh.newyorkapi.models.SearchFilters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdvSearch extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText editBeginDate;
    DatePickerDialog beginDate;
    SimpleDateFormat dateFormat;

    StringBuilder query;

    Button save;

    Spinner sort;

    SearchFilters moreFilter;

    CheckBox cbArts, cbFashion, cbSport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adv_search);

        query = new StringBuilder();


        sort = (Spinner) findViewById(R.id.spinner_sort);

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        dateFormat = new SimpleDateFormat("yyyymmdd");

        editBeginDate = (EditText) findViewById(R.id.edit_dialog);
        editBeginDate.setInputType(InputType.TYPE_NULL);
        editBeginDate.requestFocus();
        editBeginDate.setOnClickListener(this);

        setDateTime();

        cbArts = (CheckBox) findViewById(R.id.cb_arts);

        cbFashion = (CheckBox) findViewById(R.id.cb_fashion);

        cbSport = (CheckBox) findViewById(R.id.cb_sport);


    }


    @Override
    public void onClick(View v) {
        if (v == editBeginDate) {
            beginDate.show();
        } else if (v == save) {
            moreFilter = new SearchFilters();
            StringBuilder newsDesk = new StringBuilder();
            if (cbArts.isChecked()) {
                newsDesk.append(cbArts.getText());
            }
            if (cbFashion.isChecked()) {
                newsDesk.append(cbFashion.getText());
            }
            if (cbSport.isChecked()) {
                newsDesk.append(cbSport.getText());
            }
            moreFilter.setNewsDesk(newsDesk.toString().toLowerCase());
            moreFilter.setBeginDate(Long.parseLong(editBeginDate.getText().toString().toLowerCase()));
            Intent saveData = new Intent();
            saveData.putExtra("FILTERS", moreFilter);
            setResult(MainActivity.REQUEST_CODE, saveData);
            finish();
        }
    }

    private void setDateTime() {
        Calendar calendar = Calendar.getInstance();
        beginDate = new DatePickerDialog(AdvSearch.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editBeginDate.setText(dateFormat.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString().toLowerCase();
        moreFilter.setSort(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        moreFilter.setSort(parent.getSelectedItem().toString());
    }
}
