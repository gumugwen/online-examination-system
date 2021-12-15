package com.hw.model.vo;

/**
 * 考卷搜索VO
 * @author  
 * @date 2020/11/19 3:21 下午
 */
public class ExamSearchVo {
    private Integer page;
    private Integer limit;
    private Data data;

    public ExamSearchVo() {
    }

    public ExamSearchVo(Integer page, Integer limit, Data data) {
        this.page = page;
        this.limit = limit;
        this.data = data;
    }

    public static class Data{

        private String title;
        private Integer status;
        private Integer valid;

        public Data() {
        }

        public Data(String title, Integer status, Integer valid) {
            this.title = title;
            this.status = status;
            this.valid = valid;
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

        public Integer getValid() {
            return valid;
        }

        public void setValid(Integer valid) {
            this.valid = valid;
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
}
