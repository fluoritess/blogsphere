package xin.dztyh.personal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.nio.cs.FastCharsetProvider;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.dao.*;
import xin.dztyh.personal.pojo.*;
import xin.dztyh.personal.service.ManagerService;
import xin.dztyh.personal.util.LogInfo;
import xin.dztyh.personal.util.UploadUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service.impl
 * @Description:
 * @date 19-5-15 下午11:13
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private MainSiteInfoMapper mainSiteInfoMapper;

    @Autowired
    private ProfessionalInfoMapper professionalInfoMapper;

    @Autowired
    private DataStatisticsMapper dataStatisticsMapper;

    @Autowired
    private CapacityMapper capacityMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ResumeMapper resumeMapper;

    @Autowired
    private QuotesMapper quotesMapper;

    @Override
    public MainSiteInfo getMainSiteInfo() {
        return mainSiteInfoMapper.selectByPrimaryKey(1);
    }

    @Override
    public boolean updateMainSiteInfo(MainSiteInfo mainSiteInfo, Map<String, MultipartFile> files) {
        mainSiteInfo.setId(1);
        mainSiteInfo.setModifyDate(new Date());
        //如果文件不为空
        if (!files.isEmpty()) {
            MainSiteInfo m = mainSiteInfoMapper.selectByPrimaryKey(1);
            for (Map.Entry<String, MultipartFile> entry : files.entrySet()) {
                //判断文件类型并存储
                if (UploadUtils.getExtendsName(entry.getValue()).equals("IMG") && entry.getKey().equals("backgroundUrl")) {
                    mainSiteInfo.setBackgroundUrl(UploadUtils.uploadFile(entry.getValue()));
                } else if (UploadUtils.getExtendsName(entry.getValue()).equals("PDF") && entry.getKey().equals("resumeFile")) {
                    mainSiteInfo.setResumeFile(UploadUtils.uploadFile(entry.getValue()));
                } else if (UploadUtils.getExtendsName(entry.getValue()).equals("IMG") && entry.getKey().equals("personalPic")) {
                    mainSiteInfo.setPersonalPic(UploadUtils.uploadFile(entry.getValue()));
                } else {
                    return false;
                }
            }
            //存入数据
            if (mainSiteInfoMapper.updateByPrimaryKeySelective(mainSiteInfo) != 0) {
                //存入数据后删除旧数据
                if (mainSiteInfo.getBackgroundUrl() != null) {
                    UploadUtils.deleteFile(m.getBackgroundUrl());
                }
                if (mainSiteInfo.getResumeFile() != null) {
                    UploadUtils.deleteFile(m.getResumeFile());
                }
                return true;
            } else {
                LogInfo.logger.error(LogInfo.getTime() + "插入数据失败，回滚上传的图片");
                //存入数据失败后删除新插入的数据
                if (mainSiteInfo.getBackgroundUrl() != null) {
                    UploadUtils.deleteFile(mainSiteInfo.getBackgroundUrl());
                }
                if (mainSiteInfo.getResumeFile() != null) {
                    UploadUtils.deleteFile(mainSiteInfo.getResumeFile());
                }
                if (mainSiteInfo.getPersonalPic() != null) {
                    UploadUtils.deleteFile(mainSiteInfo.getPersonalPic());
                }
                return false;
            }

        } else {
            //未上传文件直接插入
            return mainSiteInfoMapper.updateByPrimaryKeySelective(mainSiteInfo) != 0;
        }
    }

    @Override
    public List<ProfessionalInfo> getProfessionalInfo() {
        ProfessionalInfoExample example = new ProfessionalInfoExample();
        return professionalInfoMapper.selectByExample(example);
    }

    @Override
    public boolean addProfessionalInfo(ProfessionalInfo professionalInfo) {
        return professionalInfoMapper.insertSelective(professionalInfo) != 0;
    }

    @Override
    public boolean updateProfessionalInfo(ProfessionalInfo professionalInfo) {
        return professionalInfoMapper.updateByPrimaryKeySelective(professionalInfo) != 0;
    }

    @Override
    public boolean deleteProfessionalInfo(List<String> list) {
        int n = 0;
        for (String i : list) {
            n += professionalInfoMapper.deleteByPrimaryKey(Integer.parseInt(i));
        }
        return n == list.size();
    }

    @Override
    public List<DataStatistics> getDataStatisticInfo() {
        DataStatisticsExample example = new DataStatisticsExample();
        return dataStatisticsMapper.selectByExample(example);
    }

    @Override
    public boolean updateDataStatisticsInfo(DataStatistics dataStatistics) {
        return dataStatisticsMapper.updateByPrimaryKeySelective(dataStatistics) != 0;
    }

    @Override
    public List<Capacity> getCapacityInfo() {
        CapacityExample example = new CapacityExample();
        return capacityMapper.selectByExample(example);
    }

    @Override
    public boolean addCapacityInfo(Capacity capacity) {
        return capacityMapper.insertSelective(capacity) != 0;
    }

    @Override
    public boolean updateCapacityInfo(Capacity capacity) {
        return capacityMapper.updateByPrimaryKeySelective(capacity) != 0;
    }

    @Override
    public boolean deleteCapacityInfo(List<String> idArray) {
        int n = 0;
        for (String id : idArray) {
            capacityMapper.deleteByPrimaryKey(Integer.parseInt(id));
            n++;
        }
        return n == idArray.size();
    }

    @Override
    public List<Project> getProjectInfo() {
        ProjectExample example = new ProjectExample();
        return projectMapper.selectByExample(example);
    }

    @Override
    public boolean addProjectInfo(MultipartFile file, Project project) {
        project.setPictureUrl(UploadUtils.uploadFile(file));
        if (project.getPictureUrl() != null) {
            if (projectMapper.insertSelective(project) != 0) {
                return true;
            } else {
                UploadUtils.deleteFile(project.getPictureUrl());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteProjectInfo(List<String> idArray) {
        int n = 0;
        for (String id : idArray) {
            Project project = projectMapper.selectByPrimaryKey(Integer.parseInt(id));
            if (projectMapper.deleteByPrimaryKey(Integer.parseInt(id)) != 0) {
                UploadUtils.deleteFile(project.getPictureUrl());
                n++;
            }
        }
        return n == idArray.size();
    }

    @Override
    public boolean updateProjectInfo(MultipartFile file, Project project) {
        //如果文件不为空
        if (file != null) {
            //存入图片
            project.setPictureUrl(UploadUtils.uploadFile(file));
            //存入图片成功
            if (project.getPictureUrl() != null) {
                //查出以前存放的图片地址
                Project p = projectMapper.selectByPrimaryKey(project.getId());
                String imgUrl = p.getPictureUrl();
                //修改数据库
                if (projectMapper.updateByPrimaryKeySelective(project) != 0) {
                    //数据库修改成功后删除以前存放的图片
                    if (UploadUtils.deleteFile(imgUrl)) {
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    //数据库修改失败后删除当前存放的图片
                    if (UploadUtils.deleteFile(project.getPictureUrl())) {
                        return false;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return projectMapper.updateByPrimaryKeySelective(project) != 0;
        }
    }

    @Override
    public List<Resume> getResumeInfo() {
        ResumeExample resumeExample = new ResumeExample();
        return resumeMapper.selectByExample(resumeExample);
    }

    @Override
    public boolean addResumeInfo(Resume resume) {
        return resumeMapper.insertSelective(resume) != 0;
    }

    @Override
    public boolean updateResumeInfo(Resume resume) {
        return resumeMapper.updateByPrimaryKeySelective(resume) != 0;
    }

    @Override
    public boolean deleteResumeInfo(List<String> idArray) {
        int n = 0;
        for (String id : idArray) {
            resumeMapper.deleteByPrimaryKey(Integer.parseInt(id));
            n++;
        }
        return n == idArray.size();
    }

    @Override
    public boolean addQuotesInfo(MultipartFile file, Quotes quotes) {
        quotes.setImgUrl(UploadUtils.uploadFile(file));
        if (quotes.getImgUrl() != null) {
            if (quotesMapper.insertSelective(quotes) != 0) {
                return true;
            } else {
                UploadUtils.deleteFile(quotes.getImgUrl());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Quotes> getQuotesInfo() {
        QuotesExample example = new QuotesExample();
        return quotesMapper.selectByExample(example);
    }

    @Override
    public boolean updateQuotesInfo(MultipartFile file, Quotes quotes) {
        //如果文件不为空
        if (file != null) {
            //存入图片
            quotes.setImgUrl(UploadUtils.uploadFile(file));
            //存入图片成功
            if (quotes.getImgUrl() != null) {
                //查出以前存放的图片地址
                Quotes p = quotesMapper.selectByPrimaryKey(quotes.getId());
                String imgUrl = p.getImgUrl();
                //修改数据库
                if (quotesMapper.updateByPrimaryKeySelective(quotes) != 0) {
                    //数据库修改成功后删除以前存放的图片
                    if (UploadUtils.deleteFile(imgUrl)) {
                        return true;
                    } else {
                        return true;
                    }
                } else {
                    //数据库修改失败后删除当前存放的图片
                    if (UploadUtils.deleteFile(quotes.getImgUrl())) {
                        return false;
                    } else {
                        return false;
                    }
                }
            } else {
                return false;
            }
        } else {
            return quotesMapper.updateByPrimaryKeySelective(quotes) != 0;
        }
    }

    @Override
    public boolean deleteQuotesInfo(List<String> idArray) {
        int n = 0;
        for (String id : idArray) {
            quotesMapper.deleteByPrimaryKey(Integer.parseInt(id));
            n++;
        }
        return n == idArray.size();
    }
}
