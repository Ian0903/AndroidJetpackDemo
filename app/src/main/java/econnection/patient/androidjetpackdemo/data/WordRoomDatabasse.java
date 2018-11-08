package econnection.patient.androidjetpackdemo.data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Word.class},version = 1)
public abstract class WordRoomDatabasse extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabasse INSTANCE;

    static WordRoomDatabasse getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabasse.class){
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabasse.class,"word_databse")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
            }

        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabasse db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
