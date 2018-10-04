package example.chaoyueteam.com.pocketsofanimals.image;

import java.util.List;

public class Animal{
    private Long log_id;
    private List<Result> result;
    private  List<Baike_info> baike_infos;
    private  String  names;

    public void setDescription(String description) {
        this.description = description;
    }


    public String getNames() {
        return names;
    }



    public void setNames(String names) {
        this.names = names;
    }

    public String getDescription() {

        return description;
    }

    private String description
            ;



    public void setBaike_infos(List<Baike_info> baike_infos) {
        this.baike_infos = baike_infos;
    }

    public List<Baike_info> getBaike_infos() {

        return baike_infos;
    }

    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public static class Result {
        private String score;
        private String name;
        private String baike_info;
        public String getScore() {
            return score;
        }

        public void setBaike_info(String baike_info) {
            this.baike_info = baike_info;
        }

        public String getBaike_info() {

            return baike_info;
        }

        public void setScore(String score) {
            this.score = score;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
    public  static  class Baike_info{
        private  String baike_url;
        private  String img_url;
        private  String description;

        public void setBaike_url(String baike_url) {
            this.baike_url = baike_url;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setImg_url(String img_url) {

            this.img_url = img_url;
        }

        public String getDescription() {
            return description;

        }

        public String getImg_url() {

            return img_url;
        }

        public String getBaike_url() {

            return baike_url;
        }
    }
}