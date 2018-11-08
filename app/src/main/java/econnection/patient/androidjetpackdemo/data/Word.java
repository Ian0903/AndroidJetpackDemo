package econnection.patient.androidjetpackdemo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

@Entity(tableName = "word_table")
public class Word {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(String mWord){
        this.mWord = mWord;
    }

    public String getWord() {
        return this.mWord;
    }

    public void setmWord(@Nullable String mWord) {
        this.mWord = mWord;
    }
}
