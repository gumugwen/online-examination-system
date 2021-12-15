package com.hw.model.vo;

/**
 * @author  
 * @date 2020/11/20 2:42 下午
 */
public class ExamRecordSearchVo {
    private Integer page;
    private Integer limit;
    private Data data;


    public static class Data{
        private String title;
        private Integer status;
        private Integer classId;

        public Data() {
        }

        public Data(String title, Integer status, Integer classId) {
            this.title = title;
            this.status = status;
            this.classId = classId;
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

        public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }
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

    @Override
    public String toString() {
        return "ExamRecordSearchVo{" +
                "page=" + page +
                ", limit=" + limit +
                ", data=" + data +
                '}';
    }
}
