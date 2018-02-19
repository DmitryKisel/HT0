package com.epam.ht0.prj2;


import com.epam.ht0.prj2.Entity.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalogizator {
    MusicBank musicBank = new MusicBank();
    List<Song> repeatsList = new ArrayList<>();

    void mp3DataConverter(List<Mp3File> mp3FileList) {



        for (Mp3File file : mp3FileList) {
          Artist artist = createArtist(file.getArtistName());
          Album album = createAlbum(file.getAlbumInFile(), artist);
          List<Song> songList = createSongList(file.getAlbumInFile(), album);
          Song song = createSong(file.getSongInFile(), file.getFileDuration(),file.getPathToFile());

            for(Song songs : songList){
                if (songs.getSongName().equals(file.getSongInFile())){
                    repeatsList.add(songs);
                    repeatsList.add(song);
                }
            }
            songList.add(song);
        }

        System.out.println(musicBank.toString());

        ToHTML toHTML = new ToHTML();
        try {
            toHTML.writeHTML(musicBank.toHTMLStyle());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Данные совпадают");
        for(Song song: repeatsList){
            System.out.println(song.getSongName() + " " + song.getPathToFile());
        }
        }


    private Artist createArtist (String name) {
        Artist artist = musicBank.getMusicBankMap().get(name);
        if (artist == null) {
            artist = new Artist();
            musicBank.getMusicBankMap().put(name, artist);
        }
        return artist;
    }


    private Album createAlbum(String name, Artist artist) {
        Album album = artist.getArtistMap().get(name);
        if (album == null) {
            album = new Album();
            artist.getArtistMap().put(name, album);
        }
        return album;
    }

    private List<Song> createSongList (String title, Album album) {
        return album.getAlbumMap().computeIfAbsent(title, k -> new ArrayList<>());
    }

    private Song createSong(String name, Double duration, String pathToFile){
        Song song = new Song(name, duration, pathToFile);

    return song;
    }





}

