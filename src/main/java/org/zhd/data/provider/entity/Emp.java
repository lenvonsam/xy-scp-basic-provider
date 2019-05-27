package org.zhd.data.provider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "basic_employee")
@KeySequence(value = "BASIC_EMPLOYEE_SEQ")
public class Emp implements Serializable {
    @TableId(value = "employee_id", type = IdType.INPUT)
    private Long empId;
    // 数据共享
    private Integer basicShare;
    // 所属部门
    private String deptCode;
    // 住址
    private String employeeAddr;
    // 生日
    private Date employeeBirthday;
    // 员工类别
    private String employeeClass;
    // 员工代码
    private String employeeCode;
    // 学历
    private String employeeDegree;
    // 邮件
    private String employeeEmail;
    // 身份证号
    private String employeeIdcard;
    // 职位
    private String employeeJob;
    // 进公司日期
    private Date employeeJoindate;
    // 婚姻 0：未知1：未婚2：已婚3：离婚4：丧偶
    private String employeeMarriage;
    // 助记码
    private String employeeMnemcode;
    // 手机
    private String employeeMobile;
    // 员工名称
    private String employeeName;
    // 民族
    private String employeeNation;
    // 籍贯
    private String employeeNative;
    // 政治面貌
    private String employeeParty;
    // 电话
    private String employeePhone;
    // 备注
    private String employeeRemark;
    // 性别(男、女供选择)
    private String employeeSex;
    // 专业
    private String employeeSpecialty;
    // 状态0：启用1：停用
    private Integer employeeState;
    // 技术职称
    private String employeeTechnical;
    // 代码(禁止输入汉字)
    private String memberCode;
    // 所属机构
    private String orgCode;
    // 工种名称
    private String worktypeName;
}
