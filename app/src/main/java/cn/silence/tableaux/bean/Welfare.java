package cn.silence.tableaux.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by apple on 2018/5/18.
 */

public class Welfare implements Serializable {


    /**
     * code : 200
     * message : OK
     * data : {"pageCount":1,"list":[{"id":1,"platform_id":0,"name":"test","logo":"https://res.cloudinary.com/dqxvumu7n/image/upload/v1592551533/04_cw9uow.png","banner_img":"","introduct":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈","rule_desc":"","status":1,"check_way":0,"to_use":"","user_group":"1","loan_period":"","accept_method":"","real_accept_money":"","pay_channel":"","pay_method":"","interest_alg":0,"interest_rate":"0.00","apply_condition":null,"apply_material":null,"overdue_alg":"0","overdue_cost":"0.00","position_sort":9999,"period_max":0,"period_min":0,"is_roll":0,"min_month_rate":0,"max_month_rate":0,"intro":"","fee":"0.00","news_link":"","avg_quota":0,"loan_min":0,"loan_max":0,"fast_time":"","avg_time":"","guide":null,"experience":0,"loan_speed":0,"composite_rate":0,"success_rate":0,"success_count":0,"syn_count":0,"click_count":0,"create_date":"1970-01-01 00:00:00","update_date":"1970-01-01 00:00:00","times":0,"type_nid":"","h5_url":"","official_website":"","channel_status":0,"min_day_rate":0,"max_day_rate":0,"star":5,"product_id":1},{"id":2,"platform_id":0,"name":"fagaga","logo":"http://or2eh71ll.bkt.clouddn.com/159256648773646.png","banner_img":"","introduct":"fadadgda","rule_desc":"","status":1,"check_way":0,"to_use":"","user_group":"1","loan_period":"","accept_method":"","real_accept_money":"","pay_channel":"","pay_method":"","interest_alg":0,"interest_rate":"0.00","apply_condition":null,"apply_material":null,"overdue_alg":"0","overdue_cost":"0.00","position_sort":9999,"period_max":0,"period_min":0,"is_roll":0,"min_month_rate":0,"max_month_rate":0,"intro":"","fee":"0.00","news_link":"","avg_quota":0,"loan_min":0,"loan_max":0,"fast_time":"","avg_time":"","guide":null,"experience":0,"loan_speed":0,"composite_rate":0,"success_rate":0,"success_count":999,"syn_count":0,"click_count":0,"create_date":"2020-06-19 19:34:47","update_date":"2020-06-19 19:34:47","times":0,"type_nid":"","h5_url":"fadgadg","official_website":"","channel_status":0,"min_day_rate":0,"max_day_rate":0,"star":4,"product_id":2}]}
     * error_code : 0
     * error_message :
     * time : 2020-06-22 16:22:26
     */

