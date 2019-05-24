package ijbh.com.allinoneassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EmailActivity extends AppCompatActivity {


    EditText emailAddressEt;

    //TODO:rearrange the file.
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);   //show back button

        emailAddressEt = findViewById(R.id.email_to_et);
        Button sendBtn = findViewById(R.id.send_btn);
        final EditText ccEt = findViewById(R.id.cc_et);
        final EditText bccEt = findViewById(R.id.bcc_et);
        final TextView ccTv = findViewById(R.id.cc_tv);
        final TextView bccTv = findViewById(R.id.bcc_tv);

        emailAddressEt.setText(getIntent().getStringExtra("emailAddress"));
        emailAddressEt.clearFocus();

        //listeners
        //intent to mail app
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText emailSubjectEt = findViewById(R.id.email_title_et);
                EditText emailMessageEt = findViewById(R.id.email_msg_et);
                EditText emailCcEt = findViewById(R.id.cc_et);
                EditText emailBccEt = findViewById(R.id.bcc_et);

                if(!isEmpty(emailAddressEt)){
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+emailAddressEt.getText()));
                    //emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubjectEt.getText().toString());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessageEt.getText());

                    emailIntent.putExtra(Intent.EXTRA_CC, new String[]{emailCcEt.getText().toString()});
                    emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{emailBccEt.getText().toString()});
                    if (emailIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.email_chooser)));
                    }
                }
            }
        });

        //cc and bcc
        ccTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ccEt.getVisibility() == View.GONE){
                    ccEt.setVisibility(View.VISIBLE);
                    ccEt.requestFocus();
                    ccTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_remove_black_24dp, 0, 0, 0);
                }
                else{
                    ccEt.setVisibility(View.GONE);
                    ccEt.setText("");
                    ccTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);
                }
            }
        });

        bccTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bccEt.getVisibility() == View.GONE){
                    bccEt.setVisibility(View.VISIBLE);
                    ccEt.requestFocus();
                    bccTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_remove_black_24dp, 0, 0, 0);

                }
                else{
                    bccEt.setVisibility(View.GONE);
                    bccEt.setText("");
                    bccTv.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_add_black_24dp, 0, 0, 0);
                }
            }
        });
    }

    //extra functions
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
