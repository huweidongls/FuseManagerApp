package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/16.
 */

public class MenuBean {

    /**
     * status : 200
     * data : [{"id":30,"funName":"城市微观审核","funCode":"WGSH","logoPic":"/upload/icon/chengshiweiguanshenhe.png","firstpageNo":-1,"moreNo":-1,"unregSee":0,"isManageappmenu":1,"androidUrl":"androidUrl","iosUrl":"iosUrl"},{"id":31,"funName":"城市微观反馈","funCode":"WGFK","logoPic":"/upload/icon/chengshiweiguanfankui.png","firstpageNo":-1,"moreNo":-1,"unregSee":0,"isManageappmenu":2,"androidUrl":"androidUrl","iosUrl":"iosUrl"}]
     * errorMsg : 获取成功
     */

    private String status;
    private String errorMsg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 30
         * funName : 城市微观审核
         * funCode : WGSH
         * logoPic : /upload/icon/chengshiweiguanshenhe.png
         * firstpageNo : -1
         * moreNo : -1
         * unregSee : 0
         * isManageappmenu : 1
         * androidUrl : androidUrl
         * iosUrl : iosUrl
         */

        private int id;
        private String funName;
        private String funCode;
        private String logoPic;
        private int firstpageNo;
        private int moreNo;
        private int unregSee;
        private int isManageappmenu;
        private String androidUrl;
        private String iosUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFunName() {
            return funName;
        }

        public void setFunName(String funName) {
            this.funName = funName;
        }

        public String getFunCode() {
            return funCode;
        }

        public void setFunCode(String funCode) {
            this.funCode = funCode;
        }

        public String getLogoPic() {
            return logoPic;
        }

        public void setLogoPic(String logoPic) {
            this.logoPic = logoPic;
        }

        public int getFirstpageNo() {
            return firstpageNo;
        }

        public void setFirstpageNo(int firstpageNo) {
            this.firstpageNo = firstpageNo;
        }

        public int getMoreNo() {
            return moreNo;
        }

        public void setMoreNo(int moreNo) {
            this.moreNo = moreNo;
        }

        public int getUnregSee() {
            return unregSee;
        }

        public void setUnregSee(int unregSee) {
            this.unregSee = unregSee;
        }

        public int getIsManageappmenu() {
            return isManageappmenu;
        }

        public void setIsManageappmenu(int isManageappmenu) {
            this.isManageappmenu = isManageappmenu;
        }

        public String getAndroidUrl() {
            return androidUrl;
        }

        public void setAndroidUrl(String androidUrl) {
            this.androidUrl = androidUrl;
        }

        public String getIosUrl() {
            return iosUrl;
        }

        public void setIosUrl(String iosUrl) {
            this.iosUrl = iosUrl;
        }
    }
}
