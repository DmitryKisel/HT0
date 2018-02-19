package com.epam.ht0.prj2.Entity;

import java.util.HashMap;
import java.util.Map;

public class Artist {
    Map<String, Album> artistMap;

    public Map<String, Album> getArtistMap() {
        return artistMap;
    }

    public void setArtistMap(Map<String, Album> artistMap) {
        this.artistMap = artistMap;
    }

    public Artist() {
        artistMap = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Album> entry :
                artistMap.entrySet()) {
            sb.append("\t").append(entry.getKey()).append("\n");
            sb.append(entry.getValue().toString());
        }
        return sb.toString();
    }

    public String toHTMLStyle() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Album> entry :
                artistMap.entrySet()) {
            sb.append("<pre>").append("    ").append(entry.getKey())
                    .append("</pre>");
            sb.append(entry.getValue().toHTMLStyle());
        }
        return sb.toString();
    }
}
