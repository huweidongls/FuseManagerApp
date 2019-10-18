package com.guoyu.fusemanagerapp.bean;

import java.util.List;

/**
 * Created by Administrator on 2019/10/18.
 */

public class TicketServiceListBean {
    /**
     * status : 200
     * data : [{"id":21,"title":"string","contentShort":"string","mainPic":"string","content":"string","ticketMoney":0,"ticketMoneyMore":"string","areaTraffic":"string","contentPic":"string","statusid":2,"createDate":"2019-10-16 15:40:16","createUser":102,"publishDate":"2019-10-16 15:32:19","publishUser":651,"isRecommend":1}]
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
         * id : 21
         * title : string
         * contentShort : string
         * mainPic : string
         * content : string
         * ticketMoney : 0
         * ticketMoneyMore : string
         * areaTraffic : string
         * contentPic : string
         * statusid : 2
         * createDate : 2019-10-16 15:40:16
         * createUser : 102
         * publishDate : 2019-10-16 15:32:19
         * publishUser : 651
         * isRecommend : 1
         */

        private int id;
        private String title;
        private String contentShort;
        private String mainPic;
        private String content;
        private int ticketMoney;
        private String ticketMoneyMore;
        private String areaTraffic;
        private String contentPic;
        private int statusid;
        private String createDate;
        private int createUser;
        private String publishDate;
        private int publishUser;
        private int isRecommend;

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

        public String getContentShort() {
            return contentShort;
        }

        public void setContentShort(String contentShort) {
            this.contentShort = contentShort;
        }

        public String getMainPic() {
            return mainPic;
        }

        public void setMainPic(String mainPic) {
            this.mainPic = mainPic;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getTicketMoney() {
            return ticketMoney;
        }

        public void setTicketMoney(int ticketMoney) {
            this.ticketMoney = ticketMoney;
        }

        public String getTicketMoneyMore() {
            return ticketMoneyMore;
        }

        public void setTicketMoneyMore(String ticketMoneyMore) {
            this.ticketMoneyMore = ticketMoneyMore;
        }

        public String getAreaTraffic() {
            return areaTraffic;
        }

        public void setAreaTraffic(String areaTraffic) {
            this.areaTraffic = areaTraffic;
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

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public int getPublishUser() {
            return publishUser;
        }

        public void setPublishUser(int publishUser) {
            this.publishUser = publishUser;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }
    }
}
