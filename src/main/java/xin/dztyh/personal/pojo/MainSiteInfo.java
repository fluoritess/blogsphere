package xin.dztyh.personal.pojo;

import java.io.Serializable;
import java.util.Date;

public class MainSiteInfo implements Serializable {
    private Integer id;

    private String name;

    private String signOne;

    private String signTwo;

    private String detail;

    private String backgroundUrl;

    private String resumeFile;

    private String personalPic;

    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSignOne() {
        return signOne;
    }

    public void setSignOne(String signOne) {
        this.signOne = signOne == null ? null : signOne.trim();
    }

    public String getSignTwo() {
        return signTwo;
    }

    public void setSignTwo(String signTwo) {
        this.signTwo = signTwo == null ? null : signTwo.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl == null ? null : backgroundUrl.trim();
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile == null ? null : resumeFile.trim();
    }

    public String getPersonalPic() {
        return personalPic;
    }

    public void setPersonalPic(String personalPic) {
        this.personalPic = personalPic == null ? null : personalPic.trim();
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", signOne=").append(signOne);
        sb.append(", signTwo=").append(signTwo);
        sb.append(", detail=").append(detail);
        sb.append(", backgroundUrl=").append(backgroundUrl);
        sb.append(", resumeFile=").append(resumeFile);
        sb.append(", personalPic=").append(personalPic);
        sb.append(", modifyDate=").append(modifyDate);
        sb.append("]");
        return sb.toString();
    }
}