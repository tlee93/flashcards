package app.flashcards;

import android.app.Application;
import android.content.Context;

//this class allows the application context to be accessed from anywhere
public class GlobalApplicationContext extends Application{
    private static GlobalApplicationContext context;

    @Override
    public void onCreate(){
        context = this;
        super.onCreate();
    }

    public Context getContext(){
        return context.getApplicationContext();
    }
}
