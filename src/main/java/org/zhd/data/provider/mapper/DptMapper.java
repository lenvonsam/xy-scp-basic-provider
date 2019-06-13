package org.zhd.data.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zhd.data.provider.entity.DptBean;

import java.util.List;

/**
 * @author cth
 * @date 2019/06/03
 */
public interface DptMapper extends BaseMapper<DptBean> {
    /**
     * 主键自增长 xml写法
     * @param dptBean 部门
     * @return int
     */
    @Deprecated
    int insertDpt(DptBean dptBean);

    /**
     * 动态sql写法
     * @param dptBean 部门
     * @return int
     */
    @Deprecated
    List<DptBean> findDptList(DptBean dptBean);

    @Select("select count(1) from basic_employee a,basic_dept b where a.dept_code = b.dept_code and b.dept_id = #{dptId}")
    int countEmpByDptId(@Param("dptId")Long dptId);
}