    private int code;
    private String message;
    private DataBean data;
    private int error_code;
    private String error_message;
    private String time;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public static class DataBean implements Serializable{
        /**
         * pageCount : 1
         * list : [{"id":1,"platform_id":0,"name":"test","logo":"https://res.cloudinary.com/dqxvumu7n/image/upload/v1592551533/04_cw9uow.png","banner_img":"","introduct":"哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈","rule_desc":"","status":1,"check_way":0,"to_use":"","user_group":"1","loan_period":"","accept_method":"","real_accept_money":"","pay_channel":"","pay_method":"","interest_alg":0,"interest_rate":"0.00","apply_condition":null,"apply_material":null,"overdue_alg":"0","overdue_cost":"0.00","position_sort":9999,"period_max":0,"period_min":0,"is_roll":0,"min_month_rate":0,"max_month_rate":0,"intro":"","fee":"0.00","news_link":"","avg_quota":0,"loan_min":0,"loan_max":0,"fast_time":"","avg_time":"","guide":null,"experience":0,"loan_speed":0,"composite_rate":0,"success_rate":0,"success_count":0,"syn_count":0,"click_count":0,"create_date":"1970-01-01 00:00:00","update_date":"1970-01-01 00:00:00","times":0,"type_nid":"","h5_url":"","official_website":"","channel_status":0,"min_day_rate":0,"max_day_rate":0,"star":5,"product_id":1},{"id":2,"platform_id":0,"name":"fagaga","logo":"http://or2eh71ll.bkt.clouddn.com/159256648773646.png","banner_img":"","introduct":"fadadgda","rule_desc":"","status":1,"check_way":0,"to_use":"","user_group":"1","loan_period":"","accept_method":"","real_accept_money":"","pay_channel":"","pay_method":"","interest_alg":0,"interest_rate":"0.00","apply_condition":null,"apply_material":null,"overdue_alg":"0","overdue_cost":"0.00","position_sort":9999,"period_max":0,"period_min":0,"is_roll":0,"min_month_rate":0,"max_month_rate":0,"intro":"","fee":"0.00","news_link":"","avg_quota":0,"loan_min":0,"loan_max":0,"fast_time":"","avg_time":"","guide":null,"experience":0,"loan_speed":0,"composite_rate":0,"success_rate":0,"success_count":999,"syn_count":0,"click_count":0,"create_date":"2020-06-19 19:34:47","update_date":"2020-06-19 19:34:47","times":0,"type_nid":"","h5_url":"fadgadg","official_website":"","channel_status":0,"min_day_rate":0,"max_day_rate":0,"star":4,"product_id":2}]
         */

        private int pageCount;
        private List<ListBean> list;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * id : 1
             * platform_id : 0
             * name : test
             * logo : https://res.cloudinary.com/dqxvumu7n/image/upload/v1592551533/04_cw9uow.png
             * banner_img :
             * introduct : 哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈
             * rule_desc :
             * status : 1
             * check_way : 0
             * to_use :
             * user_group : 1
             * loan_period :
             * accept_method :
             * real_accept_money :
             * pay_channel :
             * pay_method :
             * interest_alg : 0
             * interest_rate : 0.00
             * apply_condition : null
             * apply_material : null
             * overdue_alg : 0
             * overdue_cost : 0.00
             * position_sort : 9999
             * period_max : 0
             * period_min : 0
             * is_roll : 0
             * min_month_rate : 0
             * max_month_rate : 0
             * intro :
             * fee : 0.00
             * news_link :
             * avg_quota : 0
             * loan_min : 0
             * loan_max : 0
             * fast_time :
             * avg_time :
             * guide : null
             * experience : 0
             * loan_speed : 0
             * composite_rate : 0
             * success_rate : 0
             * success_count : 0
             * syn_count : 0
             * click_count : 0
             * create_date : 1970-01-01 00:00:00
             * update_date : 1970-01-01 00:00:00
             * times : 0
             * type_nid :
             * h5_url :
             * official_website :
             * channel_status : 0
             * min_day_rate : 0
             * max_day_rate : 0
             * star : 5
             * product_id : 1
             */

            private String id;
            private int platform_id;
            private String name;
            private String logo;
            private String banner_img;
            private String introduct;
            private String rule_desc;
            private int status;
            private int check_way;
            private String to_use;
            private String user_group;
            private String loan_period;
            private String accept_method;
            private String real_accept_money;
            private String pay_channel;
            private String pay_method;
            private int interest_alg;
            private String interest_rate;
            private Object apply_condition;
            private Object apply_material;
            private String overdue_alg;
            private String overdue_cost;
            private int position_sort;
            private int period_max;
            private int period_min;
            private int is_roll;
            private int min_month_rate;
            private int max_month_rate;
            private String intro;
            private String fee;
            private String news_link;
            private int avg_quota;
            private int loan_min;
            private int loan_max;
            private String fast_time;
            private String avg_time;
            private Object guide;
            private int experience;
            private int loan_speed;
            private int composite_rate;
            private int success_rate;
            private int success_count;
            private int syn_count;
            private int click_count;
            private String create_date;
            private String update_date;
            private int times;
            private String type_nid;
            private String h5_url;
            private String official_website;
            private int channel_status;
            private int min_day_rate;
            private int max_day_rate;
            private int star;
            private int product_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getPlatform_id() {
                return platform_id;
            }

            public void setPlatform_id(int platform_id) {
                this.platform_id = platform_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getBanner_img() {
                return banner_img;
            }

            public void setBanner_img(String banner_img) {
                this.banner_img = banner_img;
            }

            public String getIntroduct() {
                return introduct;
            }

            public void setIntroduct(String introduct) {
                this.introduct = introduct;
            }

            public String getRule_desc() {
                return rule_desc;
            }

            public void setRule_desc(String rule_desc) {
                this.rule_desc = rule_desc;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getCheck_way() {
                return check_way;
            }

            public void setCheck_way(int check_way) {
                this.check_way = check_way;
            }

            public String getTo_use() {
                return to_use;
            }

            public void setTo_use(String to_use) {
                this.to_use = to_use;
            }

            public String getUser_group() {
                return user_group;
            }

            public void setUser_group(String user_group) {
                this.user_group = user_group;
            }

            public String getLoan_period() {
                return loan_period;
            }

            public void setLoan_period(String loan_period) {
                this.loan_period = loan_period;
            }

            public String getAccept_method() {
                return accept_method;
            }

            public void setAccept_method(String accept_method) {
                this.accept_method = accept_method;
            }

            public String getReal_accept_money() {
                return real_accept_money;
            }

            public void setReal_accept_money(String real_accept_money) {
                this.real_accept_money = real_accept_money;
            }

            public String getPay_channel() {
                return pay_channel;
            }

            public void setPay_channel(String pay_channel) {
                this.pay_channel = pay_channel;
            }

            public String getPay_method() {
                return pay_method;
            }

            public void setPay_method(String pay_method) {
                this.pay_method = pay_method;
            }

            public int getInterest_alg() {
                return interest_alg;
            }

            public void setInterest_alg(int interest_alg) {
                this.interest_alg = interest_alg;
            }

            public String getInterest_rate() {
                return interest_rate;
            }

            public void setInterest_rate(String interest_rate) {
                this.interest_rate = interest_rate;
            }

            public Object getApply_condition() {
                return apply_condition;
            }

            public void setApply_condition(Object apply_condition) {
                this.apply_condition = apply_condition;
            }

            public Object getApply_material() {
                return apply_material;
            }

            public void setApply_material(Object apply_material) {
                this.apply_material = apply_material;
            }

            public String getOverdue_alg() {
                return overdue_alg;
            }

            public void setOverdue_alg(String overdue_alg) {
                this.overdue_alg = overdue_alg;
            }

            public String getOverdue_cost() {
                return overdue_cost;
            }

            public void setOverdue_cost(String overdue_cost) {
                this.overdue_cost = overdue_cost;
            }

            public int getPosition_sort() {
                return position_sort;
            }

            public void setPosition_sort(int position_sort) {
                this.position_sort = position_sort;
            }

            public int getPeriod_max() {
                return period_max;
            }

            public void setPeriod_max(int period_max) {
                this.period_max = period_max;
            }

            public int getPeriod_min() {
                return period_min;
            }

            public void setPeriod_min(int period_min) {
                this.period_min = period_min;
            }

            public int getIs_roll() {
                return is_roll;
            }

            public void setIs_roll(int is_roll) {
                this.is_roll = is_roll;
            }

            public int getMin_month_rate() {
                return min_month_rate;
            }

            public void setMin_month_rate(int min_month_rate) {
                this.min_month_rate = min_month_rate;
            }

            public int getMax_month_rate() {
                return max_month_rate;
            }

            public void setMax_month_rate(int max_month_rate) {
                this.max_month_rate = max_month_rate;
            }

            public String getIntro() {
                return intro;
            }

            public void setIntro(String intro) {
                this.intro = intro;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getNews_link() {
                return news_link;
            }

            public void setNews_link(String news_link) {
                this.news_link = news_link;
            }

            public int getAvg_quota() {
                return avg_quota;
            }

            public void setAvg_quota(int avg_quota) {
                this.avg_quota = avg_quota;
            }

            public int getLoan_min() {
                return loan_min;
            }

            public void setLoan_min(int loan_min) {
                this.loan_min = loan_min;
            }

            public int getLoan_max() {
                return loan_max;
            }

            public void setLoan_max(int loan_max) {
                this.loan_max = loan_max;
            }

            public String getFast_time() {
                return fast_time;
            }

            public void setFast_time(String fast_time) {
                this.fast_time = fast_time;
            }

            public String getAvg_time() {
                return avg_time;
            }

            public void setAvg_time(String avg_time) {
                this.avg_time = avg_time;
            }

            public Object getGuide() {
                return guide;
            }

            public void setGuide(Object guide) {
                this.guide = guide;
            }

            public int getExperience() {
                return experience;
            }

            public void setExperience(int experience) {
                this.experience = experience;
            }

            public int getLoan_speed() {
                return loan_speed;
            }

            public void setLoan_speed(int loan_speed) {
                this.loan_speed = loan_speed;
            }

            public int getComposite_rate() {
                return composite_rate;
            }

            public void setComposite_rate(int composite_rate) {
                this.composite_rate = composite_rate;
            }

            public int getSuccess_rate() {
                return success_rate;
            }

            public void setSuccess_rate(int success_rate) {
                this.success_rate = success_rate;
            }

            public int getSuccess_count() {
                return success_count;
            }

            public void setSuccess_count(int success_count) {
                this.success_count = success_count;
            }

            public int getSyn_count() {
                return syn_count;
            }

            public void setSyn_count(int syn_count) {
                this.syn_count = syn_count;
            }

            public int getClick_count() {
                return click_count;
            }

            public void setClick_count(int click_count) {
                this.click_count = click_count;
            }

            public String getCreate_date() {
                return create_date;
            }

            public void setCreate_date(String create_date) {
                this.create_date = create_date;
            }

            public String getUpdate_date() {
                return update_date;
            }

            public void setUpdate_date(String update_date) {
                this.update_date = update_date;
            }

            public int getTimes() {
                return times;
            }

            public void setTimes(int times) {
                this.times = times;
            }

            public String getType_nid() {
                return type_nid;
            }

            public void setType_nid(String type_nid) {
                this.type_nid = type_nid;
            }

            public String getH5_url() {
                return h5_url;
            }

            public void setH5_url(String h5_url) {
                this.h5_url = h5_url;
            }

            public String getOfficial_website() {
                return official_website;
            }

            public void setOfficial_website(String official_website) {
                this.official_website = official_website;
            }

            public int getChannel_status() {
                return channel_status;
            }

            public void setChannel_status(int channel_status) {
                this.channel_status = channel_status;
            }

            public int getMin_day_rate() {
                return min_day_rate;
            }

            public void setMin_day_rate(int min_day_rate) {
                this.min_day_rate = min_day_rate;
            }

            public int getMax_day_rate() {
                return max_day_rate;
            }

            public void setMax_day_rate(int max_day_rate) {
                this.max_day_rate = max_day_rate;
            }

            public int getStar() {
                return star;
            }

            public void setStar(int star) {
                this.star = star;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }
        }
    }
}
