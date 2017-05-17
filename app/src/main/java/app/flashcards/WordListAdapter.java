package app.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder>{
    private ArrayList<Word> wordList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView characterNumberTextView;
        TextView characterTextView;
        CheckBox seenCheckBox;
        ViewHolder(View v) {
            super(v);
            characterNumberTextView = (TextView) v.findViewById(R.id.entryNumberTextView);
            characterTextView = (TextView) v.findViewById(R.id.entryWordTextView);
            seenCheckBox = (CheckBox) v.findViewById(R.id.seenCheckBox);
        }
    }

    WordListAdapter(Context c, ArrayList<Word> chlist) {
        wordList = chlist;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Word word = wordList.get(position);
        holder.itemView.setOnClickListener(new ItemOnClickListener(position));
        holder.characterNumberTextView.setText(String.valueOf(word.getNumber()));
        String s = ApplicationResourceManager.formatWordDisplayString(word.getWord());
        holder.characterTextView.setText(s);
        holder.seenCheckBox.setChecked((word.getStatus()));
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    //this handler is used to switch from list view to detailed view
    private class ItemOnClickListener implements View.OnClickListener{
        private int position;

        ItemOnClickListener(int p){
            position = p;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailedViewActivity.class);
            ApplicationResourceManager.setCurrentPosition(position);
            context.startActivity(intent);
        }
    }
}
