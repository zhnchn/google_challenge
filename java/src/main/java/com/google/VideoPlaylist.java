package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** A class used to represent a Playlist */
class VideoPlaylist {

    public LinkedHashMap<String, Video> videoInPlaylist;
    private String nameOfVideo;

    public VideoPlaylist(String videoName) {
        this.videoInPlaylist = new LinkedHashMap<>();
        this.nameOfVideo = videoName;
    }

    public boolean checkVideoExist(String videoID) {
        for (Video video : videoInPlaylist.values()) {
            if(video.getVideoId().equals(videoID)) {
                return true;
            }
        }
        return false;
    }
}
