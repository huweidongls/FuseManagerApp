package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/12/31.
 */

public class AppEducationInfofindTypeBean {

    /**
     * status : 200
     * data : [{"id":6,"itemName":"数学"},{"id":7,"itemName":"语文"},{"id":8,"itemName":"英语"},{"id":9,"itemName":"物理"}]
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
         * itemName : 数学
         */

        private int id;
        private String itemName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }
    }
}
