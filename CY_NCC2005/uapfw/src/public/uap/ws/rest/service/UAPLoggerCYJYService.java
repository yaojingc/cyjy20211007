package uap.ws.rest.service;

import org.restlet.Context;
import org.restlet.routing.Filter;
import org.restlet.service.Service;

import uap.ws.rest.filter.UAPLoggerCYJYFilter;

public class UAPLoggerCYJYService extends Service {

	public Filter createInboundFilter(Context context) {

		return new UAPLoggerCYJYFilter(context, this);

	}

}
