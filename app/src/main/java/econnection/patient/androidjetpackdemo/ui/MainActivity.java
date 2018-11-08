package econnection.patient.androidjetpackdemo.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import econnection.patient.androidjetpackdemo.Adapter.WordListAdapter;
import econnection.patient.androidjetpackdemo.R;
import econnection.patient.androidjetpackdemo.ViewModel.WordViewModel;
import econnection.patient.androidjetpackdemo.data.Word;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private WordViewModel mWordViewModel;
    private WordListAdapter mWordListAdapter;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
        setData();
        setRecyclerview();

    }

    private void init(){
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
    }

    private void setData(){
        mWordViewModel.getAllWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                mWordListAdapter.setWords(words);
            }
        });
    }

    private void setRecyclerview(){
        mWordListAdapter = new WordListAdapter(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mWordListAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onClick() {
        Intent intent = new Intent(this,NewWordActivity.class);
        startActivityForResult(intent,NEW_WORD_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(data.getStringExtra("word_data"));
            mWordViewModel.insert(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "not save",
                    Toast.LENGTH_LONG).show();
        }
    }
}
