package com.bootcamp.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    private static final String[] ENROLLMENT_RECIPIENTS = { "utnbootcamp@test.com" };
    private Toolbar toolbar;
    private TextView tvTitle;
    private TextInputLayout fullNameInput;
    private TextInputLayout identificationInput;
    private TextInputLayout phoneInput;
    private TextInputLayout emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        toolbar = findViewById(R.id.toolbar_course);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setUpViews();
    }

    private void setUpViews() {
        findViewById(R.id.btn_enroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onBtnEnrollClick();
            }
        });

        tvTitle = findViewById(R.id.tv_title);
        registerForContextMenu(tvTitle);

        fullNameInput = findViewById(R.id.input_full_name);
        identificationInput = findViewById(R.id.input_identification);
        phoneInput = findViewById(R.id.input_phone);
        emailInput = findViewById(R.id.input_email);
    }

    /* default */ void onBtnEnrollClick() {
        hiddeKeyboard();

        String fullName = fullNameInput.getEditText().getText().toString();
        String identification = identificationInput.getEditText().getText().toString();
        String phone = phoneInput.getEditText().getText().toString();
        String email = emailInput.getEditText().getText().toString();

        StringBuilder builder = new StringBuilder("Hola, me quiero anotar al curso:");
        builder.append("\nNombre: " + fullName);
        builder.append("\nDNI: " + identification);
        builder.append("\nTeléfono: " + phone);
        builder.append("\nEmail: " + email);

        composeEmail(ENROLLMENT_RECIPIENTS, "GO! Desde Hello World hasta API Rest", builder.toString());
    }

    public void composeEmail(final String[] addresses, final String subject, final String body) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(CourseActivity.this, "Ups, no podes enviar un email.", Toast.LENGTH_SHORT).show();
        }
    }

    private void hiddeKeyboard() {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Options menu
     */
    //region OptionsMenu

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.course_activity_menu, menu);
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
        getMenuInflater().inflate(R.menu.change_color_context_menu, menu);
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
}
