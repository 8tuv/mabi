package com.ying.qixu.Bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class newsBean  {


    /**
     * code : 1
     * msg : 数据列表
     * page : 1
     * pagecount : 440
     * limit : 20
     * total : 8791
     * list : [{"vod_id":19340,"vod_name":"2019年辽宁春晚","type_id":3,"type_name":"综艺","vod_remarks":"BD","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://ww2.sinaimg.cn/large/bfe05ea9ly1fzvqhaojikj207i0b9t94.jpg"},{"vod_id":19339,"vod_name":"喜剧之王2","type_id":7,"type_name":"喜剧片","vod_remarks":"TC首发","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://ww2.sinaimg.cn/large/bfe05ea9ly1fzvqdlksx4j207i0a9aaa.jpg"},{"vod_id":19338,"vod_name":"2019安徽卫视春晚","type_id":3,"type_name":"综艺","vod_remarks":"BD","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx4.sinaimg.cn/large/006VRvsSgy1fzvps9q9m3j305006kq32.jpg"},{"vod_id":19337,"vod_name":"2019央视春晚","type_id":3,"type_name":"综艺","vod_remarks":"BD","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx4.sinaimg.cn/large/006VRvsSgy1fzvprceemlj305006kmx5.jpg"},{"vod_id":19336,"vod_name":"2019山西卫视春晚","type_id":3,"type_name":"综艺","vod_remarks":"BD","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://ws2.sinaimg.cn/large/006VRvsSgy1fzvpqfk7xvj305006k0t4.jpg"},{"vod_id":19335,"vod_name":"2019山东卫视春晚","type_id":3,"type_name":"综艺","vod_remarks":"BD","vod_play_from":"jdym3u8","vod_time":"2019-02-05","vod_pic":"https://ws2.sinaimg.cn/large/006VRvsSgy1fzvppfdpbnj305006k3yn.jpg"},{"vod_id":18559,"vod_name":"兽娘动物园 第二季","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至4集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"https://wx4.sinaimg.cn/mw690/6c305f13gy1fz77kstlgxj207i0aln3p.jpg"},{"vod_id":18558,"vod_name":"灵剑尊 第1季","type_id":21,"type_name":"国产动漫","vod_remarks":"更新至10集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"https://wx2.sinaimg.cn/mw690/6c305f13gy1fz77i6r829j20le0u07wh.jpg"},{"vod_id":18483,"vod_name":"荒野的寿飞行队","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至4集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx3.sinaimg.cn/mw690/6c305f13gy1fz9gairk6qj207i0al44q.jpg"},{"vod_id":18367,"vod_name":"飞翔吧! 战机少女","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至4集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx4.sinaimg.cn/mw690/6c305f13gy1fz4vscja9gj207i0alwkg.jpg"},{"vod_id":18109,"vod_name":"欢迎光临千岁酱","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至17集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529796615.jpg"},{"vod_id":17999,"vod_name":"灵能百分百 第二季","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至5集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2527779770.jpg"},{"vod_id":17957,"vod_name":"不愉快的妖怪庵 续","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至5集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2536657666.jpg"},{"vod_id":17956,"vod_name":"多罗罗","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至5集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx2.sinaimg.cn/mw690/6c305f13gy1fz9g6g1trij207i0antf2.jpg"},{"vod_id":14481,"vod_name":"圣斗士星矢 圣斗少女翔","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至8集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2426614396.jpg"},{"vod_id":11486,"vod_name":"闪电十一人 猎户座的刻印","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至15集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2532719141.jpg"},{"vod_id":3708,"vod_name":"百炼成神","type_id":21,"type_name":"国产动漫","vod_remarks":"更新至63集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx2.sinaimg.cn/mw690/6c305f13gy1fvu586smi7j205006kwh2.jpg"},{"vod_id":3675,"vod_name":"关于我转生变成史莱姆这档事","type_id":22,"type_name":"日本动漫","vod_remarks":"更新至18集","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2534407188.jpg"},{"vod_id":3666,"vod_name":"妖神记 第3季 影妖篇","type_id":21,"type_name":"国产动漫","vod_remarks":"完结","vod_play_from":"m3u8","vod_time":"2019-02-05","vod_pic":"https://wx2.sinaimg.cn/mw690/6c305f13gy1fvlsol6kioj205006kaad.jpg"},{"vod_id":2039,"vod_name":"斗魂卫之玄月奇缘 ","type_id":21,"type_name":"国产动漫","vod_remarks":"更新至24集","vod_play_from":"m3u8,jdym3u8","vod_time":"2019-02-05","vod_pic":"https://wx4.sinaimg.cn/mw690/6c305f13gy1fsogi9v33cj20ab0epdq0.jpg"}]
     * class : [{"type_id":1,"type_name":"电影"},{"type_id":2,"type_name":"连续剧"},{"type_id":3,"type_name":"综艺"},{"type_id":4,"type_name":"动漫"},{"type_id":5,"type_name":"资讯"},{"type_id":6,"type_name":"动作片"},{"type_id":7,"type_name":"喜剧片"},{"type_id":8,"type_name":"爱情片"},{"type_id":9,"type_name":"科幻片"},{"type_id":10,"type_name":"恐怖片"},{"type_id":11,"type_name":"剧情片"},{"type_id":12,"type_name":"战争片"},{"type_id":13,"type_name":"国产剧"},{"type_id":14,"type_name":"港台剧"},{"type_id":15,"type_name":"日韩剧"},{"type_id":16,"type_name":"欧美剧"},{"type_id":17,"type_name":"公告"},{"type_id":18,"type_name":"头条"},{"type_id":20,"type_name":"单集动漫"},{"type_id":21,"type_name":"国产动漫"},{"type_id":22,"type_name":"日本动漫"},{"type_id":23,"type_name":"剧场版"},{"type_id":24,"type_name":"电影合集"}]
     */

    private int code;
    private String msg;
    private int page;
    private int pagecount;
    private String limit;
    private int total;
    private List<ListBean> list;
    @SerializedName("class")
    private List<ClassBean> classX;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public List<ClassBean> getClassX() {
        return classX;
    }

    public void setClassX(List<ClassBean> classX) {
        this.classX = classX;
    }

    public static class ListBean {
        /**
         * vod_id : 19340
         * vod_name : 2019年辽宁春晚
         * type_id : 3
         * type_name : 综艺
         * vod_remarks : BD
         * vod_play_from : jdym3u8
         * vod_time : 2019-02-05
         * vod_pic : https://ww2.sinaimg.cn/large/bfe05ea9ly1fzvqhaojikj207i0b9t94.jpg
         */

        private int vod_id;
        private String vod_name;
        private int type_id;
        private String type_name;
        private String vod_remarks;
        private String vod_play_from;
        private String vod_time;
        private String vod_pic;
        private String vod_actor;

        public String getVod_actor() {
            return vod_actor;
        }

        public void setVod_actor(String vod_actor) {
            this.vod_actor = vod_actor;
        }

        public int getVod_id() {
            return vod_id;
        }

        public void setVod_id(int vod_id) {
            this.vod_id = vod_id;
        }

        public String getVod_name() {
            return vod_name;
        }

        public void setVod_name(String vod_name) {
            this.vod_name = vod_name;
        }

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getVod_remarks() {
            return vod_remarks;
        }

        public void setVod_remarks(String vod_remarks) {
            this.vod_remarks = vod_remarks;
        }

        public String getVod_play_from() {
            return vod_play_from;
        }

        public void setVod_play_from(String vod_play_from) {
            this.vod_play_from = vod_play_from;
        }

        public String getVod_time() {
            return vod_time;
        }

        public void setVod_time(String vod_time) {
            this.vod_time = vod_time;
        }

        public String getVod_pic() {
            return vod_pic;
        }

        public void setVod_pic(String vod_pic) {
            this.vod_pic = vod_pic;
        }
    }

    public static class ClassBean {
        /**
         * type_id : 1
         * type_name : 电影
         */

        private int type_id;
        private String type_name;

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }
    }
}
