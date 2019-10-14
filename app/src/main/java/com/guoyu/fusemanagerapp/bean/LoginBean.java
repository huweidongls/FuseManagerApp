package com.guoyu.fusemanagerapp.bean;

/**
 * Created by Administrator on 2019/10/12.
 */

public class LoginBean {

    /**
     * status : 200
     * data : {"id":5,"userPic":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg","username":"13045134573","password":"***","isDelete":0,"createDate":"2019-10-11T03:51:01.000+0000","realName":"测试名字","status":4,"userType":2,"appuserType":2,"appuserPics":"1,2,3","userSex":"男"}
     * userNameFromToken : eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzA0NTEzNDU3MyIsImNyZWF0ZWQiOjE1NzA4NTIxNDc4NDYsImlzcyI6NSwiZXhwIjoxNTcxNDU2OTQ3fQ.Q145DzpbC4ia7KzQN9BSJ1DEB-q8geSOpSMqANBBtIFyqrpwh-ZGPrSVdb0QI5sfVZHzo9nd8vb1m0yl12iuOw=app
     * errorMsg : 登录成功
     */

    private String status;
    private DataBean data;
    private String userNameFromToken;
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

    public String getUserNameFromToken() {
        return userNameFromToken;
    }

    public void setUserNameFromToken(String userNameFromToken) {
        this.userNameFromToken = userNameFromToken;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class DataBean {
        /**
         * id : 5
         * userPic : /upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg
         * username : 13045134573
         * password : ***
         * isDelete : 0
         * createDate : 2019-10-11T03:51:01.000+0000
         * realName : 测试名字
         * status : 4
         * userType : 2
         * appuserType : 2
         * appuserPics : 1,2,3
         * userSex : 男
         */

        private int id;
        private String userPic;
        private String username;
        private String password;
        private int isDelete;
        private String createDate;
        private String realName;
        private int status;
        private int userType;
        private int appuserType;
        private String appuserPics;
        private String userSex;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getAppuserType() {
            return appuserType;
        }

        public void setAppuserType(int appuserType) {
            this.appuserType = appuserType;
        }

        public String getAppuserPics() {
            return appuserPics;
        }

        public void setAppuserPics(String appuserPics) {
            this.appuserPics = appuserPics;
        }

        public String getUserSex() {
            return userSex;
        }

        public void setUserSex(String userSex) {
            this.userSex = userSex;
        }
    }
}
