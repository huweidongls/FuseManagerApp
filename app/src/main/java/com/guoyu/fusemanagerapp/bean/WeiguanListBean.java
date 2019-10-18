package com.guoyu.fusemanagerapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/10/18.
 */

public class WeiguanListBean implements Serializable {

    /**
     * status : 200
     * data : [{"id":10000,"title":"测试标题1","contentTop":"测试简介1","content":"啦啦啦啦啦哼哼哼哼","statusid":2,"createDate":"2019-10-09 20:14:52","createUser":651,"contentPic":"/upload/appHeadPhoto/ac1c10bf79754bb9987fcd29f1b563ef.jpg,/upload/appHeadPhoto/443e17ce6127430289e34c98b0459559.jpg","publishDate":"2019-10-09 20:14:52","publishUser":651,"apprOpp":"同意了,小伙子","apprUser":651,"apprDate":"2019-10-09 20:14:52","apprUserName":"审核人姓名","feeMemo":"哼哼","feeUser":651,"feeDate":"2019-10-09 20:14:52","feeStatus":1,"nickName":"未实名认证用户","nikePic":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg"}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 10000
         * title : 测试标题1
         * contentTop : 测试简介1
         * content : 啦啦啦啦啦哼哼哼哼
         * statusid : 2
         * createDate : 2019-10-09 20:14:52
         * createUser : 651
         * contentPic : /upload/appHeadPhoto/ac1c10bf79754bb9987fcd29f1b563ef.jpg,/upload/appHeadPhoto/443e17ce6127430289e34c98b0459559.jpg
         * publishDate : 2019-10-09 20:14:52
         * publishUser : 651
         * apprOpp : 同意了,小伙子
         * apprUser : 651
         * apprDate : 2019-10-09 20:14:52
         * apprUserName : 审核人姓名
         * feeMemo : 哼哼
         * feeUser : 651
         * feeDate : 2019-10-09 20:14:52
         * feeStatus : 1
         * nickName : 未实名认证用户
         * nikePic : /upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg
         */

        private int id;
        private String title;
        private String contentTop;
        private String content;
        private int statusid;
        private String createDate;
        private int createUser;
        private String contentPic;
        private String publishDate;
        private int publishUser;
        private String apprOpp;
        private int apprUser;
        private String apprDate;
        private String apprUserName;
        private String feeMemo;
        private int feeUser;
        private String feeDate;
        private int feeStatus;
        private String nickName;
        private String nikePic;

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

        public String getApprOpp() {
            return apprOpp;
        }

        public void setApprOpp(String apprOpp) {
            this.apprOpp = apprOpp;
        }

        public int getApprUser() {
            return apprUser;
        }

        public void setApprUser(int apprUser) {
            this.apprUser = apprUser;
        }

        public String getApprDate() {
            return apprDate;
        }

        public void setApprDate(String apprDate) {
            this.apprDate = apprDate;
        }

        public String getApprUserName() {
            return apprUserName;
        }

        public void setApprUserName(String apprUserName) {
            this.apprUserName = apprUserName;
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

        public String getFeeDate() {
            return feeDate;
        }

        public void setFeeDate(String feeDate) {
            this.feeDate = feeDate;
        }

        public int getFeeStatus() {
            return feeStatus;
        }

        public void setFeeStatus(int feeStatus) {
            this.feeStatus = feeStatus;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getNikePic() {
            return nikePic;
        }

        public void setNikePic(String nikePic) {
            this.nikePic = nikePic;
        }
    }
}
