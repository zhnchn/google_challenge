package com.google;

import java.util.*;
import java.util.stream.Collectors;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private boolean isPlaying = false;
  private boolean isPaused = false;
  private String currentVideo = "";
  private String prevVideo = "";
  private String showPlayingText = "";
  private Map<String, VideoPlaylist> userPlaylist;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.userPlaylist = new HashMap<>();
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
      List<String> videoTags = vid.getTags();
      System.out.println(vid.getTitle() + " (" + vid.getVideoId()+ ") " + videoTags.toString().replace(",",""));
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
      isPaused = false;
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
    if(!isPlaying) {
      System.out.println("Cannot continue video: No video is currently playing");
    }
    else {
      if (isPaused) {
        System.out.println("Continuing video: " + getCurrentVideoTitle());
        isPaused = false;
      } else if (!isPaused) {
        System.out.println("Cannot continue video: Video is not paused");
      }
    }
  }

  /**
   * Displays the title, video_id, video tags and paused status of the video that is currently playing.
   * If no video is currently playing, display a message.
   */
  public void showPlaying() {
    showPlayingText = "";
    String showPausedText = "";
    if(isPaused) {
      showPausedText = " - PAUSED";
    }
    else {
      showPausedText = "";
    }
    if(isPlaying) {
      showPlayingText += "Currently playing: " + getCurrentVideoTitle() + " (" + videoLibrary.getVideo(currentVideo).getVideoId()
      + ") " + videoLibrary.getVideo(currentVideo).getTags().toString().replace(",","") + showPausedText;
    }
    else {
      System.out.println("No video is currently playing");
    }

    System.out.println(showPlayingText);
  }

  public void createPlaylist(String playlistName) {

    if(userPlaylist.containsKey(playlistName.toLowerCase())) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
    else {
      VideoPlaylist videoPlaylist = new VideoPlaylist(playlistName);
      userPlaylist.put(playlistName.toLowerCase(), videoPlaylist);
      System.out.println("Successfully created new playlist: " + playlistName);
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if(userPlaylist.containsKey(playlistName.toLowerCase())) {
      if(videoLibrary.getVideo(videoId) == null){
        System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      }
      else if(userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.containsKey(videoId)) {
        System.out.println("Cannot add video to " + playlistName + ": Video already added");
      }
      else {
        userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.put(videoId, videoLibrary.getVideo(videoId));
        System.out.println("Added video to " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
      }
    }
    else {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    }
  }

  public void showAllPlaylists() {
    if(userPlaylist.isEmpty()) {
      System.out.println("No playlists exist yet");
    }
    else {
      System.out.println("Showing all playlists:");
      for (String playList : userPlaylist.keySet()) {
        System.out.println(" " + playList);
      }
      //System.out.println(userPlaylist.keySet().stream().sorted().toString());
    }
  }

  public void showPlaylist(String playlistName) {
    if (!userPlaylist.containsKey(playlistName.toLowerCase())) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
    } else {
      System.out.println("Showing playlist: " + playlistName);
      if (userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.isEmpty()) {
        System.out.println("No videos here yet");
        return;
      }
      System.out.println(userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.toString());

    }
  }


  public void removeFromPlaylist(String playlistName, String videoId) {
    if (!userPlaylist.containsKey(playlistName.toLowerCase())) {
      System.out.println("Cannot remove video from " + playlistName + ": Playlist does not exist");
      return;
    }
    if (!videoLibrary.containsVideo(videoId.toLowerCase())) {
      System.out.println("Cannot remove video from " + playlistName + ": Video does not exist");
      return;
    }
    if (!userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.containsKey(videoId.toLowerCase())) {
      System.out.println("Cannot remove video from " + playlistName + ": Video is not in playlist");
    }
    else {
    userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.remove(videoId);
    System.out.println("Removed video from " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
    }
  }

  /**
   *
   * @param playlistName
   */
  public void clearPlaylist(String playlistName) {
    if (!userPlaylist.containsKey(playlistName.toLowerCase())) {
      System.out.println("Cannot clear playlist " + playlistName + ": Playlist does not exist");
    } else {
    userPlaylist.get(playlistName.toLowerCase()).videoInPlaylist.clear();
    System.out.println("Successfully removed all videos from " + playlistName);
    }
  }

  public void deletePlaylist(String playlistName) {
    if (!userPlaylist.containsKey(playlistName.toLowerCase())) {
      System.out.println("Cannot delete playlist " + playlistName + ": Playlist does not exist");
    } else {
      userPlaylist.remove(playlistName.toLowerCase());
      System.out.println("Deleted playlist: " + playlistName);
    }
  }

  public void displaySearchResults(String searchTerm, List<Video> results) {
    if(results.size()==0) {
      System.out.println("No search results for " + searchTerm);
    } else {
      System.out.println("Here are the results for " + searchTerm + ":");
      int count = 1;
      for(Video v : results) {
        System.out.println(count + ") " + v);
        count++;
      }
      System.out.println("Would you like to play any of the above? If yes, specify the number of the video. \n"
              + "If your answer is not a valid number, we will assume it's a no.");
      var scanner = new Scanner(System.in);
      if(scanner.hasNextInt()) {
        var option = scanner.nextInt();
        if(results.size()>=option) {
          Video videoToPlay = results.get(option-1);
          playVideo(videoToPlay.getVideoId());
        }
      }
    }
  }

  public void searchVideos(String searchTerm) {
    List<Video> results = videoLibrary.getVideos().stream()
            .filter(v -> v.getTitle().toLowerCase(Locale.ROOT).contains(searchTerm.toLowerCase(Locale.ROOT)))
            .sorted(Comparator.comparing(Video::getTitle))
            .collect(Collectors.toList());

    displaySearchResults(searchTerm, results);
  }

  public void searchVideosWithTag(String videoTag) {
    List<Video> results = videoLibrary.getVideos().stream()
            .filter(v -> v.getTags().toString().toLowerCase(Locale.ROOT).contains(videoTag.toLowerCase(Locale.ROOT)))
            .sorted(Comparator.comparing(Video::getTitle))
            .collect(Collectors.toList());

    displaySearchResults(videoTag, results);
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