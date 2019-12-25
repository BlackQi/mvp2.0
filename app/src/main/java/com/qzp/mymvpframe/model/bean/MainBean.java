package com.qzp.mymvpframe.model.bean;

import java.util.List;

/**
 * Created by qizepu on 2017/5/17.
 */

public class MainBean {


    private List<CarouselListBean> carouselList;

    public List<CarouselListBean> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<CarouselListBean> carouselList) {
        this.carouselList = carouselList;
    }

    public static class CarouselListBean {
        /**
         * detailsLink : http://www.baidu.com
         * id : 1
         * imgUrl : http://hdlsxy.oss.houdask.com/sys/i/18/11/f63d7400fa9540308de7f7eb8febe740.png
         * sort : 1
         * useable : 1
         */

        private String detailsLink;
        private String id;
        private String imgUrl;
        private int sort;
        private String useable;

        public String getDetailsLink() {
            return detailsLink;
        }

        public void setDetailsLink(String detailsLink) {
            this.detailsLink = detailsLink;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getUseable() {
            return useable;
        }

        public void setUseable(String useable) {
            this.useable = useable;
        }
    }
}
