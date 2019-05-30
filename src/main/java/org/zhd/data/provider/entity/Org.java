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
@TableName(value = "basic_org")
@KeySequence(value = "BASIC_ORG_SEQ")
@ApiModel(value="机构对象")
public class Org implements Serializable {
    @TableId(value = "org_id", type = IdType.INPUT)
    @ApiModelProperty(value="唯一标识",example="1")
    private Long orgId;
    @ApiModelProperty(value="数据共享",example="1")
    private Integer basicShare;
    @ApiModelProperty(value="对应往来单位代码")
    private String companyCode;
    @ApiModelProperty(value="会员代码")
    private String memberCode;
    @ApiModelProperty(value="机构简称")
    private String orgAbbreviate;
    @ApiModelProperty(value="帐号")
    private String orgAccounts;
    @ApiModelProperty(value="地址")
    private String orgAddr;
    @ApiModelProperty(value="开户银行")
    private String orgBankname;
    @ApiModelProperty(value="机构代码")
    private String orgCode;
    @ApiModelProperty(value="机构法人")
    private String orgCorporation;
    @ApiModelProperty(value="机构传真号")
    private String orgFax;
    @ApiModelProperty(value="叶子节点（0：机构组，1：机构明细）",example="0")
    private Integer orgIsleaf;
    @ApiModelProperty(value="机构名称")
    private String orgName;
    @ApiModelProperty(value="树节点代码")
    private String orgNodecode;
    @ApiModelProperty(value="父代码")
    private String orgParent;
    @ApiModelProperty(value="机构电话")
    private String orgPhone;
    @ApiModelProperty(value="备注")
    private String orgRemark;
    @ApiModelProperty(value="税号")
    private String orgTanu;
}
