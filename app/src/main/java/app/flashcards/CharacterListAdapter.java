package app.flashcards;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder>{
    protected ArrayList<Character> characterList;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView characterNumberTextView;
        protected TextView characterTextView;
        protected CheckBox seenCheckBox;
        public ViewHolder(View v) {
            super(v);
            characterNumberTextView = (TextView) v.findViewById(R.id.characterNumberTextView);
            characterTextView = (TextView) v.findViewById(R.id.characterTextView);
            seenCheckBox = (CheckBox) v.findViewById(R.id.seenCheckBox);
        }
    }

    public CharacterListAdapter(Context c, ArrayList<Character> chlist) {
        characterList = chlist;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_entry_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.itemView.setOnClickListener(new ItemOnClickListener(context, position));
        holder.characterNumberTextView.setText(String.valueOf(character.getNumber()));
        holder.characterTextView.setText(character.getCharacter());
        holder.seenCheckBox.setChecked((character.getStatus()));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }
}
