package cn.wizzer.app.web.modules.controllers.open.api.download;

import cn.wizzer.app.sys.modules.services.SysApiService;
import cn.wizzer.app.web.commons.base.Globals;

import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wizzer on 2016/8/11.
 */
@IocBean
@At("/ota/download")
public class DownloadDataController {
    @Inject
    private SysApiService apiService;
    
    @At("")
    @Ok("raw")
    @Fail("json")
    public File downloadOta(@Param("..") NutMap map, HttpServletRequest req) {
    		String p = Globals.AppRoot;
    		String basePath = Globals.AppUploadPath;
    		String fileName = "Default.vxp";
    		//String moduleId = map.getString("id");
    		if(!StringUtils.isEmpty(map.getString("file"))) {
    			fileName = map.getString("file");
    		}
    		File file = new File(p+basePath+"/"+fileName);
    		if(file.exists()) {
    			return file;
    		}else {
    			return null;
    		}
        
    }
}
