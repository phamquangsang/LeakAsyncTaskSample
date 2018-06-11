package sample.pham.sang.leakasynctask;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class FeedsFragment extends Fragment {


    public FeedsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new MyAsyncTask(this).execute();

        return inflater.inflate(R.layout.fragment_feeds, container, false);
    }


    private static class Feed {
        String id;
        String content;
    }

    public static class MyAsyncTask extends AsyncTask<Void, Void, List<Feed>> {

        private WeakReference<FeedsFragment> ref;

        MyAsyncTask(FeedsFragment fragment) {
            ref = new WeakReference<>(fragment);
        }

        @Override
        protected List<Feed> doInBackground(Void... voids) {
            try {
                //doing a long run networking task
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }

        @Override
        protected void onPostExecute(List<Feed> feeds) {
            if(ref.get() != null ){
                Toast.makeText(ref.get().getContext(), "Done", Toast.LENGTH_SHORT).show();
                ref.get().bindView(feeds);
            }
        }
    }

    private void bindView(List<Feed> feeds) {
    }
}
