package econnection.patient.androidjetpackdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import econnection.patient.androidjetpackdemo.R;

public class NewWordActivity extends AppCompatActivity {

    @BindView(R.id.edit_word)
    EditText editWord;
    @BindView(R.id.button_save)
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_save)
    public void onClick() {
        String content = editWord.getText().toString();
        if(TextUtils.isEmpty(content)) setResult(RESULT_CANCELED);
        else{
            Intent replyIntent = new Intent();
            replyIntent.putExtra("word_data",content);
            setResult(RESULT_OK,replyIntent);
        }
        finish();
    }
}
