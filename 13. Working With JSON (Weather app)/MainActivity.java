package com.jabarian.adabazi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    public static TextView txtLog;
    public static EditText txtCity;
    public static Button btnFetch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLog = findViewById(R.id.txtLog);
        txtCity = findViewById(R.id.txtCity);
        btnFetch = findViewById(R.id.btnFetch);


}
      public static void Done(String result)
      {

          try {
              JSONObject root = new JSONObject(result);
              JSONObject current = root.getJSONObject("current");
              String weather_descriptions = current.getJSONArray("weather_descriptions").getString(0);
              txtLog.setText(weather_descriptions);

          } catch (JSONException e) {

              txtLog.setText("Error: " + e.getMessage());
          }
          btnFetch.setText("Fetch");

      }

    public void btnFetch_Click(View view) {
        btnFetch.setText("...");
        String city = txtCity.getText().toString();
        new Weather().execute("http://api.weatherstack.com/current?access_key="+ Setting.API_KEY_weatherstack+"&query=" + city);

    }
}