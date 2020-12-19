




Add this line to `AndroidManifest.xml`
```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```

Make a new class and extends from AsyncTask
```java
public class Weather extends AsyncTask<String, String, String> 
```

Parse JSON Like this
```java
          try {
              JSONObject root = new JSONObject(result);
              JSONObject current = root.getJSONObject("current");
              String weather_descriptions = current.getJSONArray("weather_descriptions").getString(0);
              txtLog.setText(weather_descriptions);

          } catch (JSONException e) {

              txtLog.setText("Error: " + e.getMessage());
          }
```

Use the class
```java
        String city = txtCity.getText().toString();
        new Weather().execute("http://api.weatherstack.com/current?access_key="+ Setting.API_KEY_weatherstack+"&query=" + city);
```


https://weatherstack.com/