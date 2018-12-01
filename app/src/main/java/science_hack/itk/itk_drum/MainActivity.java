package science_hack.itk.itk_drum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button one = (Button) this.findViewById(R.id.button_main);
    final MediaPlayer mp = MediaPlayer.create(this, R.raw.kick);

    public void buttonClick(View view) {
        mp.start();
    }
}