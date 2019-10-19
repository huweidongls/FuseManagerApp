package com.guoyu.fusemanagerapp.bean;

/**
 * Created by Administrator on 2019/10/19.
 */

public class FeedbackDetailsBean {
    /**
     * status : 200
     * data : {"id":23,"title":"你仔细why","contentTop":"人做最肉皮子吐了","consType":5,"content":"哦哦呼呼呼呼","contentPic":"upload/appHeadPhoto/59e0b4e3666c4f21bd60cce682e90e6c.jpeg,upload/appHeadPhoto/ccbd8b7aa22049fd9f4b6f6374b94527.jpeg,upload/appHeadPhoto/90d46d7b386c48d7ab1cdcf02e0dd1bb.jpeg,","statusid":1,"createDate":"2019-10-11 15:29:21","createUser":651,"deptId":1,"deptName":"精纳小垃圾部门","username":"未实名认证用户","userPic":"/upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg"}
     */

    private String status;
    private DataBean data;

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

    public static class DataBean {
        /**
         * id : 23
         * title : 你仔细why
         * contentTop : 人做最肉皮子吐了
         * consType : 5
         * content : 哦哦呼呼呼呼
         * contentPic : upload/appHeadPhoto/59e0b4e3666c4f21bd60cce682e90e6c.jpeg,upload/appHeadPhoto/ccbd8b7aa22049fd9f4b6f6374b94527.jpeg,upload/appHeadPhoto/90d46d7b386c48d7ab1cdcf02e0dd1bb.jpeg,
         * statusid : 1
         * createDate : 2019-10-11 15:29:21
         * createUser : 651
         * deptId : 1
         * deptName : 精纳小垃圾部门
         * username : 未实名认证用户
         * userPic : /upload/appHeadPhoto/ec017de9b5646ad8f833ca7f34fd04d.jpg
         */

        private int id;
        private String title;
        private String contentTop;
        private int consType;
        private String content;
        private String contentPic;
        private int statusid;
        private String createDate;
        private int createUser;
        private int deptId;
        private String deptName;
        private String username;
        private String userPic;

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

        public String getContentPic() {
            return contentPic;
        }

        public void setContentPic(String contentPic) {
            this.contentPic = contentPic;
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

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserPic() {
            return userPic;
        }

        public void setUserPic(String userPic) {
            this.userPic = userPic;
        }
    }
}
