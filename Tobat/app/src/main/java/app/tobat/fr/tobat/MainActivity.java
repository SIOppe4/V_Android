package app.tobat.fr.tobat;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setTitle("Coucou les gens");

        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                    TextView letexte = (TextView) findViewById(R.id.textvrai);
                    letexte.setText(Html.fromHtml("Bonjour <i> Alan </i> !! <b> tu vas bien ? </b>"));
            }
        });
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_edit:
                /* EDIT */
                startActivity(new Intent(MainActivity.this, ClientActivity.class));
                return true;
            case R.id.action_add:
                /* ADD */
                return true;
            case R.id.action_delete:
                /* DELETE */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

