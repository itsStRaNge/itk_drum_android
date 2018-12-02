package science_hack.itk.itk_drum;

import android.content.Context;
import android.media.MediaPlayer;
import java.util.ArrayList;
import static android.os.SystemClock.sleep;
import static java.lang.Math.abs;

public class Algorithms {
    private MediaPlayer mp;
    private Context context;
    public static float DRUM_HIT_DISTANCE = 4;
    public static long FREEZE_CYCLES = 10;
    public static float WAVE_NORM = 16;
    public boolean LOCK = false;

    public Algorithms(Context c){
        context = c;
        mp = MediaPlayer.create(context, R.raw.kick);
    }

    public boolean DrumHit(ArrayList<Float> l, float current){
        //calculate minimum
        float m = 100;
        for(int i=0;i<l.size();++i) {
            if(l.get(i)<m) m=l.get(i);
        }
        if(current - m >= DRUM_HIT_DISTANCE){
            if(mp.isPlaying()) mp.seekTo(0);
            mp.start();
            return true;
        }
        return false;
    }
    //only considering the current norm, when it is large enough, it means there was a big change in the velocity, so from moving up to down
    public boolean DrumHit2(float current){
        if(current >= WAVE_NORM){
            if(mp.isPlaying()) mp.seekTo(0);
            mp.start();
            return true;
        }
        return false;
    }

}
