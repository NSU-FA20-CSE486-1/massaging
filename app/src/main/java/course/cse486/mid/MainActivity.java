package course.cse486.mid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button mDecryption;
    Button mEncrypt;

    EditText mPhone;
    EditText mSecret;
    EditText mMessage;

    Cypher cypher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDecryption = findViewById(R.id.btn_decrypt);
        mEncrypt = findViewById(R.id.btn_encrypt);

        mPhone = findViewById(R.id.et_phone);
        mSecret = findViewById(R.id.et_secret);
        mMessage = findViewById(R.id.et_message_body);

        cypher = new Cypher();

        mDecryption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DecryptActivity.class);
                startActivity(intent);
            }
        });

        mEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = mPhone.getText().toString();
                String message = mMessage.getText().toString();
                String secret = mSecret.getText().toString();

                String cypherText = cypher.encode(message, secret.hashCode() % 26);

                try {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    // This ensures only SMS apps respond
                    intent.setData(Uri.parse("smsto:"+phoneNumber));
                    intent.putExtra("sms_body", secret+" "+cypherText);
                    startActivity(intent);
                } catch (Exception e) {
//            Log.d("ERROR", e.getMessage());
                }

            }
        });
    }
}