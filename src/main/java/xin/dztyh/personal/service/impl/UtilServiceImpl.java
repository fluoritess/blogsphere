package xin.dztyh.personal.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xin.dztyh.personal.service.UtilService;
import xin.dztyh.personal.util.FileIOUtils;
import xin.dztyh.personal.util.UploadUtils;

import java.util.UUID;

/**
 * @author tyh
 * @Package xin.dztyh.personal.service.impl
 * @Description:
 * @date 19-6-23 下午10:43
 */
@Service
public class UtilServiceImpl implements UtilService {

    @Override
    public boolean updateMaintainInfo(String content) {
        String name = FileIOUtils.writeFile("maintainInfo.md", content,false);
        return name != null;
    }

    @Override
    public String getMaintainInfo() {
        String value =  FileIOUtils.readFile("maintainInfo.md",false);
        return value==null?"":value;
    }

    @Override
    public String uploadImage(MultipartFile image) {
        if (UploadUtils.getExtendsName(image).equals("IMG")){
            return UploadUtils.uploadFile(image);
        }else {
            return null;
        }
    }

    @Override
    public String addMdFile(String content) {
        UUID id = UUID.randomUUID();
        String[] idd = id.toString().split("-");
        String fileName = idd[0] + idd[4]+".md";
        return FileIOUtils.writeFile(fileName,content,true);
    }
}
