package com.xhemil.flickrbrowser;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class GetFlickrJsonData extends AsyncTask<String, Void, List<Photo>> implements GetRawData.OnDownloadComplete {

    private static final String TAG = "GetFlickrJsonData";
    private static final String BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
    private static final String PARAM_TAGS = "tags";
    private static final String PARAM_TAG_MODE = "tagmode";
    private static final String PARAM_LANG = "lang";
    private static final String PARAM_FORMAT = "format";
    private static final String PARAM_CALL_BACK = "nojsoncallback";

    private static final String VALUE_FORMAT = "json";
    private static final String VALUE_CALL_BACK = "1";


    private List<Photo> mPhotoList = null;
    private String mLanguage;
    private boolean mMatchAll;

    private final OnDataAvailable mCallBack;
    private boolean runningOnSameThread = false;

    interface OnDataAvailable{
        void onDataAvailable(List<Photo> data, DownloadStatus status);
    }

    public GetFlickrJsonData(OnDataAvailable callBack, String language, boolean matchAll) {
        mLanguage = language;
        mMatchAll = matchAll;
        mCallBack = callBack;
    }

    void executeOnSameThread(String searchCriteria){
        runningOnSameThread = true;
        String destinationUri = createUri(searchCriteria, mLanguage, mMatchAll);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationUri);
    }

    @Override
    protected void onPostExecute(List<Photo> photos) {
        if(mCallBack != null){
            mCallBack.onDataAvailable(mPhotoList, DownloadStatus.OK);
        }
    }

    @Override
    protected List<Photo> doInBackground(String... params) {
        String destinationUri = createUri(params[0], mLanguage, mMatchAll);

        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationUri);
        return null;
    }

    private String createUri(String searchCriteria, String language, boolean matchAll){
        return Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_TAGS, searchCriteria)
                .appendQueryParameter(PARAM_TAG_MODE, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(PARAM_LANG, language)
                .appendQueryParameter(PARAM_FORMAT, VALUE_FORMAT)
                .appendQueryParameter(PARAM_CALL_BACK, VALUE_CALL_BACK)
                .build().toString();
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        if (status == DownloadStatus.OK){
            mPhotoList = new ArrayList<>();
            try {
                JSONObject jsonData = new JSONObject(data);
                JSONArray itemsArray = jsonData.getJSONArray("items");

                for (int i = 0; i<itemsArray.length(); i++){
                    JSONObject jsonPhoto = itemsArray.getJSONObject(i);
                    String title = jsonPhoto.getString("title");
                    String author = jsonPhoto.getString("author");
                    String authorId = jsonPhoto.getString("author_id");
                    String tags = jsonPhoto.getString("tags");
                    String fullLink = jsonPhoto.getString("link");

                    JSONObject jsonMedia = jsonPhoto.getJSONObject("media");
                    String photoUrl = jsonMedia.getString("m");

                    String link = photoUrl.replaceFirst("_m.", "_b.");

                    Photo photoObject = new Photo(title, author, authorId, link, tags, photoUrl, link);
                    mPhotoList.add(photoObject);
                }
            }catch (JSONException e){
                Log.e(TAG, "onDownloadComplete: could not extract JSON data" + e.getMessage());
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }

        if (runningOnSameThread && mCallBack != null){
            mCallBack.onDataAvailable(mPhotoList, status);
        }
    }
}
