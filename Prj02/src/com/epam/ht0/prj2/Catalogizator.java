package com.epam.ht0.prj2;

import com.epam.ht0.prj2.Entity.*;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import java.util.*;

public class Catalogizator {
    private static final Logger LOGGER = LogManager.getLogger(Catalogizator.class);
    MusicBank musicBank = new MusicBank();
    Set<Mp3File> repeats = new HashSet<>();
    Map<String, Set<Mp3File>> repeatsMap = new HashMap<>();


    void mp3DataConverter(List<Mp3File> mp3FileList) {
        PropertyConfigurator.configure("log4j2.properties");

        // Creating new objects in MusicBank
        for (Mp3File file : mp3FileList) {
            Artist artist = createArtist(file.getArtistName());
            Album album = createAlbum(file.getAlbumInFile(), artist);
            List<Song> songList = createSongList(file.getAlbumInFile(), album);
            Song song = createSong(file.getSongInFile(), file.getFileDuration(), file.getPathToFile());

            //Searching for repeating tracks and adding them to repeats list
            for (Mp3File mp3File : mp3FileList) {
                if ((file.getSongInFile().equals(mp3File.getSongInFile())) && (file.getArtistName().equals(mp3File.getArtistName()))
                        && (file.getAlbumInFile().equals(mp3File.getAlbumInFile()))
                        && !file.getPathToFile().equals(mp3File.getPathToFile())) {
                    repeats.add(mp3File);
                    repeats.add(file);
                }
            }
            songList.add(song);
        }

      // Write music collection to HTML file
       ToHTML toHTML = new ToHTML();
        try {
            toHTML.writeHTML(toHTML.prepareForHTML(musicBank.toHTMLStyle()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sorting repeating files by song. One HashSet is created for each track to keep in a repeats.
        //Then create a map of repeats and put in this map info(Artist name + album name + song name) as key
        // and set of repeats as Value
        for (Mp3File file : repeats) {
            Set<Mp3File> set = new HashSet<>();
            for(Mp3File f: repeats){
                if (f.getArtistName().equals(file.getArtistName())){
                    set.add(f);
                }
            }
            String info = file.getArtistName() + ", " + file.getAlbumInFile() + ", " + file.getSongInFile();
            repeatsMap.put(info , set);
        }

        // Using log4j crete logfile of repeats
        for (Map.Entry<String, Set<Mp3File>> pair : repeatsMap.entrySet()) {
            LOGGER.log(Level.WARN,pair.getKey());
            for (Mp3File s : pair.getValue()) {
                LOGGER.log(Level.WARN,"+" + s.getPathToFile());
            }
        }
    }

    /**
     * This metod creates new Artist object in COllection
     * @param name
     * @return
     */
    private Artist createArtist(String name) {
        Artist artist = musicBank.getMusicBankMap().get(name);
        if (artist == null) {
            artist = new Artist();
            musicBank.getMusicBankMap().put(name, artist);
        }
        return artist;
    }

    /**
     * This metod creates new Album object of Artist
     * @param name
     * @return
     */
    private Album createAlbum(String name, Artist artist) {
        Album album = artist.getArtistMap().get(name);
        if (album == null) {
            album = new Album();
            artist.getArtistMap().put(name, album);
        }
        return album;
    }

    /**
     * This metod creates new list of song in Album
     * @param name
     * @return
     */
    private List<Song> createSongList(String title, Album album) {
        return album.getAlbumMap().computeIfAbsent(title, k -> new ArrayList<>());
    }

    /**
     * This metod creates new Song object
     * @param name
     * @return
     */
    private Song createSong(String name, Double duration, String pathToFile) {
        Song song = new Song(name, duration, pathToFile);
        return song;
    }
}

