package xin.dztyh.personal.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author tyh
 * @Package xin.dztyh.personal.dao
 * @Description:
 * @date 19-6-10 下午6:25
 */
public interface UtilMapper {

    int selectCount(@Param(value = "name") String name,
                    @Param(value = "limitName") String limitName,
                    @Param(value = "limitValue") String limitValue);

    List<Map<String,Object>> selectPaging(@Param(value = "name") String name,
                                          @Param(value = "offset") int offset,
                                          @Param(value = "pageSize") int pageSize,
                                          @Param(value = "limitName") String limitName,
                                          @Param(value = "limitValue") String limitValue);

}
