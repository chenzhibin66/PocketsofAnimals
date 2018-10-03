package example.chaoyueteam.com.pocketsofanimals.image;

import java.util.List;

public class plant {
    private Long log_id;
    private  Long baike_info;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    private List<Result> result;

    public void setBaike_info(Long baike_info) {
        this.baike_info = baike_info;
    }

    public Long getBaike_info() {

        return baike_info;
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
        private double score;
        private String name;
        public double getScore() {
            return score;
        }
        public void setScore(double score) {
            this.score = score;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
