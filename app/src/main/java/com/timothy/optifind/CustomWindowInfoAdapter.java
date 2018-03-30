package com.timothy.optifind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by user on 2/25/2018.
 */

public class CustomWindowInfoAdapter implements GoogleMap.InfoWindowAdapter {

    private View mWindow;
    private Context mContext;

    public CustomWindowInfoAdapter(Context context) {
        this.mContext = context;
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindow(Marker marker , View view){
        String title = marker.getTitle();
        TextView tvtitle =(TextView) view.findViewById(R.id.title);

        if(!title.equals("")){
            tvtitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvsnippet =(TextView) view.findViewById(R.id.snippet);

        if(!snippet.equals("")){
            tvsnippet.setText(snippet);
        }
    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindow(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindow(marker, mWindow);
        return mWindow;
    }
}
