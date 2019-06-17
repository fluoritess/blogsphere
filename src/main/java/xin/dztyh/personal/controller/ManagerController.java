package xin.dztyh.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xin.dztyh.personal.SpringAop.ArchivesLog;
import xin.dztyh.personal.pojo.*;
import xin.dztyh.personal.service.ManagerService;
import xin.dztyh.personal.util.R;

import java.util.*;

/**
 * @author tyh
 * @Package xin.dztyh.personal.controller
 * @Description: 管理信息controller
 * @date 19-5-15 下午10:31
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 获取主页信息
     * @return
     */
    @ArchivesLog(operationName = "获取[主页信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getMainInfo")
    public Map<String, Object> getMainInfo() {
        MainSiteInfo mainSiteInfo = managerService.getMainSiteInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", mainSiteInfo);
        return R.ok(map);
    }

    /**
     * 修改主页信息
     * @param mainSiteInfo
     * @return
     */
    @ArchivesLog(operationName = "修改[主页信息]", operationType = "更新信息")
    @ResponseBody
    @RequestMapping("/modifyMainInfo")
    public Map<String, Object> modifyMainInfo(@RequestParam(required = false,value = "file1")MultipartFile file1,
                                              @RequestParam(required = false,value = "file2")MultipartFile file2,
                                              @RequestParam(required = false,value = "file3")MultipartFile file3,
                                                MainSiteInfo mainSiteInfo) {
        Map<String,MultipartFile> fileMap=new HashMap<>();
        if (file1!=null){
            fileMap.put("backgroundUrl",file1);
        }
        if (file2!=null){
            fileMap.put("resumeFile",file2);
        }
        if (file3!=null){
            fileMap.put("personalPic",file3);
        }
        if (managerService.updateMainSiteInfo(mainSiteInfo,fileMap)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 获取专业知识信息
     * @return
     */
    @ArchivesLog(operationName = "获取[专业知识]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getProfessionalInfo")
    public Map<String, Object> getProfessionalInfo() {
        List<ProfessionalInfo> list = managerService.getProfessionalInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 添加专业知识信息
     * @param lang 语言
     * @param percent 百分比
     * @param color 颜色
     * @return
     */
    @ArchivesLog(operationName = "增加[专业知识信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/addProfessionalInfo")
    public Map<String, Object> addProfessionalInfo(@RequestParam("lang") String lang,
                                                   @RequestParam("percent") String percent,
                                                   @RequestParam("color") String color) {
        ProfessionalInfo professionalInfo = new ProfessionalInfo();
        professionalInfo.setLang(lang);
        professionalInfo.setColor(color);
        professionalInfo.setPercent(percent);
        professionalInfo.setModifyData(new Date());
        if (managerService.addProfessionalInfo(professionalInfo)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 修改专业知识信息
     * @param id id
     * @param lang 语言
     * @param percent 百分比
     * @param color 颜色
     * @return
     */
    @ArchivesLog(operationName = "更新[专业知识信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateProfessionalInfo")
    public Map<String, Object> updateProfessionalInfo(@RequestParam("id") String id,
                                                      @RequestParam("lang") String lang,
                                                      @RequestParam("percent") String percent,
                                                      @RequestParam("color") String color){
        ProfessionalInfo professionalInfo=new ProfessionalInfo();
        professionalInfo.setId(Integer.parseInt(id));
        professionalInfo.setLang(lang);
        professionalInfo.setColor(color);
        professionalInfo.setPercent(percent);
        professionalInfo.setModifyData(new Date());
        System.out.println(professionalInfo);
        if (managerService.updateProfessionalInfo(professionalInfo)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除信息
     * @param map
     * @return
     */
    @ArchivesLog(operationName = "删除[专业知识信息]", operationType = "删除信息")
    @ResponseBody
    @RequestMapping("/deleteProfessionalInfo")
    public Map<String,Object> deleteProfessionalInfo(@RequestBody Map<String,Object> map){
        List<String> idArray=(List<String>)map.get("data");
        if (managerService.deleteProfessionalInfo(idArray)) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 获取数据统计信息
     * @return
     */
    @ArchivesLog(operationName = "获取[数据统计信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getDataStatisticsInfo")
    public Map<String, Object> getDataStatisticsInfo() {
        List<DataStatistics> list = managerService.getDataStatisticInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 修改数据统计信息
     * @param id id
     * @param name 数据名
     * @param data 数据
     * @param icon 图标
     * @return
     */
    @ArchivesLog(operationName = "修改[数据统计信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateDataStatisticsInfo")
    public Map<String,Object> updateDataStatisticsInfo(@RequestParam("id") String id,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("data") String data,
                                                       @RequestParam("icon") String icon){
        DataStatistics dataStatistics=new DataStatistics();
        dataStatistics.setId(Integer.parseInt(id));
        dataStatistics.setName(name);
        dataStatistics.setIcon(icon);
        dataStatistics.setNum(Long.parseLong(data));
        dataStatistics.setModifyDate(new Date());
        if (managerService.updateDataStatisticsInfo(dataStatistics)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 获取专业能力信息
     * @return
     */
    @ArchivesLog(operationName = "获取[专业能力信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getCapacityInfo")
    public Map<String,Object> getCapacityInfo(){
        List<Capacity> list=managerService.getCapacityInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 增加专业能力信息
     * @param name
     * @param detail
     * @param icon
     * @return
     */
    @ArchivesLog(operationName = "增加[专业能力信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/addCapacityInfo")
    public Map<String,Object> addCapacityInfo(@RequestParam("name") String name,
                                              @RequestParam("detail") String detail,
                                              @RequestParam("icon") String icon){
        Capacity capacity=new Capacity();
        capacity.setName(name);
        capacity.setDetail(detail);
        capacity.setIcon(icon);
        capacity.setModifyDate(new Date());
        if (managerService.addCapacityInfo(capacity)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 更新专业能力信息
     * @return
     */
    @ArchivesLog(operationName = "更新[专业能力信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateCapacityInfo")
    public Map<String,Object> updateCapacityInfo(@RequestParam("id") String id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("detail") String detail,
                                                 @RequestParam("icon") String icon){
        Capacity capacity=new Capacity();
        capacity.setIcon(icon);
        capacity.setDetail(detail);
        capacity.setName(name);
        capacity.setId(Integer.parseInt(id));
        capacity.setModifyDate(new Date());
        if (managerService.updateCapacityInfo(capacity)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 删除专业能力信息
     * @param map
     * @return
     */
    @ArchivesLog(operationName = "删除[专业能力信息]", operationType = "删除信息")
    @ResponseBody
    @RequestMapping("/deleteCapacityInfo")
    public Map<String ,Object> deleteCapacityInfo(@RequestBody Map<String,Object> map){
        List<String> idArray=(List<String>)map.get("data");
        if (managerService.deleteCapacityInfo(idArray)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 获取项目信息
     * @return
     */
    @ArchivesLog(operationName = "获取[项目信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getProjectInfo")
    public Map<String,Object> getProjectInfo(){
        List<Project> list=managerService.getProjectInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 增加项目信息
     * @param file 背景图片
     * @param project 项目信息
     * @return
     */
    @ArchivesLog(operationName = "增加[项目信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/addProjectInfo")
    public Map<String ,Object> addProjectInfo(@RequestParam("file")MultipartFile file,Project project){
        System.out.println(project);
        project.setModifyDate(new Date());
        if (file.isEmpty()){
            return R.error("上传失败，请选择文件");
        }
        if (managerService.addProjectInfo(file,project)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 删除项目信息
     * @param map
     * @return
     */
    @ArchivesLog(operationName = "删除[项目信息]", operationType = "删除信息")
    @ResponseBody
    @RequestMapping("/deleteProjectInfo")
    public Map<String ,Object> deleteProjectInfo(@RequestBody Map<String,Object> map){
        List<String> idArray=(List<String>)map.get("data");
        if (managerService.deleteProjectInfo(idArray)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 修改项目信息
     * @param file
     * @param project
     * @return
     */
    @ArchivesLog(operationName = "修改[项目信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateProjectInfo")
    public Map<String ,Object> updateProjectInfo(@RequestParam(required = false,value = "file")MultipartFile file,
                                                 Project project){
        project.setModifyDate(new Date());
        if (managerService.updateProjectInfo(file,project)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 获取简历信息
     * @return
     */
    @ArchivesLog(operationName = "获取[简历信息]",operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getResumeInfo")
    public Map<String,Object> getResumeInfo(){
        List<Resume> list=managerService.getResumeInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 增加简历信息
     * @param resume
     * @return
     */
    @ArchivesLog(operationName = "增加[简历信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/addResumeInfo")
    public Map<String,Object> addResumeInfo(Resume resume){
        resume.setModifyDate(new Date());
        if(managerService.addResumeInfo(resume)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 修改简历信息
     * @param resume
     * @return
     */
    @ArchivesLog(operationName = "修改[简历信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateResumeInfo")
    public Map<String,Object> updateResumeInfo(Resume resume){
        resume.setModifyDate(new Date());
        if(managerService.updateResumeInfo(resume)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 删除简历信息
     * @param map
     * @return
     */
    @ArchivesLog(operationName = "删除[简历信息]", operationType = "删除信息")
    @ResponseBody
    @RequestMapping("/deleteResumeInfo")
    public Map<String,Object> deleteResumeInfo(@RequestBody Map<String,Object> map){
        List<String> idArray=(List<String>)map.get("data");
        if (managerService.deleteResumeInfo(idArray)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 增加项目信息
     * @param file 头像
     * @param quotes 名言信息
     * @return
     */
    @ArchivesLog(operationName = "增加[名言信息]", operationType = "添加信息")
    @ResponseBody
    @RequestMapping("/addQuotesInfo")
    public Map<String ,Object> addQuotesInfo(@RequestParam("file")MultipartFile file,Quotes quotes){
        quotes.setModifyDate(new Date());
        if (file.isEmpty()){
            return R.error("上传失败，请选择文件");
        }
        if (managerService.addQuotesInfo(file,quotes)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 获取项目信息
     * @return
     */
    @ArchivesLog(operationName = "获取[名言信息]", operationType = "获取信息")
    @ResponseBody
    @RequestMapping("/getQuotesInfo")
    public Map<String,Object> getQuotesInfo(){
        List<Quotes> list=managerService.getQuotesInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 修改项目信息
     * @param file
     * @param quotes
     * @return
     */
    @ArchivesLog(operationName = "修改[名言信息]", operationType = "修改信息")
    @ResponseBody
    @RequestMapping("/updateQuotesInfo")
    public Map<String ,Object> updateQuotesInfo(@RequestParam(required = false,value = "file")MultipartFile file,
                                                 Quotes quotes){
        quotes.setModifyDate(new Date());
        if (managerService.updateQuotesInfo(file,quotes)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    /**
     * 删除项目信息
     * @param map
     * @return
     */
    @ArchivesLog(operationName = "删除[名言信息]", operationType = "删除信息")
    @ResponseBody
    @RequestMapping("/deleteQuotesInfo")
    public Map<String ,Object> deleteQuotesInfo(@RequestBody Map<String,Object> map){
        List<String> idArray=(List<String>)map.get("data");
        if (managerService.deleteQuotesInfo(idArray)){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
