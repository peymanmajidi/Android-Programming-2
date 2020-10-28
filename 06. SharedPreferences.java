public class Setting {
    public static  String TITLE = "MyAppSetting";
    public static  String FULLNAME = "Fullname";
    public static  String NO_NAME = "no name yet";


    public static String ReadPreferences(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(TITLE, Context.MODE_PRIVATE);
        return preferences.getString(FULLNAME, NO_NAME );

    }

    public static void UpdatePreferences(Context context, String name)
    {
        SharedPreferences.Editor editor = context.getSharedPreferences(TITLE, Context.MODE_PRIVATE).edit();
        editor.putString(FULLNAME, name);
        editor.apply();

    }



}
