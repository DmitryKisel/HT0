package com.epam.ht0.prj2.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {
    private Map<String, List<Song>> albumMap;

    public Map<String, List<Song>> getAlbumMap() {
        return albumMap;
    }


    public Album() {
       albumMap = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Song>> entry : albumMap.entrySet()) {
            for (Song song : entry.getValue()) {
               sb.append(song.toString());
            }
        }
        return sb.toString();
    }

    /**
     * This method adopts object data for writing in HTML-file
     * @return
     */
    public String toHTMLStyle() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<Song>> entry : albumMap.entrySet()) {
            for (Song song : entry.getValue()) {
                sb.append(song.toHTMLStyle());
            }
        }
        return sb.toString();
    }
}
