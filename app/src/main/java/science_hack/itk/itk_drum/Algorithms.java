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
    public boolean LOCK = false;

    public Algorithms(Context c){
        context = c;
        mp = MediaPlayer.create(context, R.raw.kick);
    }

    public boolean DrumHit(ArrayList<Float> l, float current){
        //calculate maximum
        float m = 0;
        for(int i=0;i<l.size();++i) {
            if(l.get(i)>m) m=l.get(i);
        }
        if(abs(m-current) >= DRUM_HIT_DISTANCE){
            if(mp.isPlaying()) mp.seekTo(0);
            mp.start();
            return true;
        }
        return false;
    }

}
