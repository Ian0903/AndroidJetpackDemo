package econnection.patient.androidjetpackdemo.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import econnection.patient.androidjetpackdemo.data.Word;
import econnection.patient.androidjetpackdemo.data.WordRepository;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;
    private LiveData<List<Word>> mAllWords;


    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public void insert(Word word){
        mRepository.insert(word);
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }
}
