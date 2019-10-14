package com.guoyu.fusemanagerapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2019/10/14.
 */

public class APPFuncInfoApplyFoorListBean implements Serializable {

    /**
     * status : 200
     * data : [{"id":33,"funName":"市民实名审核","funCode":"SMSH","firstpageNo":-1,"moreNo":-1,"isManageappmenu":4},{"id":32,"funName":"咨询投诉反馈","funCode":"TSFK","firstpageNo":-1,"moreNo":-1,"isManageappmenu":3},{"id":31,"funName":"城市微观反馈","funCode":"WGFK","firstpageNo":-1,"moreNo":-1,"isManageappmenu":2},{"id":30,"funName":"城市微观审核","funCode":"WGSH","firstpageNo":-1,"moreNo":-1,"isManageappmenu":1}]
     * errorMsg : 查询成功
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

    public static class DataBean implements Serializable {
        /**
         * id : 33
         * funName : 市民实名审核
         * funCode : SMSH
         * firstpageNo : -1
         * moreNo : -1
         * isManageappmenu : 4
         */

        private int id;
        private String funName;
        private String funCode;
        private int firstpageNo;
        private int moreNo;
        private int isManageappmenu;
        private int isSelect = 0;

        public int getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(int isSelect) {
            this.isSelect = isSelect;
        }

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

        public int getIsManageappmenu() {
            return isManageappmenu;
        }

        public void setIsManageappmenu(int isManageappmenu) {
            this.isManageappmenu = isManageappmenu;
        }
    }
}
