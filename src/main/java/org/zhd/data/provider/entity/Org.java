package org.zhd.data.provider.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "basic_org")
public class Org implements Serializable {
    @TableId(value = "org_id")
    private Long orgId;
    // 数据共享
    private Integer basicShare;
    // 对应往来单位代码
    private String companyCode;
    // 会员代码
    private String memberCode;
    // 机构简称
    private String orgAbbreviate;
    // 帐号
    private String orgAccounts;
    // 地址
    private String orgAddr;
    // 开户银行
    private String orgBankname;
    // 代码
    private String orgCode;
    // 机构法人
    private String orgCorporation;
    // 机构传真号
    private String orgFax;
    // 叶子节点（0：机构组，1：机构明细）
    private Integer orgIsleaf;
    // 机构名称
    private String orgName;
    // 树节点代码
    private String orgNodecode;
    // 父代码
    private String orgParent;
    // 机构电话
    private String orgPhone;
    // 备注
    private String orgRemark;
    // 税号
    private String orgTanu;
}
