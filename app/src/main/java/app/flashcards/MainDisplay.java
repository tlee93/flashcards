package app.flashcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainDisplay extends AppCompatActivity {
    //load json data. display as list
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);
        CharacterList cl = new CharacterList(this);
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(cl.a);
    }
}
