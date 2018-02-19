package com.epam.ht0.prj2.Entity;

public class Mp3File {
    String fileName;
    String artistName;
    String albumInFile;
    String songInFile;
    String pathToFile;
    double fileDuration;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumInFile() {
        return albumInFile;
    }

    public void setAlbumInFile(String albumInFile) {
        this.albumInFile = albumInFile;
    }

    public String getSongInFile() {
        return songInFile;
    }

    public void setSongInFile(String songInFile) {
        this.songInFile = songInFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public double getFileDuration() {
        return fileDuration;
    }

    public void setFileDuration(double fileDuration) {
        this.fileDuration = fileDuration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Mp3File(String fileName, String artistName, String albumInFile, String songInFile, String pathToFile, double fileDuration) {
        this.fileName = fileName;
        this.artistName = artistName;
        this.albumInFile = albumInFile;
        this.songInFile = songInFile;
        this.pathToFile = pathToFile;
        this.fileDuration = fileDuration;
    }

    @Override
    public String toString() {
        return "Mp3File{" +
                "fileName='" + fileName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumInFile='" + albumInFile + '\'' +
                ", songInFile='" + songInFile + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                ", fileDuration=" + fileDuration +
                '}';
    }
}
