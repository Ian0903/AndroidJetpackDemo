package econnection.patient.androidjetpackdemo.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application){
        //获取数据库对象
        WordRoomDatabasse db = WordRoomDatabasse.getDatabase(application);
        //获取Dao对象
        mWordDao = db.wordDao();
        //获取数据句柄
        mAllWords = mWordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    //封装插入数据逻辑
    public void insert(Word word){
        new InsertAsynTask(mWordDao).execute(word);
    }

    private static class InsertAsynTask extends AsyncTask<Word,Void,Void>{
        private WordDao mAsynTaskDao;

        InsertAsynTask(WordDao dao){
            mAsynTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsynTaskDao.insert(words[0]);
            return null;
        }
    }
}
