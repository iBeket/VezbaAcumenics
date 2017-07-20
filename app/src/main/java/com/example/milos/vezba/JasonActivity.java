package com.example.milos.vezba;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Created by Milos on 10-Jul-17.
 */

public class JasonActivity extends AppCompatActivity {
    final String TAG = "JSON";
    private ProgressDialog dialog;
    private static String url = "https://raw.githubusercontent.com/danieloskarsson/mobile-coding-exercise/master/items.json";
    private ListView lv;
    ArrayList<JasonModel> list = new ArrayList<>();
    private CustomAdapter2 adapter2;
    private EditText search;
    private TextView entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jasn);
        lv = (ListView) findViewById(R.id.listjson);
        search = (EditText)findViewById(R.id.search);
        entries = (TextView)findViewById(R.id.entries);
        new getData().execute();
    }

    public class getData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(JasonActivity.this);
            dialog.setMessage("Please wait");
            dialog.setCancelable(false);
            dialog.show();

        }

        @Override
        protected Void doInBackground(Void... params) {
            JSONParser parser = new JSONParser();

            String jsonStr = parser.makeServiceCall(url);
            if (jsonStr != null) {
                try {

                    JSONArray jsonArray = new JSONArray(jsonStr);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        JasonModel model = new JasonModel();
                        model.setImageJson(obj.getString("image"));
                        model.setDescription(obj.getString("description"));
                        model.setTitle(obj.getString("title"));
                        list.add(model);

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
            final ArrayList<JasonModel>pomList = new ArrayList<JasonModel>();
            pomList.addAll(list);
            entries.setText("Found: "+list.size()+" entries.");

            search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    list.clear();
                    for (JasonModel jasonModel : pomList){
                        if(jasonModel.getTitle().toString().toLowerCase().contains(s.toString().toLowerCase())){
                            list.add(jasonModel);
                            // lv.setAdapter(adapter2);
                            adapter2.notifyDataSetChanged();
                        } else {
                            lv.setAdapter(adapter2);
                        }
                    }

                    if (list.size()==0){
                        entries.setText("Found no entries.");
                    } else if (list.size()==1){
                        entries.setText("Found: 1 entry.");
                    } else {
                        entries.setText("Found: " + list.size() + " entries.");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //sorting the list
            Collections.sort(list, new Comparator<JasonModel>() {
                @Override
                public int compare(JasonModel o1, JasonModel o2) {
                    return o1.getTitle().compareToIgnoreCase(o2.getTitle());
                }
            });
            //reversing list
            //  Collections.reverse(list);

            // Updating parsed JSON data into ListView
            adapter2 = new CustomAdapter2(getApplicationContext(), R.layout.jason_list_item, list);
            lv.setAdapter(adapter2);

        }
    }
}