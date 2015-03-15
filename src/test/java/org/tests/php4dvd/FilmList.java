package org.tests.php4dvd;

import java.util.*;

public class FilmList {


    private final List<String> fullFilmList;

    public FilmList(List<String> fullFilmList) {
        this.fullFilmList = fullFilmList;
    }

    public String getRandomFilm() {
        return getRandomFilm(fullFilmList);
    }

    public String getRandomFilm(List<String> filmList) {
        Random rnd = new Random(System.currentTimeMillis());
        int i = rnd.nextInt(filmList.size());
        System.out.println(i);
        String randomFilm = filmList.get(i);
        System.out.println(randomFilm);
        return randomFilm;
    }


    public String calculateNonExistFilm() {
        String nonExistFilm = "NonExistFilmName";
        while (filmExists(nonExistFilm)) {
            nonExistFilm = nonExistFilm + "1";
        }
        System.out.println(nonExistFilm);
        return nonExistFilm;
    }

    public boolean filmExists(String nonExistFilm) {
        for (String film : fullFilmList) {
            if (film.toUpperCase().contains(nonExistFilm.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public List<String> getLongEnoughFilms() {
        List<String> listLongEnoughFilms = new ArrayList<>();
        for (String film : fullFilmList) {
            if (film.length() > 3 ) {
                listLongEnoughFilms.add(film);
            }
        }
        return listLongEnoughFilms;
    }

    public String findMostFrequentFilm() {
        Map<String, Integer> map = collectFilmNameFrequencies();
        String mostFrequentFilm = findGreatestValue(map);
        return mostFrequentFilm;
    }


    private String findGreatestValue(Map<String, Integer> map) {
        int max = 0;
        String greatestKey = "";
        for (String key : map.keySet()) {
            Integer value = map.get(key);
            if (value > max) {
                max = value;
                greatestKey = key;
            }
        }
        return greatestKey;
    }

    private Map<String, Integer> collectFilmNameFrequencies() {
        Map<String,Integer> map = new HashMap<>();
        for (String film : fullFilmList) {
            Integer count = map.get(film);
            if (count == null) {
                map.put(film,1);
            } else {
                map.put(film,count+1);
            }
        }
        return map;
    }

    public List<String> getFullList() {
        return fullFilmList;
    }
}
