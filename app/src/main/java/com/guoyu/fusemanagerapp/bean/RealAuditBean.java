package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/17.
 */

public class RealAuditBean {

    /**
     * status : 200
     * data : [{"id":701,"username":"17612434198","psd":"e10adc3949ba59abbe56e057f20f883e","isDelete":1,"phone":"17612434198","realName":"未实名认证用户","status":1,"autonymName":0,"appuserId":"2110222200000000000"},{"id":651,"userPic":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg","username":"15244615473","psd":"a7f4bbc0f69ff4e85b00c2c7b2adac81","isDelete":1,"phone":"15244615473","realName":"未实名认证用户","status":1,"autonymName":0,"appuserId":"2110222200000000000"},{"id":602,"userPic":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg","username":"13796068265","psd":"d4ed5255f23a83b77745c112eddabee3","isDelete":1,"phone":"13796068265","realName":"13796068265","status":1,"autonymName":0,"appuserId":"2110222200000000000"}]
     * totalPage : 0
     * totalCount : 3
     */

    private String status;
    private int totalPage;
    private int totalCount;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 701
         * username : 17612434198
         * psd : e10adc3949ba59abbe56e057f20f883e
         * isDelete : 1
         * phone : 17612434198
         * realName : 未实名认证用户
         * status : 1
         * autonymName : 0
         * appuserId : 2110222200000000000
         * userPic : /upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg
         */

        private int id;
        private String username;
        private String psd;
        private int isDelete;
        private String phone;
        private String realName;
        private int status;
        private int autonymName;
        private String appuserId;
        private String userPic;

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

        public int getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(int isDelete) {
            this.isDelete = isDelete;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAutonymName() {
            return autonymName;
        }

        public void setAutonymName(int autonymName) {
            this.autonymName = autonymName;
        }

        public String getAppuserId() {
            return appuserId;
        }

        public void setAppuserId(String appuserId) {
            this.appuserId = appuserId;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }
    }
}
