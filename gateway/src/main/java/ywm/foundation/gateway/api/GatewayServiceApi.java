package ywm.foundation.gateway.api;

import com.wolf.net.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ywm.library.shared.security.SecurityUser;
import ywm.foundation.gateway.request.LoginRequest;
import ywm.foundation.gateway.support.SecurityManager;
import ywm.library.shared.YwmSpecification;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Herbert Yu on 2020-10-26 15:34
 */
@Controller
public class GatewayServiceApi implements YwmSpecification {

    private Logger logger = LoggerFactory.getLogger(GatewayServiceApi.class);


    @Autowired
    private SecurityManager securityManager;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ModelAttribute("form") LoginRequest form, HttpServletResponse response) {

//        if (properties.isRefererEnabled() && properties.getRefererWhitelist() != null && Strings.isNotBlank(form.getRefer())) {
//            long count = Streams.of(properties.getRefererWhitelist())
//                    .filter(item -> Strings.startsWith(Uri.decode(form.getRefer()), item))
//                    .count();
//
//            if (count == 0) {
//                logger.warn("[{}]来源无效, 拒绝访问", form.getRefer());
//                try {
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }
        return "/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("form") @Valid LoginRequest request,
                          BindingResult result, HttpServletResponse response) {
        String loginUrl = "/login";
        try {
//            request.setPassword(AESHelper.decryptAES(request.getPassword()));
            if (result.hasErrors()) {
                return loginUrl;
            }

            SecurityUser user = null;
            user = securityManager.login(
                    request.getUsername(), request.getPassword(), request.isRemember());

            if (user != null) {
                Uri uri = createRedirectUrl(request.getRefer(), user);
                response.sendRedirect(uri.get());
                return null;
            }
        } catch (Exception ex) {
            logger.error("用户登录失败", ex);
            addError(result, ex.getMessage());
            return loginUrl;
        }
        return "redirect:/";
    }

    private Uri createRedirectUrl(String refer, SecurityUser user) {
        /**
         * 需要去掉重定向之前的_ticket
         */
        Uri uri = Uri.create(Uri.decode(refer)).remove(TICKET);
        return uri.query(TICKET, user.getTicket());
    }

    /**
     * 登出
     */
    @RequestMapping(value = "/logout/{_ticket}", method = RequestMethod.GET)
    @ResponseBody
    public void logout(@PathVariable String _ticket) {
        securityManager.logout(_ticket);
    }

    /**
     * 从ticket获取用户对象
     */
    @RequestMapping(value = "/profile/{_ticket}", method = RequestMethod.GET)
    @ResponseBody
    public SecurityUser profile(@PathVariable String _ticket) {
        return securityManager.getUser(_ticket);
    }

    public void addError(BindingResult result, String message) {
        result.addError(new ObjectError(this.getClass().getSimpleName(), message));
    }


    /**
     * 验证ticket是否有效
     */
    @RequestMapping(value = "/validate/{_ticket}", method = RequestMethod.GET)
    @ResponseBody
    public boolean validate(@PathVariable("_ticket") String _ticket) {
        return securityManager.validate(_ticket);
    }

}
