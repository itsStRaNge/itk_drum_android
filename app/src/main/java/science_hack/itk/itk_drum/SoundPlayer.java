package science_hack.itk.itk_drum;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.SparseArray;

public class SoundPlayer {
    //private MediaPlayer mp;
    //private SparseArray<PlayThread> threadMap = null;
    private Context context;
    //private static final SparseArray<String> SOUND_MAP = new SparseArray<>();
    //public static final int MAX_VOLUME = 100, CURRENT_VOLUME = 90;

    public SoundPlayer(Context context) {
        this.context = context;
        //threadMap = new SparseArray<>();

    }

    public void playDrum() {
        PlayThread thread = new PlayThread();
        thread.start();
    }

    private class PlayThread extends Thread {

       public void run() {
           MediaPlayer mp = MediaPlayer.create(context, R.raw.kick);
           mp.start();
        }
    }
}
