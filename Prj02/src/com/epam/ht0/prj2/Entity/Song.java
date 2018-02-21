package com.epam.ht0.prj2.Entity;

public class Song {
   private String songName;
   private Double duration;
   private String pathToFile;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Song(String songName, Double duration, String pathToFile) {
        this.songName = songName;
        this.duration = duration;
        this.pathToFile = pathToFile;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (!songName.equals(song.songName)) return false;
        if (!duration.equals(song.duration)) return false;
        return pathToFile.equals(song.pathToFile);
    }

    @Override
    public int hashCode() {
        int result = songName.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + pathToFile.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
          sb.append("\t").append("\t")
                        .append("song name: ").append(songName)
                        .append(", duration: ").append(duration).append(" seconds")
                        .append(", path: ").append(pathToFile)
                        .append("\n");
    return sb.toString();
    }

    /**
     * This method adopts object data for writing in HTML-file
     * @return
     */
    public String toHTMLStyle() {
              int minutes =  (int) Math.round(duration/60);
              int seconds = (int) Math.round(duration%60);
        StringBuilder sb = new StringBuilder();

        sb.append("<pre>").append("        ").append(songName).append("  ")
                .append(minutes).append(":").append(seconds).append("  ").append("(")
                .append(pathToFile).append(")").append("</pre>");
        return sb.toString();
    }



}
