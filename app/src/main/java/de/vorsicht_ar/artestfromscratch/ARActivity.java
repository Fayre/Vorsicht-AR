package de.vorsicht_ar.artestfromscratch;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class ARActivity extends AppCompatActivity {

    /**
     * Define the target html in case that different vr views will be used.
     * Always relative from Androids assets-folder.
     */
    public static String TARGET_HTML;

    /**
     * my free trial license key.
     */
    private final String licenseKey = "vU8s2C85uD6q8YmB+njl5TpjIxQwymwDu6bXcrELvnWTGwNRlNi9fd0O09XSBjQXIla00HM4BeU0QLohIKKWD6RuI3VMpvIRvwsEG0ErWlpIK4Ulcco5QOdMejjj93f1LkrEkwsiMg0IgydWc0tdOvJ0sOk0RYh6HXfX23z4i9NTYWx0ZWRfXwV6VYlU4hW9ZtWxF4vL9RM+Q35rCXsiX3qPyC6cEuNGOOkqEyid1XE6iEC7NTFwFFW5HjqlYMIZZQhVvTOhK6YtLSmYslRrinanYdHVHPuzndA0BHQ5LR12v8142ZKTwsZIUT6vNxamJbnb6BmG5GCVsgWqBXx6OczWPp145RASeWr/0hzQeq4WV9S3zFw0Kr9lQP/tZyMkXFNdBTZcIU3NFCAvzcCUio57B/oA1ukzCattULMVGKFZnJMAN00nW73er6z3yDhhCcSvQWsrTRPfhKfBliSoic2c1P9w98p2RZ7xR6q5edTzT6RYMDd72npPRQO3pZmvjctH1ndgotTszv7nFoalgrMHWSviqUCyKvQeGh9J1oMLflx6Nddd8qLNFWulg3moI0Jnrxqzus+ulwWU86ykhe1cT36YTl0yp27WHEu4BrdyJZHcPfS3pN7aLSJkAQFb5Nkic2E3IjyxR5xMAIPrRoh7s5M4a9pxPKxCPMpWOMF+4TkCNEaYoe+pnalNndQ2";

    /**
     * the view handling the ar.
     */
    private ArchitectView architectView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey(licenseKey);
        // Since Android 6.0+ you need to make sure your app has the camera runtime permission before calling
        cameraPermission();
        this.architectView.onCreate(config);

        FloatingActionButton btn_help = (FloatingActionButton) findViewById(R.id.btn_help);
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                  startActivity(new Intent(getApplicationContext(), Help.class));

//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                //Fragment fragment = HelpFragment.newInstance("skdf", "skdflj");
//                Fragment fragment = new HelpFragment();
//                fragmentTransaction.add(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        architectView.onPostCreate();
        try {
            architectView.load(TARGET_HTML);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("unable to load html file: " + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        architectView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        architectView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        architectView.onResume();
    }

    /**
     * You need to check and gain the camera permission to use the architectView.
     */
    private void cameraPermission() {
        // TODO: check why this doesnt work
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // you have the permission, everything is fine
        } else {
            // you do not have the permission, so ask for it
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    permissionCheck);
//                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }
}
