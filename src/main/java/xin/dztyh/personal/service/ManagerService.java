package xin.dztyh.personal.service;

import org.springframework.web.multipart.MultipartFile;
import xin.dztyh.personal.pojo.*;

import java.util.List;
import java.util.Map;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service
 * @Description:
 * @date 19-5-15 下午11:13
 */
public interface ManagerService {

    MainSiteInfo getMainSiteInfo();

    boolean updateMainSiteInfo(MainSiteInfo mainSiteInfo,Map<String,MultipartFile> files);

    List<ProfessionalInfo> getProfessionalInfo();

    boolean addProfessionalInfo(ProfessionalInfo professionalInfo);

    boolean updateProfessionalInfo(ProfessionalInfo professionalInfo);

    boolean deleteProfessionalInfo(List<String> list);

    List<DataStatistics> getDataStatisticInfo();

    boolean updateDataStatisticsInfo(DataStatistics dataStatistics);

    List<Capacity> getCapacityInfo();

    boolean addCapacityInfo(Capacity capacity);

    boolean updateCapacityInfo(Capacity capacity);

    boolean deleteCapacityInfo(List<String> idArray);

    List<Project> getProjectInfo();

    boolean addProjectInfo(MultipartFile file,Project project);

    boolean deleteProjectInfo(List<String> idArray);

    boolean updateProjectInfo(MultipartFile file,Project project);

    List<Resume> getResumeInfo();

    boolean addResumeInfo(Resume resume);

    boolean updateResumeInfo(Resume resume);

    boolean deleteResumeInfo(List<String> idArray);

    boolean addQuotesInfo(MultipartFile file, Quotes quotes);

    List<Quotes> getQuotesInfo();

    boolean updateQuotesInfo(MultipartFile file,Quotes quotes);

    boolean deleteQuotesInfo(List<String> idArray);

}
