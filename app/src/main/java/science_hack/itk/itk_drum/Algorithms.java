package science_hack.itk.itk_drum;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.util.ArrayList;
import static android.os.SystemClock.sleep;
import static java.lang.Math.abs;

//Class algorithm to determine when there was a hit and play the corresponding sound
public class Algorithms {
    private MediaPlayer mp;
    private Context context;

    public static float DRUM_HIT_DISTANCE = 4;
    public static long FREEZING_CYCLES = 1;
    public static float HIT_NORM = 20;

    public Algorithms(Context c){
        context = c;
        mp = MediaPlayer.create(context, R.raw.kick);
    }

    //Old algorithm: receives a list of the accelerations during some period of time in the past and the current acceleration
    //calculates the minimum of the list, a low value represents a movement downwards
    //if the current acceleration is larger enough than the minimum, it means a stop after moving down, therefore a hit
    public boolean DrumHit(ArrayList<Float> l, float current){
        //calculate minimum
        float m = 100;
        for(int i=0;i<l.size();++i) {
            if(l.get(i)<m) m = l.get(i);
        }
        if(current - m >= DRUM_HIT_DISTANCE){
            if(mp.isPlaying()) mp.seekTo(0);
            mp.start();
            return true;
        }
        return false;
    }

    //Only considering the current norm
    //When the value is large enough, it means there was a big change in the velocity, i.e. from moving up to down, therefore a drum hit
    public boolean DrumHit2(float current){
        if(current >= HIT_NORM){
            if(mp.isPlaying()) mp.seekTo(0);
            Log.d("Algorithm","Durm Hit!");
            mp.start();
            return true;
        }
        return false;
    }

}
