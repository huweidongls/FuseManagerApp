package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/17.
 */

public class HomeNewsBean {
    /**
     * status : 200
     * data : [{"id":21,"title":"string","content":"string","statusid":2,"createDate":"2019-10-16T07:34:26.000+0000","createUser":651,"contentPic":"string","eduType":1,"publishDate":"2019-10-16T07:32:19.000+0000","publishUser":651,"scopeType":"string","scopeArea":"string","scopeSchool":"string"},{"id":1,"title":"string","content":"string","statusid":2,"createDate":"2019-10-09T12:14:52.000+0000","createUser":651,"contentPic":"string","eduType":1,"publishDate":"2019-10-09T12:14:24.000+0000","publishUser":651,"scopeType":"string","scopeArea":"string","scopeSchool":"string"}]
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 21
         * title : string
         * content : string
         * statusid : 2
         * createDate : 2019-10-16T07:34:26.000+0000
         * createUser : 651
         * contentPic : string
         * eduType : 1
         * publishDate : 2019-10-16T07:32:19.000+0000
         * publishUser : 651
         * scopeType : string
         * scopeArea : string
         * scopeSchool : string
         */

        private int id;
        private String title;
        private String content;
        private int statusid;
        private String createDate;
        private int createUser;
        private String contentPic;
        private int eduType;
        private String publishDate;
        private int publishUser;
        private String scopeType;
        private String scopeArea;
        private String scopeSchool;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStatusid() {
            return statusid;
        }

        public void setStatusid(int statusid) {
            this.statusid = statusid;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getContentPic() {
            return contentPic;
        }

        public void setContentPic(String contentPic) {
            this.contentPic = contentPic;
        }

        public int getEduType() {
            return eduType;
        }

        public void setEduType(int eduType) {
            this.eduType = eduType;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public int getPublishUser() {
            return publishUser;
        }

        public void setPublishUser(int publishUser) {
            this.publishUser = publishUser;
        }

        public String getScopeType() {
            return scopeType;
        }

        public void setScopeType(String scopeType) {
            this.scopeType = scopeType;
        }

        public String getScopeArea() {
            return scopeArea;
        }

        public void setScopeArea(String scopeArea) {
            this.scopeArea = scopeArea;
        }

        public String getScopeSchool() {
            return scopeSchool;
        }

        public void setScopeSchool(String scopeSchool) {
            this.scopeSchool = scopeSchool;
        }
    }
}
