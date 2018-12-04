package cn.aorise.common.core.module.network;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/08/13
 *     desc   : TODO
 *     version: 1.0
 * </pre>
 */
public class ReqPage<T> extends Request {
    private T where;
    private int pageNum;
    private int pageSize = 8;
    private String orderBy;
    private String order;

    public ReqPage(T where, int pageNum) {
        this.where = where;
        this.pageNum = pageNum;
    }

    public T getWhere() {
        return where;
    }

    public void setWhere(T where) {
        this.where = where;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
