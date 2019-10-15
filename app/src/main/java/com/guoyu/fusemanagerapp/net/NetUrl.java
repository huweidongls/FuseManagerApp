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
    //app商户审核认证
    public static final String AppUserUpdateCommercialAudit = "/AppUser/UpdateCommercialAudit";
}
