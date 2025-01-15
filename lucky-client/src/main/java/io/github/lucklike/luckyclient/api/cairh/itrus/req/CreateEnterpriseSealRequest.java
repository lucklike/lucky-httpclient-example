package io.github.lucklike.luckyclient.api.cairh.itrus.req;

import lombok.Data;

@Data
public class CreateEnterpriseSealRequest {

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 印章创建⼈⽤户id
     */
    private String userId;

    /**
     * 印章创建⽅式
     * 1=⾃定义 2=图⽚
     */
    private Integer sealType;

    /**
     * 印章名称
     * 1-32个字符
     */
    private String sealName;

    /**
     * 印章图⽚
     * ⽂件格式：jpg、png、jpeg
     */
    private String sealBase64;

    /**
     * 印章颜⾊
     * 0=透明化时保持原⾊
     * 1=红⾊(默认)
     * 2=绿⾊
     * 3=蓝⾊
     * 4=⿊⾊
     */
    private Integer color;

    /**
     * 印章形状
     * 1=圆章(默认)
     * 2=椭圆章
     * 3=三⻆章
     */
    private Integer shape;

    /**
     * 印章宽度
     * 单位：mm 范围：38-58 默认：40
     */
    private Integer width;

    /**
     * 印章透明度,值越⼩透明度越⼤
     * 范围：0-10 默认：5
     */
    private Integer alpha;

    /**
     * 下弦⽂
     * sealType=1时必填
     */
    private String lowerText;

    /**
     * ⽔平⽂字
     * sealType=1时必填
     */
    private String horizontalText;

    /**
     * 印章字体
     * 1=微软雅⿊(默认)
     * 2=⿊体
     * 3=宋体
     * 4=华⽂⾏楷
     * 5=⽅正舒体
     */
    private Integer fontType;

    /**
     * 创建印章类型
     * 1=普通印章(默认)
     * 2=OFD印章
     */
    private Integer createSealType;

    /**
     * 印章类型
     * 1=公章
     * 2=财务专⽤章
     * 3=合同专⽤章
     * 4=发票专⽤章
     * 0=其他
     */
    private Integer sealForm;

    /**
     * 印章状态
     * 2=启⽤(默认)
     * 3=停⽤
     */
    private Integer status;

    /**
     * 是否做透明化处理
     * 默认：true
     */
    private Boolean alphaDeal;

    /**
     * 操作类型
     * 0=创建（默认)
     * 1=预览
     */
    private Integer operationMode;

    /**
     * 是否返回base64
     * 默认：false
     */
    private Boolean returnBase64;
}
