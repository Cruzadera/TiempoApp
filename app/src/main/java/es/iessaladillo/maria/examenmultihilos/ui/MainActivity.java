package es.iessaladillo.maria.examenmultihilos.ui;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.maria.examenmultihilos.R;

import es.iessaladillo.maria.examenmultihilos.ui.main.MainFragment;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flContent, MainFragment.newInstance(), MainFragment.class.getSimpleName())
                    .commit();
        }

    }


}
