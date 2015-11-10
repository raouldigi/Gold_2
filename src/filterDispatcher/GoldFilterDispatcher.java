package filterDispatcher;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import org.apache.log4j.Logger;

//public class GoldFilterDispatcher extends org.apache.struts2.dispatcher.FilterDispatcher {
public class GoldFilterDispatcher extends org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter {    
    
//	public static Logger LOG = Logger.getLogger(Delta80FilterDispatcher.class);

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		try {
			super.doFilter(arg0, arg1, arg2);
		} catch (Exception e) {
//			LOG.debug(e.getMessage() + " " + e.getCause() + " " + e.getClass());
		}
	}
}