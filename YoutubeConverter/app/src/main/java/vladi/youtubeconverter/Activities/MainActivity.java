package vladi.youtubeconverter.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vladi.youtubeconverter.Fragments.YoutubeLinkFragment;
import vladi.youtubeconverter.R;

public class MainActivity extends AppCompatActivity implements YoutubeLinkFragment.YoutubeLinkDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetVideo(View view) {
        Intent getVideoIntent = new Intent(this, MyVideo.class);
        startActivity(getVideoIntent);

    }

    public void GetMusic(View view) {
        Intent getMusicIntent = new Intent(this, MyMusic.class);
        startActivity(getMusicIntent);
    }
    public void Download(View view) {
        showYtLinkDialog();
    }

    private void showYtLinkDialog() {
        FragmentManager fm = getSupportFragmentManager();
        YoutubeLinkFragment editNameDialogFragment = YoutubeLinkFragment.newInstance("Paste Youtube Link");
        editNameDialogFragment.show(fm, "fragment_youtube_link");
    }

    @Override
    public void onFinishYtLinkDialog(String inputText) {
        Intent downloadIntent = new Intent(this, Download.class);
        downloadIntent.putExtra(Intent.EXTRA_TEXT, inputText);
        startActivity(downloadIntent);
    }
}
