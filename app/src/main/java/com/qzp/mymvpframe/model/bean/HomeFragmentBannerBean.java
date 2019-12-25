package com.qzp.mymvpframe.model.bean;

import java.util.List;

/**
 * Created by qzp on 2018/11/30.
 */

public class HomeFragmentBannerBean {


    private List<CarouselListBean> carouselList;

    public List<CarouselListBean> getCarouselList() {
        return carouselList;
    }

    public void setCarouselList(List<CarouselListBean> carouselList) {
        this.carouselList = carouselList;
    }

    public static class CarouselListBean {
        /**
         * detailsLink :
         * id : 15
         * imgUrl : http://oss.fk.houdask.com/sys/i/18/11/9eb878d5046541f0afe7b57c8649a3c8.jpg
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
