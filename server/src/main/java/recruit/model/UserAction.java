package recruit.model;

import java.util.Date;

public class UserAction {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Integer os;

    private Integer sdkSt;

    private Integer ei;

    private Integer vl;

    private Integer ts;

    private String traceid;

    private String cmpType;

    private String cmpId;

    private String isAutoSave;

    private String submitFailReason;

    private String pageid;

    private String formid;

    private String actionType;

    private String functype;

    private String status;

    private String biz;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public Integer getSdkSt() {
        return sdkSt;
    }

    public void setSdkSt(Integer sdkSt) {
        this.sdkSt = sdkSt;
    }

    public Integer getEi() {
        return ei;
    }

    public void setEi(Integer ei) {
        this.ei = ei;
    }

    public Integer getVl() {
        return vl;
    }

    public void setVl(Integer vl) {
        this.vl = vl;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String getTraceid() {
        return traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid == null ? null : traceid.trim();
    }

    public String getCmpType() {
        return cmpType;
    }

    public void setCmpType(String cmpType) {
        this.cmpType = cmpType == null ? null : cmpType.trim();
    }

    public String getCmpId() {
        return cmpId;
    }

    public void setCmpId(String cmpId) {
        this.cmpId = cmpId == null ? null : cmpId.trim();
    }

    public String getIsAutoSave() {
        return isAutoSave;
    }

    public void setIsAutoSave(String isAutoSave) {
        this.isAutoSave = isAutoSave == null ? null : isAutoSave.trim();
    }

    public String getSubmitFailReason() {
        return submitFailReason;
    }

    public void setSubmitFailReason(String submitFailReason) {
        this.submitFailReason = submitFailReason == null ? null : submitFailReason.trim();
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid == null ? null : pageid.trim();
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid == null ? null : formid.trim();
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType == null ? null : actionType.trim();
    }

    public String getFunctype() {
        return functype;
    }

    public void setFunctype(String functype) {
        this.functype = functype == null ? null : functype.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBiz() {
        return biz;
    }

    public void setBiz(String biz) {
        this.biz = biz == null ? null : biz.trim();
    }
}