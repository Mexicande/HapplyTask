package cn.silence.tableaux.bean;

import java.io.Serializable;

/**
 * Created by apple on 2018/5/18.
 */

public class Banner implements Serializable{


    /**
     * url : http://obd7ty4wc.bkt.clouddn.com/默认130.png
     * app_url : http://a.app.qq.com/o/simple.jsp?pkgname=com.j1319947515.ikm
     * h5_link : http://a.app.qq.com/o/simple.jsp?pkgname=com.j1319947515.ikm
     * name : 速贷之家直接下载
     */

    private String url;
    private String app_url;
    private String h5_link;
    private String name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApp_url() {
        return app_url;
    }

    public void setApp_url(String app_url) {
        this.app_url = app_url;
    }

    public String getH5_link() {
        return h5_link;
    }

    public void setH5_link(String h5_link) {
        this.h5_link = h5_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
