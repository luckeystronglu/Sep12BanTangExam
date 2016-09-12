package com.qf.entity;

import java.util.List;

/**
 * Created by Ken on 2016/9/12.15:07
 */
public class HomeHeadEntity {


    /**
     * status : 1
     * msg : 成功
     * ts : 1473642718
     * data : {"banner":[{"id":"1085","title":"我控几不住我记几的手啊","sub_title":"","type":"topic_list","topic_type":"","photo":"http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/52545510.jpg?v=1473416196","extend":"2309,3223,1292,6540,2676,2835,2405,2307,1122,2683,1830,1734,1105,2292,1563","index":138,"parent_id":"1","photo_width":"900","photo_height":"432","is_show_icon":0},{"id":"1080","title":"开学季，我都买了什么？","sub_title":"","type":"topic_list","topic_type":"","photo":"http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/50495155.jpg?v=1473244594","extend":"1923,1536,984,964,1040,1389,2209,2271,2437,2221","index":137,"parent_id":"1","photo_width":"900","photo_height":"432","is_show_icon":0},{"id":"1082","title":"懒人必备 省时高科技","sub_title":"","type":"topic_list","topic_type":"","photo":"http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/10051505.jpg?v=1473330525","extend":"10803,8323,7220,6789,5796","index":135,"parent_id":"1","photo_width":"900","photo_height":"432","is_show_icon":0},{"id":"1078","title":"夏末的气味 跟青春有关","sub_title":"","type":"topic_list","topic_type":"","photo":"http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/97545351.jpg?v=1473075210","extend":"4991,4290,3100,2255,1585,2202,2010,1985,2038","index":134,"parent_id":"1","photo_width":"900","photo_height":"432","is_show_icon":0},{"id":"1077","title":"活动 | 新人来种草","sub_title":"","type":"topic_detail","topic_type":"2","photo":"http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/54514897.jpg?v=1473041366","extend":"10577","index":133,"parent_id":"1","photo_width":"900","photo_height":"432","is_show_icon":0}],"insert_element":[],"category_element":[{"id":"99999999","title":"精选","sub_title":"","type":"topic_main","topic_type":"","photo":"","extend":"","is_show_icon":0},{"id":"812","title":"原创精选","sub_title":"","type":"topic_list","topic_type":"","photo":"","extend":"10875,10944,11101,10571,10903,10845,8605,10819,10962,10863,8737,10684,10803,10639,9736,10690,10488,8567,10057,10078,10485,10193,10279,10370,10304,9883,10319,9917,9438,9565,8323,7792,10028,10088,10067,9983,9631,9624,9754,9567,9813,9254,9627,9399,7617,9502,9455,9175,9374,9313","index":150,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"612","title":"一周最热","sub_title":"","type":"topic_list","topic_type":"","photo":"","extend":"10063,10891,10516,8605,10962,10545,10690,10423,8737,10456,10950,10359,10068,10550,10803,10828,11012,10449,10986,9975","index":147,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"420","title":"美妆&穿搭","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"13","index":143,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"413","title":"礼物","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"3","index":142,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"422","title":"美食","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"15","index":141,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"423","title":"设计感","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"16","index":140,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"417","title":"文艺","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"8","index":135,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0},{"id":"415","title":"学生党","sub_title":"","type":"topic_scene","topic_type":"","photo":"","extend":"5","index":130,"parent_id":"23","photo_width":"","photo_height":"","is_show_icon":0}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1085
         * title : 我控几不住我记几的手啊
         * sub_title :
         * type : topic_list
         * topic_type :
         * photo : http://7xiwnz.com2.z0.glb.qiniucdn.com/element1/201609/52545510.jpg?v=1473416196
         * extend : 2309,3223,1292,6540,2676,2835,2405,2307,1122,2683,1830,1734,1105,2292,1563
         * index : 138
         * parent_id : 1
         * photo_width : 900
         * photo_height : 432
         * is_show_icon : 0
         */

        private List<BannerBean> banner;
        /**
         * id : 99999999
         * title : 精选
         * sub_title :
         * type : topic_main
         * topic_type :
         * photo :
         * extend :
         * is_show_icon : 0
         */

        private List<CategoryElementBean> category_element;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryElementBean> getCategory_element() {
            return category_element;
        }

        public void setCategory_element(List<CategoryElementBean> category_element) {
            this.category_element = category_element;
        }

        public static class BannerBean {
            private String photo;

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }
        }

        public static class CategoryElementBean {
            private String title;
            private String extend;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getExtend() {
                return extend;
            }

            public void setExtend(String extend) {
                this.extend = extend;
            }
        }
    }
}
