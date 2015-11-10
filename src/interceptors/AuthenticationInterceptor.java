package interceptors;

import bean.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;

public class AuthenticationInterceptor implements Interceptor {
    private static final long serialVersionUID = -5011962009065225959L;

	@Override
	public void destroy() {
		//release resources here
	}

	@Override
	public void init() {
		// create resources here
	}

	@Override
	public String intercept(ActionInvocation actionInvocation)
			throws Exception {
            
		Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
		
		User user = (User) sessionAttributes.get("loggedUser");
		
		if(user == null){
			return Action.LOGIN;
		}else{
			Action action = (Action) actionInvocation.getAction();
			if(action instanceof UserAware){
				((UserAware) action).setUser(user);
			}
			return actionInvocation.invoke();
		}
	}
    
}
