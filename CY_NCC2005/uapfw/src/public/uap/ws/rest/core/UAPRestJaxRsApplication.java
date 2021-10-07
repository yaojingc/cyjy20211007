package uap.ws.rest.core;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.restlet.Context;
import org.restlet.ext.jaxrs.JaxRsApplication;

import uap.ws.rest.app.ModuleMultiRestApplication;
import uap.ws.rest.container.ModuleRestContainerProxy;
import uap.ws.rest.container.ModuleRestResource;
import uap.ws.rest.provider.UAPProviderApplication;
import uap.ws.rest.resource.UAPResourceObjectFactory;
import uap.ws.rest.service.UAPAccessControlService;
import uap.ws.rest.service.UAPContextService;
import uap.ws.rest.service.UAPLogeerService;
import uap.ws.rest.service.UAPLoggerCYJYService;
import uap.ws.rest.util.RestLog;

public class UAPRestJaxRsApplication extends JaxRsApplication {
	public UAPRestJaxRsApplication(Application application) {
		super(application);
		init();
	}

	public UAPRestJaxRsApplication() {
		init();
	}

	public UAPRestJaxRsApplication(Context context) {
		super(context);
		init();
	}

	private void init() {
		bindApp();
		bindFilter();
		addProvider();
	}

	private void bindApp() {
		RestLog.info("###restinfo addresource::: allModules");
		setObjectFactory(UAPResourceObjectFactory.getInstance());
		Map<String, List<ModuleRestResource>> moduleInfos = ModuleRestContainerProxy.getInfos();
		Set<String> keys = moduleInfos.keySet();
		for (String module : keys) {
			RestLog.info("###restinfo moduleList:::" + module);
		}

		for (String module : moduleInfos.keySet()) {
			RestLog.info("###restiunfo addresource:::" + module);
			List<ModuleRestResource> resourceList = (List) moduleInfos.get(module);
			for (ModuleRestResource resource : resourceList) {
				RestLog.info("###restiunfo addresource:::" + resource.getResourceClass());
			}

			ModuleMultiRestApplication moduleApp = new ModuleMultiRestApplication(module,
					(ModuleRestResource[]) resourceList.toArray(new ModuleRestResource[0]));

			try {
				add(moduleApp);
			} catch (Exception e) {
				RestLog.error("###restinfo module:::" + module, e);
			} catch (Throwable e) {
				RestLog.error("###error!restinfo module:::" + module, e);
				throw e;
			}
		}
	}

	private void addProvider() {
		UAPProviderApplication uapProvider = new UAPProviderApplication();
		add(uapProvider);
	}

	private void bindFilter() {
		getServices().add(new UAPContextService());
		getServices().add(new UAPLogeerService());
		getServices().add(new UAPAccessControlService());
		
		/**
		 * 词源教育rest接口统一
		 * 添加一个拦截器
		 */
		getServices().add(new UAPLoggerCYJYService());
	}
}
