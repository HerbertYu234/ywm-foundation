package ywm.foundation.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.wolf.lang.helper.Strings;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ywm.foundation.gateway.support.SecurityManager;
import ywm.library.shared.YwmSpecification;
import ywm.library.shared.security.SecurityUser;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthenticateFilter
 */
@Component
public class SecurityFilter extends ZuulFilter implements YwmSpecification {

    private Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    @Autowired
    private SecurityManager securityManager;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        authenticate(RequestContext.getCurrentContext().getRequest());
        return null;
    }


    private void authenticate(HttpServletRequest request) {
        RequestContext context = RequestContext.getCurrentContext();
        String contentType = context.getRequest().getContentType();

        String ticket = Strings.defaultIfBlank(request.getParameter(TICKET), request.getHeader(TICKET));
        SecurityUser user = Strings.isBlank(ticket) ? null : securityManager.getUser(ticket);

        context.getZuulRequestHeaders().forEach((key, value) -> logger.info("zuul headers {}:{}", key, value));


        if (user == null || user.getSecurity() == null) {
            logger.info("***拒绝访问:[{}],用户未登录或登录过期,拒绝访问", ticket);
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        } else {

            logger.info("转发请求:[{},{}]", request.getMethod(), request.getRequestURL());
            logger.info("转发请求头信息");
            logger.info(Strings.repeat('-', 100));

            context.getZuulRequestHeaders().forEach((key, value) -> logger.info("gateway SecurityFilter {}:{}", key, value));

        }

    }
}
