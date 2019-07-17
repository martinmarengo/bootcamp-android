package com.bootcamp.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
    }

    /**
     * Options menu
     */
    //region OptionsMenu

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        //You can also add menu items using add() and retrieve items with findItem() to revise their
        // properties with MenuItem APIs.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_favorite:
            Toast.makeText(this, "Favorites pressed!", Toast.LENGTH_SHORT).show();
            return true;
        case R.id.action_settings:
            Toast.makeText(this, "Settings pressed!", Toast.LENGTH_SHORT).show();
            return true;
        case android.R.id.home:
            onBackPressed();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /*public void onFavoritesPressed(final MenuItem item) {
        Toast.makeText(this, "Favorites pressed!", Toast.LENGTH_SHORT).show();
    }

    public void onSettingsPressed(final MenuItem item) {
        Toast.makeText(this, "Settings pressed!", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //endregion

    /**
     * Context menu
     */
    //region ContextMenu

    @Override
    public void onCreateContextMenu(final ContextMenu menu, final View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {
        //AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
        case R.id.action_change_color:
            tvTitle.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }

    //endregion

    private void setUpViews() {
        Button btnEnroll = findViewById(R.id.btn_enroll);
        btnEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                //Toast.makeText(MainActivity.this, "Ya estas anotado!!", Toast.LENGTH_LONG).show();
                String[] recipients = { "utnbootcamp@test.com" };
                composeEmail(recipients, "Inscripción curso Go", "Hola, me quiero anotar al curso de Go.");
            }
        });

        tvTitle = findViewById(R.id.tv_title);
        registerForContextMenu(tvTitle);
    }

    /*public void onEnrollBtnClick(View button) {
        //Toast.makeText(MainActivity.this, "Ya estas anotado!!", Toast.LENGTH_LONG).show();
        String[] recipients = { "marengo.martin@gmail.com" };
        composeEmail(recipients, "Inscripción curso Go", "Hola, me quiero anotar al curso de Go.");
    }*/

    public void composeEmail(final String[] addresses, final String subject, final String body) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        //final Intent intent = new Intent(Intent.ACTION_SEND);
        //intent.setType("*/*");
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(MainActivity.this, "Ups, no podes enviar un email.", Toast.LENGTH_SHORT).show();
        }

        //toolbar.setVisibility(View.GONE); // vs getSupportActionBar().hide(); etc.
        //toolbar.setBackgroundColor(Color.BLUE);
    }
}
