package com.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean isPlaying = false;
  private boolean isPaused = false;
  private String currentVideo = "";
  private String prevVideo = "";

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  /**
   * Shows all available videos in library
   */
  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    List<Video> sortedVideoLibrary = videoLibrary.getVideos();
    sortedVideoLibrary.sort(Comparator.comparing(Video::getTitle));

    for (Video vid : sortedVideoLibrary) {
      System.out.println(vid.getTitle() + " (" + vid.getVideoId()+ ") " + vid.getTags());
    }
  }

  /**
   * Play the specified video. If a video is currently playing, display a note that this video will be stopped, even
   * if the same video is already playing. If the video doesn’t exist, display a warning message
   * and don’t stop the currently playing video).
   * @param videoId
   */
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

  /**
   * Stop the current playing video. If no video is currently playing, display a warning message “Cannot stop video:
   * No video is currently playing” and do nothing.
   */
  public void stopVideo() {
    if(isPlaying) {
      System.out.println("Stopping video: " + videoLibrary.getVideo(currentVideo).getTitle());
      isPlaying = false;
      isPaused = false;
    }
    else if(!isPlaying) {
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  /**
   * Play a random video. If a video is currently playing, display a note that this video will be stopped,
   * even if the same video is already playing.
   */
  public void playRandomVideo() {
    Random rand = new Random();
    List<Video> availableVideos = videoLibrary.getVideos();
    Video randomVideo = availableVideos.get(rand.nextInt(availableVideos.size()));

    if(randomVideo != null) {
      playVideo(randomVideo.getVideoId());
    }
  }

  /**
   * Pause the current playing video. If a video is already paused, display a warning message and do nothing. Equally,
   * If no video is currently playing, display a warning message and do nothing.
   */
  public void pauseVideo() {
    if (isPlaying) {
      if (isPaused) {
        System.out.println("Video already paused: " + getCurrentVideoTitle());
      } else if (!isPaused) {
        System.out.println("Pausing video: " + getCurrentVideoTitle());
        isPaused = true;
      }
    }
    else if(!isPlaying) {
      System.out.println("Cannot pause video: No video is currently playing");
    }
  }

  /**
   * My own method to return video title from ID faster
   * @return
   */
  private String getCurrentVideoTitle() {
    return videoLibrary.getVideo(currentVideo).getTitle();
  }

  /**
   * Continues a currently paused video. If the currently playing video is not paused,
   * display a warning message and do nothing. If no video is playing at all, also display a warning message and do nothing.
   */
  public void continueVideo() {
    if(isPaused) {
      System.out.println("Playing video: " + currentVideo);
      isPaused = false;
    }
    else if(!isPaused) {
      System.out.println("Cannot continue video: Video is not paused");
    }
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