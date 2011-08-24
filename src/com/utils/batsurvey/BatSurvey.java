package com.utils.batsurvey;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BatSurvey extends ListActivity {
    // Links a list of Surveys to a ListView of survey_item views.
    private class SurveyAdapter extends ArrayAdapter<Survey> {
        private ArrayList<Survey> items;
        
        public SurveyAdapter(Context context, int textViewResourceId, ArrayList<Survey> items){
            super(context, textViewResourceId, items);
            this.items = items;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.survey_item, null);
            }
            Survey s = items.get(position);
            if (s != null) {
                TextView t = (TextView) v.findViewById(R.id.survey_name);
                if (t != null) {
                    t.setText(s.getName());
                }
            }
            return v;
        }
    }
    
    private ArrayList<Survey> surveys = null;
    private SurveyAdapter adapter;
    
    private void makeToast(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
    
    private void createSurvey() {
        // Create a dialog for entering the survey name,
        // then add the created survey to the list.
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.enter_text);
        
        final EditText input = new EditText(this);
        alert.setView(input);
        
        alert.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String name = input.getText().toString();
                if (name.length() != 0) {
                    Survey s = new Survey();
                    s.setName(name);
                    surveys.add(s);
                    adapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichBUtton) {
                dialog.cancel();
            }
        });
        alert.show();
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.surveys = new ArrayList<Survey>();
        this.adapter = new SurveyAdapter(this, R.layout.survey_item, surveys);
        setListAdapter(this.adapter);
        
        // Add handler for the "new" button.
        final Button new_button = (Button) findViewById(R.id.new_button);
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSurvey();
            }
        });
        
        // Add handler for the "delete" button.
        final Button delete_button = (Button) findViewById(R.id.delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
    }
}