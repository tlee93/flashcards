package app.flashcards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder>{
    private ArrayList<Character> characterList;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView characterNumberTextView;
        TextView characterTextView;
        CheckBox seenCheckBox;
        ViewHolder(View v) {
            super(v);
            characterNumberTextView = (TextView) v.findViewById(R.id.characterNumberTextView);
            characterTextView = (TextView) v.findViewById(R.id.characterTextView);
            seenCheckBox = (CheckBox) v.findViewById(R.id.seenCheckBox);
        }
    }

    CharacterListAdapter(Context c, ArrayList<Character> chlist) {
        characterList = chlist;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.itemView.setOnClickListener(new ItemOnClickListener(context, position));
        holder.characterNumberTextView.setText(String.valueOf(character.getNumber()));
        String s = character.getCharacter().charAt(0) + "";
        holder.characterTextView.setText(s);
        holder.seenCheckBox.setChecked((character.getStatus()));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}
