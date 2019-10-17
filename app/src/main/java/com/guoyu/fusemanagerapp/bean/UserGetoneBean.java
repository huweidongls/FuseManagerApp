package com.guoyu.fusemanagerapp.bean;

/**
 * Created by Administrator on 2019/10/17.
 */

public class UserGetoneBean {

    /**
     * status : 200
     * errorMsg : 获取用户个人信息
     * data : {"id":501,"username":"18686817319","psd":"0b3ce46259443805ebd3707e9c892125","phone":"18686817319","realName":"哦呜","appuserPics":"[\"/upload/appHeadPhoto/fee3a688177e42079e0d2cf522335143.jpg\",\"/upload/appHeadPhoto/2c26130b87bc45aaa47ffcad28a77cd2.jpg\"]","appuserId":"36586655565","foundNo":"123","autonymName":0,"nameSex":"男"}
     */

    private String status;
    private String errorMsg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 501
         * username : 18686817319
         * psd : 0b3ce46259443805ebd3707e9c892125
         * phone : 18686817319
         * realName : 哦呜
         * appuserPics : ["/upload/appHeadPhoto/fee3a688177e42079e0d2cf522335143.jpg","/upload/appHeadPhoto/2c26130b87bc45aaa47ffcad28a77cd2.jpg"]
         * appuserId : 36586655565
         * foundNo : 123
         * autonymName : 0
         * nameSex : 男
         */

        private int id;
        private String username;
        private String psd;
        private String phone;
        private String realName;
        private String appuserPics;
        private String appuserId;
        private String foundNo;
        private int autonymName;
        private String nameSex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPsd() {
            return psd;
        }

        public void setPsd(String psd) {
            this.psd = psd;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getAppuserPics() {
            return appuserPics;
        }

        public void setAppuserPics(String appuserPics) {
            this.appuserPics = appuserPics;
        }

        public String getAppuserId() {
            return appuserId;
        }

        public void setAppuserId(String appuserId) {
            this.appuserId = appuserId;
        }

        public String getFoundNo() {
            return foundNo;
        }

        public void setFoundNo(String foundNo) {
            this.foundNo = foundNo;
        }

        public int getAutonymName() {
            return autonymName;
        }

        public void setAutonymName(int autonymName) {
            this.autonymName = autonymName;
        }

        public String getNameSex() {
            return nameSex;
        }

        public void setNameSex(String nameSex) {
            this.nameSex = nameSex;
        }
    }
}
