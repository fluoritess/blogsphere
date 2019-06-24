package xin.dztyh.personal.service.impl;

import org.springframework.stereotype.Service;
import xin.dztyh.personal.service.UtilService;
import xin.dztyh.personal.util.FileIOUtils;

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
        String name = FileIOUtils.writeFile("maintainInfo.md", content);
        return name != null;
    }

    @Override
    public String getMaintainInfo() {
        String value =  FileIOUtils.readFile("maintainInfo.md");
        return value==null?"":value;
    }
}
