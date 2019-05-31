package org.zhd.data.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "basic_dept")
@KeySequence(value = "BASIC_DEPT_SEQ")
@ApiModel(value="部门对象")
public class Dpt implements Serializable {
    @TableId(value = "dept_id", type = IdType.INPUT)
    @ApiModelProperty(value="唯一标识,新增不传",example="1")
    private Long deptId;
    @ApiModelProperty(value="数据共享",example="1")
    private Integer basicShare;
    @ApiModelProperty(value="部门代码,新增不传")
    private String deptCode;
    @ApiModelProperty(value="叶子节点（0：部门组，1：部门明细）",example="1")
    private Integer deptIsleaf;
    @ApiModelProperty(value="负责人")
    private String deptManager;
    @ApiModelProperty(value="部门名称")
    private String deptName;
    @ApiModelProperty(value="树节点代码,新增不传")
    private String deptNodecode;
    @ApiModelProperty(value="父代码,新增不传")
    private String deptParent;
    @ApiModelProperty(value="备注")
    private String deptRemark;
    @ApiModelProperty(value="会员代码")
    private String memberCode;
    @ApiModelProperty(value="所属机构")
    private String orgCode;
}
