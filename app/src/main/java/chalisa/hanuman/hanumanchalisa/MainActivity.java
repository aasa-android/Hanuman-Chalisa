package chalisa.hanuman.hanumanchalisa;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ImageButton play;
    ImageButton reload;
    Button chalisa;
    ImageView hanumanImage;
    TextView chalisaText;
    static int click = -1;
    static int i = 0;
    MediaPlayer mediaPlayer;
    private StartAppAd startAppAd = new StartAppAd(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "102735663", "203318730", true);
        StartAppAd.showSplash(this, savedInstanceState);
        setContentView(R.layout.activity_main);

        startAppAd.showAd(); // show the ad

        play = (ImageButton) findViewById(R.id.start);
        reload = (ImageButton) findViewById(R.id.reload);
        chalisa = (Button) findViewById(R.id.chalisa);
        hanumanImage = (ImageView) findViewById(R.id.hanuman_bgImage);
        chalisaText = (TextView) findViewById(R.id.chalisaText);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hanuman_chalisa);

        chalisa.setOnClickListener(this);
        play.setOnClickListener(this);
        reload.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (click % 2 == 0) {
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            click = -1;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chalisa:
                if (i % 2 == 0) {
                    hanumanImage.setAlpha((float) 0.3);
                    chalisaText.setText(R.string.chalisa);
                    i++;
                } else {
                    hanumanImage.setAlpha((float) 1);
                    chalisaText.setText("");
                    i++;
                }
                break;
            case R.id.start:
                click++;
                if (click % 2 == 0) {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_action_pause);
                } else {
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_action_play);
                }
                break;
            case R.id.reload:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hanuman_chalisa);
                    play.setImageResource(R.drawable.ic_action_play);
                    click = 0;
                }
                break;
            default:
                break;
        }
    }
}
