package science_hack.itk.itk_drum;

import android.media.MediaPlayer;

import java.util.ArrayList;

import static android.os.SystemClock.sleep;
import static java.lang.Math.abs;

public class RunAlg implements Runnable {
    Algorithms alg;
    MediaPlayer mp;
    ArrayList<Float> list;
    float current;

    public RunAlg(Algorithms a, MediaPlayer m, ArrayList<Float> l, float c){
        alg = a;
        mp = m;
        list=l;
        current=c;
    }

    public void run(){
        //calculate maximum
        float m = 0;
        for(int i=0;i<list.size();++i) {
            if(list.get(i)>m) m=list.get(i);
        }
        if(abs(m-current) >= alg.DRUM_HIT_DISTANCE){
            if(mp.isPlaying()) mp.seekTo(0);
            mp.start();
        }
    }
}
