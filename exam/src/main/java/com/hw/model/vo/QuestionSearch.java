package com.hw.model.vo;

/**
 * 老师搜索某个考卷的题目
 * @author  
 * @date 2020/11/19 9:54 下午
 */
public class QuestionSearch {
    private Integer examId;
    private Integer page;
    private Integer limit;
    private Data data;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data{
        private String title;
        private Integer status;

        public Data() {
            status =-1;
        }

        public Data( String title, Integer type) {
            this.title = title;
            this.status = type;
        }


        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Data{" +
                    ", title='" + title + '\'' +
                    ", type=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "QuestionSearch{" +
                "page=" + page +
                ", limit=" + limit +
                ", data=" + data +
                '}';
    }
}
