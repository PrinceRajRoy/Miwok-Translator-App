package com.example.prince.myapplication;

public class Word {

    private String mDefaultText;
    private String mMiwokText;
    private int mImageResource = NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED = -1;
    private int mAudio;

    public Word(String defaultTranslation, String miwokTranslation, int audioID){
        mDefaultText = defaultTranslation;
        mMiwokText = miwokTranslation;
        mAudio = audioID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int audioID){
        mDefaultText = defaultTranslation;
        mMiwokText = miwokTranslation;
        mImageResource = imageResourceId;
        mAudio = audioID;
    }

    public String getDefault(){
        return mDefaultText;
    }

    public String getMiwok(){
        return mMiwokText;
    }

    public int getImageResourceId(){
        return mImageResource;
    }

    public boolean hasImage(){
        return mImageResource != NO_IMAGE_PROVIDED;
    }

    public int getAudioID(){
        return mAudio;
    }

    /*@Override
    public String toString() {
        return "Word{" +
                "mDefaultText='" + mDefaultText + '\'' +
                ", mMiwokText='" + mMiwokText + '\'' +
                ", mImageResource=" + mImageResource +
                ", mAudio=" + mAudio +
                '}';
    }*/
}
