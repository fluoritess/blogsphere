package xin.dztyh.personal.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service
 * @Description:
 * @date 19-6-23 下午10:43
 */
public interface UtilService {

    boolean updateMaintainInfo(String content);

    String getMaintainInfo();

    String uploadImage(MultipartFile image);

    String addMdFile(String content);

}
