package cn.silence.tableaux.bean;

import java.io.Serializable;

/**
 *
 * @author apple
 * @date 2018/5/18
 *
 */

public class Product implements Serializable {


    /**
     * id : 1
     * product_id : 1
     * sort : 0
     * product_name : 米米贷
     * product_logo : http://or2eh71ll.bkt.clouddn.com/150131715167829.png?e=1501320751&token=Npg7Sanmf4z8uv3mvwwffjOvoCMYN8Ezm4T8pDrC:DcwqOuHKq8MTwaxqV_TEmb1jbTg=
     * h5_link : http://www.mimidai.com/expand/xjdslj2
     * product_desc : 极速贷款800、秒批通过后当日到账
     */

    private String id;
    private String product_id;
    private int sort;
    private String product_name;
    private String product_logo;
    private String h5_link;
    private String product_desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_logo() {
        return product_logo;
    }

    public void setProduct_logo(String product_logo) {
        this.product_logo = product_logo;
    }

    public String getH5_link() {
        return h5_link;
    }

    public void setH5_link(String h5_link) {
        this.h5_link = h5_link;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }
}
