package app.flashcards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CharacterListAdapter extends ArrayAdapter<Character>{
    public CharacterListAdapter(Context context, ArrayList<Character> chlist) {
        super(context, 0, chlist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Character character = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_entry_layout, parent, false);
        }
        TextView characterNumberTextView = (TextView) convertView.findViewById(R.id.characterNumberTextView);
        TextView characterTextView = (TextView) convertView.findViewById(R.id.characterTextView);
        CheckBox seenCheckBox = (CheckBox) convertView.findViewById(R.id.seenCheckBox);

        characterNumberTextView.setText(String.valueOf(character.getNumber()));
        characterTextView.setText(character.getCharacter());
        seenCheckBox.setChecked((character.getStatus() == 1 ? true : false));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open new activity
            }
        });
        return convertView;
    }
}
