package app.flashcards;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoadingScreenActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle b = getIntent().getExtras();
            int position = 0;
            if (b != null)
                position = b.getInt("position");

            setContentView(R.layout.activity_detailed_view);
        }
    }
}
