package com.epam.ht0.prj2.Entity;

public class Mp3File {
    private String fileName;
    private String artistName;
    private String albumInFile;
    private String songInFile;
    private String pathToFile;
    private double fileDuration;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mp3File mp3File = (Mp3File) o;

        if (Double.compare(mp3File.fileDuration, fileDuration) != 0) return false;
        if (!fileName.equals(mp3File.fileName)) return false;
        if (!artistName.equals(mp3File.artistName)) return false;
        if (!albumInFile.equals(mp3File.albumInFile)) return false;
        if (!songInFile.equals(mp3File.songInFile)) return false;
        return pathToFile.equals(mp3File.pathToFile);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = fileName.hashCode();
        result = 31 * result + artistName.hashCode();
        result = 31 * result + albumInFile.hashCode();
        result = 31 * result + songInFile.hashCode();
        result = 31 * result + pathToFile.hashCode();
        temp = Double.doubleToLongBits(fileDuration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
