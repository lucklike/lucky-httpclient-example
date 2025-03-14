package io.github.lucklike.luckyclient.api.cairh.comp.req;

import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * 基金概要文件信息
 * <p>
 * 详细为：基金产品资料概要文件命名规则为：2位国别码（大写）_8位基金管理人编码（证监会FIRST系统向备案的公募基金管理人分配的8位代码）_6位基金主代码
 * _8位公告类型代码（产品资料概要公告代码为FA010070）_8位公告编号（年度+流水号）_6位基金子代码_8位信息展示日期（格式YYYYMMDD，该日期为销售机构实际向投资者展示产品资料概要的日期）_6位信息展示时点（格式HHMMSS，该时点为销售机构实际向投资者展示产品资料概要的时间点）_2位批次号（同一版本XML与PDF批次号应相同，批次号应按照正序排号，且不得间隔，同一展示日期的文件，发送多次应使用不同批次号，与展示时点无关）.zip/pdf（不区分大小写）
 * CN_50300000_001468_FA010070_20200002_001468_20200629_120000_01.pdf
 */
public class FundSumaryFileInfo {
    private File file;
    private String fileName;
    private String nation;
    private String fundManagerCode;
    private String mainFundCode;
    private String noticeType;
    private String noticeNo;
    private String subFundCode;
    private String noticeDate;//YYYYMMDD
    private String noticeTime;//HHMMSS
    private String seqNo;
    private String orderNo;
    private String dbPropertyValue;

    public FundSumaryFileInfo(String fileName, File file) {
        this.fileName = fileName;
        this.dbPropertyValue = "{\"file_name\":\"" + fileName + "\"}";
        this.file = file;
        String[] fileInfoArray = fileName.split("_");
        this.nation = fileInfoArray[0];
        this.fundManagerCode = fileInfoArray[1];
        this.mainFundCode = fileInfoArray[2];
        this.noticeType = fileInfoArray[3];
        this.noticeNo = fileInfoArray[4];
        this.subFundCode = fileInfoArray[5];
        this.noticeDate = fileInfoArray[6];
        this.noticeTime = fileInfoArray[7];
        this.seqNo = fileInfoArray[8];
        this.orderNo = this.subFundCode + "_" + this.noticeDate + "_" + this.noticeTime + "_" + this.seqNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getFundManagerCode() {
        return fundManagerCode;
    }

    public void setFundManagerCode(String fundManagerCode) {
        this.fundManagerCode = fundManagerCode;
    }

    public String getMainFundCode() {
        return mainFundCode;
    }

    public void setMainFundCode(String mainFundCode) {
        this.mainFundCode = mainFundCode;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getSubFundCode() {
        return subFundCode;
    }

    public void setSubFundCode(String subFundCode) {
        this.subFundCode = subFundCode;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }

    public String getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public byte[] getFileBytes() throws IOException {
        return FileCopyUtils.copyToByteArray(file);
    }

    public String getDbPropertyValue() {
        return dbPropertyValue;
    }

    public void setDbPropertyValue(String dbPropertyValue) {
        this.dbPropertyValue = dbPropertyValue;
    }
}
