package cn.wizzer.app.web.modules.controllers.open.api.datacenter;

import cn.wizzer.app.sys.modules.services.SysApiService;
import cn.wizzer.framework.base.Result;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import com.wpisen.tbmpt.dataopt.model.PlcModel;
import com.wpisen.tbmpt.dataopt.task.OpDataBuffer;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wizzer on 2016/8/11.
 */
@IocBean
@At("/open/api/uploadData")
public class ApiUploadDataController {
    @Inject
    private SysApiService apiService;
    
    @At("")
    @Ok("json")
    public Object uploadData(@Param("..") NutMap map, HttpServletRequest req) {
        try {
        		String json = map.getString("json");
        		PlcModel plc = Json.fromJson(PlcModel.class, json);
        		if(plc != null && plc.getTags() != null) {
        			OpDataBuffer.getInstance().offer(plc);
        		}
        		
            return Result.success("ok");
        } catch (Exception e) {
            return Result.error("fail");
        }
    }
}
