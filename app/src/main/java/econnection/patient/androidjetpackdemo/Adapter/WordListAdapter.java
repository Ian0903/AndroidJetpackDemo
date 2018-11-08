package econnection.patient.androidjetpackdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import econnection.patient.androidjetpackdemo.R;
import econnection.patient.androidjetpackdemo.data.Word;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Word> mWords;//数据源

    public WordListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_words,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(mWords != null){
            holder.wordItemTv.setText(mWords.get(position).getWord());
        }else{
            holder.wordItemTv.setText("No Word");
        }
    }

    @Override
    public int getItemCount() {
        if (mWords != null) return mWords.size();
        else return 0;
    }

    public void setWords(List<Word> words){
        mWords = words;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView wordItemTv;

        public ViewHolder(View itemView) {
            super(itemView);
            wordItemTv = itemView.findViewById(R.id.textView);
        }
    }
}
