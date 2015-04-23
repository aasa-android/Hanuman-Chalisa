package chalisa.hanuman.hanumanchalisa;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;


public class MainActivity extends ActionBarActivity {

    ImageButton play;
    ImageButton reload;
    Button chalisa;
    ImageView hanumanImage;
    TextView  chalisaText;
    static int click = 0;
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
        startAppAd.loadAd();

        StartAppAd.showSlider(this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        play = (ImageButton) findViewById(R.id.start);
        reload = (ImageButton) findViewById(R.id.stop);
        chalisa = (Button) findViewById(R.id.chalisa);
        hanumanImage = (ImageView) findViewById(R.id.hanuman_bgImage);
        chalisaText = (TextView) findViewById(R.id.chalisaText);

        chalisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i%2 == 0) {
                    hanumanImage.setAlpha((float) 0.3);
                    chalisaText.setText(R.string.chalisa);
                    i++;
                }else{
                    hanumanImage.setAlpha((float) 1);
                    chalisaText.setText("");
                    i++;
                }
            }
        });

         mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.hanuman_chalisa);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(click%2 == 0) {
                    mediaPlayer.start();
                    play.setImageResource(R.drawable.ic_action_pause);
                    click++;

                }else{
                    mediaPlayer.pause();
                    play.setImageResource(R.drawable.ic_action_play);
                    click++;
                }
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.hanuman_chalisa);
                    play.setImageResource(R.drawable.ic_action_play);
                    click = 0;
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        startAppAd.onResume();
    }
    @Override
    public void onBackPressed() {
        startAppAd.onBackPressed();
        super.onBackPressed();
    }
}
