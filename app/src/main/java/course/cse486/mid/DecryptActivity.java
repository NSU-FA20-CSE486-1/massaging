package course.cse486.mid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DecryptActivity extends AppCompatActivity {

    TextView mEncryption;
    TextView mOutput;
    EditText mCypher;
    Button mDecrypt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);

        mEncryption = findViewById(R.id.tv_encryption);
        mOutput = findViewById(R.id.tv_output);
        mCypher = findViewById(R.id.et_message_body);
        mDecrypt = findViewById(R.id.btn_decrypt);

        mDecrypt.setOnClickListener(v -> {
            // tokenize the message into secret and cypher text
            // call the decrypt method
            // setText of @mOutput
            // make visible mOutput
            String cypherText = mCypher.getText().toString();

            if(!cypherText.isEmpty()) {
                Cypher cypher = new Cypher();
                String message = cypher.decode(cypherText);

                mOutput.setText(message);
            } else {
                mCypher.setError("empty!");
            }
        });
    }
}