package com.elanis.citytestfaifly.data.dto;


import java.util.List;

public class CityDetail {
    private List<City> geonames;

    public List<City> getGeonames() {
        return geonames;
    }

    public static class City {
        private String title;
        private String summary;
        private int elevation;
        private String wikipediaUrl;
        private String thumbnailImg;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getElevation() {
            return elevation;
        }

        public void setElevation(int elevation) {
            this.elevation = elevation;
        }

        public String getWikipediaUrl() {
            return wikipediaUrl;
        }

        public void setWikipediaUrl(String wikipediaUrl) {
            this.wikipediaUrl = wikipediaUrl;
        }

        public String getThumbnailImg() {
            return thumbnailImg;
        }

        public void setThumbnailImg(String thumbnailImg) {
            this.thumbnailImg = thumbnailImg;
        }
    }
}
