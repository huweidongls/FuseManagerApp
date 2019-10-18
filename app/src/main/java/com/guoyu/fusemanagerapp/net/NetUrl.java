package com.guoyu.fusemanagerapp.net;

/**
 * Created by Administrator on 2019/10/12.
 */

public class NetUrl {
    public static final String BASE_URL = "http://192.168.2.103:8080/";//http://192.168.2.211:8080/
    //app商户登录/管理员登录
    public static final String AppUserappAdmuinLogin = "/AppUser/appAdmuinLogin";
    //管理端用户手机号验证码发送(注册)
    public static final String AppUserregisterPhone = "/AppUser/registerPhone";
    //手机号注册(app管理端商户)
    public static final String AppUserregiste = "/AppUser/register";
    //验证码校验
    public static final String AppUseryzmCode = "/AppUser/yzmCode";
    //APP功能模块申请认证列表
    public static final String APPFuncInfoApplyFoorList = "/APPFuncInfo/ApplyFoorList";
    //app管理端查看个人信息
    public static final String AppUseradmingetOne = "/AppUser/admingetOne";
    //app商户审核认证
    public static final String AppUsercommercialAudit = "/AppUser/commercialAudit";
    //app管理端获取权限
    public static final String AppUseradminGetMuen = "/AppUser/adminGetMuen";
    //上传头像
    public static final String apiupdateheadPortrait = "/AppUser/adminUpdateheadPortrait";
    //保存个人信息
    public static final String AppUseradminUpdate = "/AppUser/adminUpdate";
    //首页最新消息
    public static final String AppHomePageLatestNews = "/AppHomePage/LatestNews";
    //个人中心政务指南分类
    public static final String AppGovernmentInfofindType = "/AppGovernmentInfo/findType";
    //个人中心政务指南列表
    public static final String AppGovernmentInfoqueryList = "/AppGovernmentInfo/queryList";
    //查询待实名认证用户列表
    public static final String AppUserqueryList = "/AppUser/queryList";
    //实名认证详情
    public static final String CitizenUsergetOne = "/CitizenUser/getOne";
    //公民身份认证审核
    public static final String AppUsercitizenUserAudit = "/AppUser/citizenUserAudit";
    //城市微观审核及未审核
    public static final String AppMiniCityInfofindStatusid = "/AppMiniCityInfo/findStatusid";
    //发布政务指南
    public static final String AppGovernmentInfotoUpdate = "/AppGovernmentInfo/toUpdate";
    //查询所有学科分类
    public static final String AppEducationInfofindType = "/AppEducationInfo/findType";
    //查询学科列表
    public static final String AppEducationInfoqueryList = "/AppEducationInfo/queryList";
}
