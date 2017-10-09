package de.vorsicht_ar.artestfromscratch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setButtonOnClickListeners();
    }

    /**
     * Creates all onClickListeners for buttons.
     * If you add a new button with a new onclick-action, add it hiere.
     */
    private void setButtonOnClickListeners(){
        // start camera with video
        Button btn_ar_start = (Button) findViewById(R.id.btn_ar_start);
        btn_ar_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARActivity.TARGET_HTML = "index.html";
                startActivity(new Intent(MainActivity.this, ARActivity.class));
            }
        });


        // start camera without video
//        Button btn_no_video = (Button) findViewById(R.id.btn_no_video);
//        btn_no_video.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ARActivity.TARGET_HTML = "index_no_video.html";
//                startActivity(new Intent(MainActivity.this, ARActivity.class));
//            }
//        });
    }
}
