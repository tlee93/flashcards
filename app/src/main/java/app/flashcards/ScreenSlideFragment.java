package app.flashcards;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ScreenSlideFragment extends Fragment {
    private int position;
    private Character character;

    // Required empty public constructor
    public ScreenSlideFragment() {}

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
        character = CharacterList.getCharacterList().get(position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.detailed_view_layout, container, false);

        TextView detailedNumberTextView = (TextView) view.findViewById(R.id.detailedNumberTextView);
        TextView detailedCharacterTextView = (TextView) view.findViewById(R.id.detailedCharacterTextView);
        TextView detailedDefinitionsTextView = (TextView) view.findViewById(R.id.detailedDefinitionsTextView);

        detailedNumberTextView.setText(String.valueOf(character.getNumber()));
        detailedCharacterTextView.setText(character.getCharacter());
        detailedDefinitionsTextView.setText(formatDefinitionString(character.getDefinitions()));
        return view;
    }

    private String formatDefinitionString(List<String> definitionList){
        String def = "";
        for(String s: definitionList)
            def += (s + '\n');
        return def;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
