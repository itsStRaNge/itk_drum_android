package science_hack.itk.itk_drum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    SoundPlayer sp;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button one = (Button) this.findViewById(R.id.button_main);
        sp = new SoundPlayer(this);
        mp = MediaPlayer.create(this, R.raw.kick);
    }

    public void buttonClick(View view) {
        Log.d("MainActivitz","Button Click");
        //sp.playDrum();
        if(mp.isPlaying()) mp.seekTo(0);
        mp.start();
    }
}