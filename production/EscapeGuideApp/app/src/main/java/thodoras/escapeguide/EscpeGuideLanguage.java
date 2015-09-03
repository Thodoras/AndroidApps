package thodoras.escapeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thodoras.escapeguide.R;


public class EscpeGuideLanguage extends Activity {

    private Button mEnglishButton;
    private Button mGreekButton;

    private String greek = "Gr";
    private String english = "En";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escpe_guide_language);

        mEnglishButton = (Button) findViewById(R.id.english_button);
        mEnglishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscpeGuideLanguage.this,EscapeGuideMenu.class);
                i.putExtra(EscapeGuideMenu.LANGUAGE, english);
                startActivity(i);
            }
        });

        mGreekButton = (Button) findViewById(R.id.greek_button);
        mGreekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EscpeGuideLanguage.this,EscapeGuideMenu.class);
                i.putExtra(EscapeGuideMenu.LANGUAGE, greek);
                startActivity(i);
            }
        });
    }





}
