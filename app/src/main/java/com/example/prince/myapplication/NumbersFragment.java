package com.example.prince.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersFragment extends Fragment {

    private MediaPlayer mPlay;

    private AudioManager audioManager;

    final MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            release();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_GAIN)
                mPlay.start();
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)
                release();
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mPlay.pause();
                mPlay.seekTo(0);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.word_list, container, false);
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one","lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten","na'aacha", R.drawable.number_ten, R.raw.number_ten));

        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        /*for(int i=0;i<words.size();i++){
            TextView wordView = new TextView(this);
            wordView.setText(words.get(i));
            rootView.addView(wordView);
        }*/

        WordAdapter itemAdapter = new WordAdapter(getActivity(),words, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                release();

                int result = audioManager.requestAudioFocus(mAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mPlay = MediaPlayer.create(getActivity(), words.get(position).getAudioID());
                    mPlay.start();
                    mPlay.setOnCompletionListener(completionListener);
                }
            }
        });
        return rootView;
    }

    private void release(){
        if(mPlay != null){
            mPlay.release();
            mPlay = null;
            audioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        release();
    }
}
