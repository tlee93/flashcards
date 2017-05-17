package app.flashcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

class ApplicationResourceManager {
    //TODO helper functions to help app get correct resources and data depending on the current language: sharedpref reader, data files to read, how to format definitions, tts locality, correct sharedpref data
    private static ApplicationResourceManager instance = new ApplicationResourceManager();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static AssetManager am;

    private ApplicationResourceManager(){
        Context context = new GlobalApplicationContext().getContext();
        sharedPreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        am = context.getAssets();
    }

    public static ApplicationResourceManager getInstance(){
        return instance;
    }

    public static boolean isFirstTimeUser(){
        return sharedPreferences.contains("currentLanguage");
    }

    public static void setLanguage(String language){
        editor.putString("currentLanguage", language);
        editor.commit();
    }

    public static String getLanguage(){
        return sharedPreferences.getString("currentLanguage", null);
    }

    //only call getLanguageFiles after language has been set
    public static BufferedReader getLanguageResourceFile(){
        String filename = getLanguage() + "wordlist.json";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(am.open(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static BufferedReader getLanguageStatusFile(){
        String filename = getLanguage() + "statuslist.dat";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(am.open(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static int getCurrentPosition(){
        String currentLanguage = sharedPreferences.getString("currentLanguage", null);
        return sharedPreferences.getInt("currentPosition" + currentLanguage, 0);
    }

    public static void setCurrentPosition(int position){
        String currentLanguage = sharedPreferences.getString("currentLanguage", null);
        editor.putInt("currentPosition" + currentLanguage, position);
        editor.apply();
    }

    //chooses the correct locale to use
    public static Locale getLocale(){
        String currentLanguage = sharedPreferences.getString("currentLanguage", null);
        Locale locale = null;
        switch (currentLanguage){
            case "Chinese":
                locale = Locale.CHINESE;
                break;
            case "Korean":
                locale = Locale.KOREA;
                break;
            default:
                locale = Locale.US; //TODO
        }
        return locale;
    }

    //formats the way definitions get displayed depending on the current language
    public static String formatDefinitionString(List<String> definitionList){
        String currentLanguage = sharedPreferences.getString("currentLanguage", null);
        String s = "";
        switch (currentLanguage){
            case "Chinese":
                for(String def: definitionList)
                    s += (def + '\n');
                break;
            default:
                s=  definitionList.toString();
        }
        return s;
    }

    //formats the way the word(s) get displayed depending on the current language
    public static String formatWordDisplayString(String word){
        String currentLanguage = sharedPreferences.getString("currentLanguage", null);
        String s = "";
        switch (currentLanguage){
            case "Chinese":
                s = word.charAt(0) + "";
                break;
            case "Korean":
                s = word;
                break;
            default:
                s = word;
                break;
        }
        return s;
    }
}
