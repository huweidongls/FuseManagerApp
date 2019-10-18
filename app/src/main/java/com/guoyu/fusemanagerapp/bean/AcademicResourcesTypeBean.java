package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/18.
 */

public class AcademicResourcesTypeBean {
    /**
     * status : 200
     * data : [{"id":6,"subName":"数学"},{"id":7,"subName":"语文"},{"id":8,"subName":"英语"},{"id":9,"subName":"物理"}]
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
         * id : 6
         * subName : 数学
         */

        private int id;
        private String subName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubName() {
            return subName;
        }

        public void setSubName(String subName) {
            this.subName = subName;
        }
    }
}
