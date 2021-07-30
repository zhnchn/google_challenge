package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean isPlaying = false;
  private String currentVideo = "";
  private String prevVideo = "";

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    List<Video> sortedVideoLibrary = videoLibrary.getVideos();
    sortedVideoLibrary.sort(Comparator.comparing(Video::getTitle));

    for (Video vid : sortedVideoLibrary) {
      System.out.println(vid.getTitle() + " (" + vid.getVideoId()+ ") " + vid.getTags());
    }
  }

  public void playVideo(String videoId) {

    currentVideo = videoId;

    if (videoLibrary.getVideo(videoId) == null) {
      System.out.println("Cannot play video: Video does not exist");
    }
    else {
      if (isPlaying) {
        System.out.println("Stopping video: " + videoLibrary.getVideo(prevVideo).getTitle());
        System.out.println("Playing video: " + videoLibrary.getVideo(currentVideo).getTitle());
      }
      if (!isPlaying) {
        System.out.println("Playing video: " + videoLibrary.getVideo(currentVideo).getTitle());
        isPlaying = true;
      }
      prevVideo = currentVideo;
    }
  }


  public void stopVideo() {
    System.out.println("stopVideo needs implementation");
  }

  public void playRandomVideo() {
    System.out.println("playRandomVideo needs implementation");
  }

  public void pauseVideo() {
    System.out.println("pauseVideo needs implementation");
  }

  public void continueVideo() {
    System.out.println("continueVideo needs implementation");
  }

  public void showPlaying() {
    System.out.println("showPlaying needs implementation");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
  }

  public void showAllPlaylists() {
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}