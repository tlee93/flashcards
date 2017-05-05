package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ItemOnClickListener implements View.OnClickListener{
    int position;
    Context context;
    public ItemOnClickListener(Context c, int p, ArrayList<Character> chlist){
        context = c;
        position = p;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailedViewActivity.class);
        Bundle b = new Bundle();
        b.putInt("position", position); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        context.startActivity(intent);
    }
}
