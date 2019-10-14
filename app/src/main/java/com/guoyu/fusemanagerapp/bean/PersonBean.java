package com.guoyu.fusemanagerapp.bean;

/**
 * Created by Administrator on 2019/10/14.
 */

public class PersonBean {

    /**
     * status : 200
     * data : {"appuserPics":"1,2,3","appuserType":2,"realName":"测试名字","userSex":"男","funcame":"城市微观审核,市民实名审核,咨询投诉反馈,城市微观反馈","id":5,"username":"13045134573","status":4}
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
         * appuserPics : 1,2,3
         * appuserType : 2
         * realName : 测试名字
         * userSex : 男
         * funcame : 城市微观审核,市民实名审核,咨询投诉反馈,城市微观反馈
         * id : 5
         * username : 13045134573
         * status : 4
         */

        private String appuserPics;
        private int appuserType;
        private String realName;
        private String userSex;
        private String funcame;
        private int id;
        private String username;
        private int status;

        public String getAppuserPics() {
            return appuserPics;
        }

        public void setAppuserPics(String appuserPics) {
            this.appuserPics = appuserPics;
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
