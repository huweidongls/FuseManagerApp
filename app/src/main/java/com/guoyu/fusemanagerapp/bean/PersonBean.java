package com.guoyu.fusemanagerapp.bean;

/**
 * Created by Administrator on 2019/10/14.
 */

public class PersonBean {


    /**
     * status : 200
     * data : {"appuserPics":"/upload/appHeadPhoto/450c633ea54f43f3aa9f6c3f2d4fb3f0.jpg,","USER_PIC":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg","appuserType":2,"realName":"东哥好几把大","userSex":"女","funcame":"市民实名审核,咨询投诉反馈","id":61,"username":"15561817068","status":2}
     * errorMsg : 查询成功
     */

    private String status;
    private DataBean data;
    private String errorMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * appuserPics : /upload/appHeadPhoto/450c633ea54f43f3aa9f6c3f2d4fb3f0.jpg,
         * USER_PIC : /upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg
         * appuserType : 2
         * realName : 东哥好几把大
         * userSex : 女
         * funcame : 市民实名审核,咨询投诉反馈
         * id : 61
         * username : 15561817068
         * status : 2
         */

        private String appuserPics;
        private String USER_PIC;
        private int appuserType;
        private String realName;
        private String userSex;
        private String funcame;
        private int id;
        private String username;
        private int status;
        private String depName;

        public String getDepName() {
            return depName;
        }

        public void setDepName(String depName) {
            this.depName = depName;
        }

        public String getAppuserPics() {
            return appuserPics;
        }

        public void setAppuserPics(String appuserPics) {
            this.appuserPics = appuserPics;
        }

        public String getUSER_PIC() {
            return USER_PIC;
        }

        public void setUSER_PIC(String USER_PIC) {
            this.USER_PIC = USER_PIC;
        }

        public int getAppuserType() {
            return appuserType;
        }

        public void setAppuserType(int appuserType) {
            this.appuserType = appuserType;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }

        public String getFuncame() {
            return funcame;
        }

        public void setFuncame(String funcame) {
            this.funcame = funcame;
        }

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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
