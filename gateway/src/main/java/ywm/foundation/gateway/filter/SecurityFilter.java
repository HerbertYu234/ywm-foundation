package ywm.foundation.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * AuthenticateFilter
 */
@Component
public class SecurityFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(SecurityFilter.class);


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
//        try {
//            context.getRequest().setCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
////        String contentType1 = context.getResponse().getContentType();
//        context.getResponse().setCharacterEncoding("UTF-8");
//        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.getZuulRequestHeaders().forEach((key, value) -> logger.debug("gateway SecurityFilter {}:{}", key, value));
    }
}
