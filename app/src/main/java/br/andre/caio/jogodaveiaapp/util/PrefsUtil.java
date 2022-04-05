package br.andre.caio.jogodaveiaapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {
    public static  void salvarSimboloJog1(String simbolo, Context context){
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simbJog1", simbolo);
        editor.commit();
    }
    public static  void salvarSimboloJog2(String simbolo, Context context){
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("simbJog2", simbolo);
        editor.commit();
    }
    public static String getSimboloJog1(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("simbJog1", "X");
    }
    public static String getSimboloJog2(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return  preferences.getString("simbJog2", "O");
    }
}
