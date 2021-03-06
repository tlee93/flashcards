package app.flashcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

class ApplicationResourceManager {
    private static ApplicationResourceManager instance = new ApplicationResourceManager();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static AssetManager am;
    private static ArrayList<String> languageList;
    private static TextToSpeech tts;
    private static String currentLanguage;


    private ApplicationResourceManager(){
        String[] languageListArray = {"Chinese", "Korean"}; //this contains the list of languages supported. add to this list
        languageList = new ArrayList<>(Arrays.asList(languageListArray));
        Context context = new GlobalApplicationContext().getContext();
        sharedPreferences = context.getSharedPreferences("appSettings", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        am = context.getAssets();
        currentLanguage = sharedPreferences.getString("currentLanguage", null);
        editor.apply();
    }

    public static ArrayList<String> getLanguageList(){
        return languageList;
    }

    public static void setLanguage(String language){
        currentLanguage = language;
        editor.putString("currentLanguage", language);
        editor.commit();
    }

    public static String getLanguage(){
        return currentLanguage;
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
        String filename = getLanguage() + "statuslist.dat"; //TODO consider using db
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(am.open(filename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    public static int getCurrentPosition(){
        return sharedPreferences.getInt("currentPosition" + currentLanguage, 0);
    }

    public static void setCurrentPosition(int position){
        editor.putInt("currentPosition" + currentLanguage, position);
        editor.apply();
    }

    public static boolean isCurrentlyDetailedViewMode(){
        return sharedPreferences.getBoolean("isCurrentlyDetailedViewMode", false);
    }

    public static void setIsCurrentlyDetailedViewMode(boolean mode){
        editor.putBoolean("isCurrentlyDetailedViewMode", mode);
        editor.apply();
    }

    //chooses the correct locale to use
    public static Locale getLocale(){
        Locale locale = null;
        switch (currentLanguage){
            case "Chinese":
                locale = Locale.CHINESE;
                break;
            case "Korean":
                locale = Locale.KOREAN;
                break;
            default:
                locale = Locale.US;
        }
        return locale;
    }

    //formats the way definitions get displayed depending on the current language
    public static String formatDefinitionString(List<String> definitionList){
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

    public static void initTTS(final Context context){
        if(tts != null) tts.shutdown();
        else tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    //try to set locale
                    int result = tts.setLanguage(getLocale());
                    if(result != TextToSpeech.LANG_AVAILABLE)
                        Toast.makeText(context, "Text-to-speech init fail. This language is not supported.", Toast.LENGTH_SHORT).show();
                }
                else //TODO better tts error handling
                    Toast.makeText(context, "Error. Text-to-speech may be disabled.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static TextToSpeech getTTS(){
        return tts;
    }

    public static void closeTTS(){
        if(tts != null) tts.shutdown();
    }
}
