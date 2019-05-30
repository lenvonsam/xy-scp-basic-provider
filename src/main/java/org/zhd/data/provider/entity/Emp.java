package org.zhd.data.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "basic_employee")
@KeySequence(value = "BASIC_EMPLOYEE_SEQ")
@ApiModel(value="业务员对象")
public class Emp implements Serializable {
    @TableId(value = "employee_id", type = IdType.INPUT)
    @ApiModelProperty(value="唯一标识",example="1")
    private Long empId;
    @ApiModelProperty(value="数据共享",example="1")
    private Integer basicShare;
    @ApiModelProperty(value="所属部门")
    private String deptCode;
    @ApiModelProperty(value="住址")
    private String employeeAddr;
    @ApiModelProperty(value="生日",example="2019-05-30 08:00:00")
    private Date employeeBirthday;
    @ApiModelProperty(value="员工类别")
    private String employeeClass;
    @ApiModelProperty(value="员工代码")
    private String employeeCode;
    @ApiModelProperty(value="学历")
    private String employeeDegree;
    @ApiModelProperty(value="邮件")
    private String employeeEmail;
    @ApiModelProperty(value="身份证号")
    private String employeeIdcard;
    @ApiModelProperty(value="职位")
    private String employeeJob;
    @ApiModelProperty(value="进公司日期",example="2019-05-30 08:00:00")
    private Date employeeJoindate;
    @ApiModelProperty(value="婚姻 0：未知1：未婚2：已婚3：离婚4：丧偶")
    private String employeeMarriage;
    @ApiModelProperty(value="助记码")
    private String employeeMnemcode;
    @ApiModelProperty(value="手机")
    private String employeeMobile;
    @ApiModelProperty(value="员工名称")
    private String employeeName;
    @ApiModelProperty(value="民族")
    private String employeeNation;
    @ApiModelProperty(value="籍贯")
    private String employeeNative;
    @ApiModelProperty(value="政治面貌")
    private String employeeParty;
    @ApiModelProperty(value="电话")
    private String employeePhone;
    @ApiModelProperty(value="备注")
    private String employeeRemark;
    @ApiModelProperty(value="性别(男、女供选择)")
    private String employeeSex;
    @ApiModelProperty(value="专业")
    private String employeeSpecialty;
    @ApiModelProperty(value="状态0：启用1：停用",example="0")
    private Integer employeeState;
    @ApiModelProperty(value="技术职称")
    private String employeeTechnical;
    @ApiModelProperty(value="代码(禁止输入汉字)")
    private String memberCode;
    @ApiModelProperty(value="所属机构")
    private String orgCode;
    @ApiModelProperty(value="工种名称")
    private String worktypeName;
}
