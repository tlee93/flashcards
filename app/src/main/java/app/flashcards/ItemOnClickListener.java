package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

//this handler is used to switch from list view to detailed view
public class ItemOnClickListener implements View.OnClickListener{
    private int position;
    private Context context;

    public ItemOnClickListener(Context c, int p){
        position = p;
        context = c;
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
