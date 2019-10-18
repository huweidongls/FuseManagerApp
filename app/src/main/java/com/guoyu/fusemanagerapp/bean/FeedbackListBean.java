package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/18.
 */

public class FeedbackListBean {

    /**
     * status : 200
     * data : [{"id":60005,"title":"已反馈1","contentTop":"已反馈副标题1","consType":4,"content":"500呢","statusid":3,"createUser":651,"feeMemo":"500呢","feeUser":651,"feeStatus":1},{"id":60000,"title":"已反馈3","contentTop":"已反馈副标题3","consType":19,"content":"LOL呢","statusid":3,"createUser":651,"feeMemo":"400呢","feeUser":651,"feeStatus":1},{"id":60001,"title":"已反馈2","contentTop":"已反馈副标题2","consType":5,"content":"404呢","statusid":3,"createUser":651,"feeMemo":"LOL呢","feeUser":651,"feeStatus":1}]
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
         * id : 60005
         * title : 已反馈1
         * contentTop : 已反馈副标题1
         * consType : 4
         * content : 500呢
         * statusid : 3
         * createUser : 651
         * feeMemo : 500呢
         * feeUser : 651
         * feeStatus : 1
         */

        private int id;
        private String title;
        private String contentTop;
        private int consType;
        private String content;
        private int statusid;
        private int createUser;
        private String feeMemo;
        private int feeUser;
        private int feeStatus;

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

        public String getContentTop() {
            return contentTop;
        }

        public void setContentTop(String contentTop) {
            this.contentTop = contentTop;
        }

        public int getConsType() {
            return consType;
        }

        public void setConsType(int consType) {
            this.consType = consType;
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

        public int getCreateUser() {
            return createUser;
        }

        public void setCreateUser(int createUser) {
            this.createUser = createUser;
        }

        public String getFeeMemo() {
            return feeMemo;
        }

        public void setFeeMemo(String feeMemo) {
            this.feeMemo = feeMemo;
        }

        public int getFeeUser() {
            return feeUser;
        }

        public void setFeeUser(int feeUser) {
            this.feeUser = feeUser;
        }

        public int getFeeStatus() {
            return feeStatus;
        }

        public void setFeeStatus(int feeStatus) {
            this.feeStatus = feeStatus;
        }
    }
}
