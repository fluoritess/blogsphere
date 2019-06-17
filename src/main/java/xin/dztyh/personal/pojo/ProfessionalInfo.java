package xin.dztyh.personal.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProfessionalInfo implements Serializable {
    private Integer id;

    private String lang;

    private String percent;

    private String color;

    private Date modifyData;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent == null ? null : percent.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Date getModifyData() {
        return modifyData;
    }

    public void setModifyData(Date modifyData) {
        this.modifyData = modifyData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", lang=").append(lang);
        sb.append(", percent=").append(percent);
        sb.append(", color=").append(color);
        sb.append(", modifyData=").append(modifyData);
        sb.append("]");
        return sb.toString();
    }
}