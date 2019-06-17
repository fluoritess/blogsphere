package xin.dztyh.personal.pojo;

import java.io.Serializable;
import java.util.Date;

public class VisitedInfo implements Serializable {
    private Integer id;

    private String ip;

    private Integer port;

    private String address;

    private String visitedUrl;

    private Date date;

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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getVisitedUrl() {
        return visitedUrl;
    }

    public void setVisitedUrl(String visitedUrl) {
        this.visitedUrl = visitedUrl == null ? null : visitedUrl.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ip=").append(ip);
        sb.append(", port=").append(port);
        sb.append(", address=").append(address);
        sb.append(", visitedUrl=").append(visitedUrl);
        sb.append(", date=").append(date);
        sb.append("]");
        return sb.toString();
    }
}