package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ItemOnClickListener implements View.OnClickListener{
    int position;
    Context context;
    ArrayList<Character> characterList;
    public ItemOnClickListener(Context c, int p, ArrayList<Character> chlist){
        context = c;
        position = p;
        characterList = chlist;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailedViewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
