package xin.dztyh.personal.pojo;

import java.io.Serializable;
import java.util.Date;

public class IpAddressPool implements Serializable {
    private Integer id;

    private String ip;

    private String address;

    private Integer visitedNum;

    private Date modifydate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getVisitedNum() {
        return visitedNum;
    }

    public void setVisitedNum(Integer visitedNum) {
        this.visitedNum = visitedNum;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", address=").append(address);
        sb.append(", visitedNum=").append(visitedNum);
        sb.append(", modifydate=").append(modifydate);
        sb.append("]");
        return sb.toString();
    }
}