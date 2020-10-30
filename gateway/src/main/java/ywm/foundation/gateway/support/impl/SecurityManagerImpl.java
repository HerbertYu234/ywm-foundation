package ywm.foundation.gateway.support.impl;

import com.wolf.lang.Landholder;
import com.wolf.lang.LandholderProvider;
import com.wolf.lang.helper.Strings;
import com.wolf.security.model.Role;
import com.wolf.security.service.WolfSecurityOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;
import ywm.library.shared.model.UserDTO;
import ywm.library.shared.security.Security;
import ywm.library.shared.security.SecurityUser;
import ywm.foundation.gateway.support.SecurityManager;
import ywm.foundation.user.model.User;
import ywm.foundation.user.service.UserService;
import ywm.library.shared.YwmSpecification;

import java.util.Objects;

/**
 * Created by Herbert Yu on 2020-10-26 15:59
 */
@Service("ywm.gateway.security.manager")
public class SecurityManagerImpl implements SecurityManager, LandholderProvider, InitializingBean, YwmSpecification {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private WolfSecurityOperations wolfSecurityOperations;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Role role = getSupervisorRole();
            User user = getSupervisorUser(role.getId());
            wolfSecurityOperations.assignRoleWithIdentity(user.getId(), role.getIdentity());
        } catch (Exception ex) {
            logger.info("[GATEWAY]初始化用户失败", ex);
        }
    }

    /**
     * 获取系统管理员用户
     */
    private User getSupervisorUser(String role) {
        User user = userService.findByUsername("admin");
        if (user != null) {
            return user;
        }
        user = new User();
        user.setRealname("系统管理员");
        user.setUsername("admin");
        user.setPassword("admin");
        user.getRoles().add(role);
        return userService.save(user);
    }

    /**
     * 获取系统管理员角色
     */
    private Role getSupervisorRole() {
        final String supervisor = "supervisor";
        Role role = wolfSecurityOperations.findRoleByIdentity(supervisor);
        if (role != null) {
            return role;
        }
        role = new Role();
        role.setIdentity(supervisor);
        role.setName("系统管理员");
        role.setDescription("系统管理员");
        role.setSupervisor(true);
        return wolfSecurityOperations.addRole(role);
    }

    @Override
    public SecurityUser login(String username, String password, boolean rememberMe) {
        User user = userService.findByUsername(username);
        SecurityUser securityUser = (SecurityUser) wolfSecurityOperations.login(username, password, rememberMe);

        securityUser.setSecurity(new Security(securityUser.getId(), null)); //设置资源权限

        Session session = sessionRepository.createSession();
        session.setAttribute(SESSION_USER, securityUser);
        securityUser.setTicket(session.getId());
        sessionRepository.save(session);
        logger.info("***用户登录 [user:{}, subject:{}]", Objects.toString(user), Objects.toString(securityUser));

        return securityUser;
    }

    @Override
    public SecurityUser getUser(String ticket) {
        Session session;
        try {
            session = sessionRepository.getSession(ticket);
        } catch (Exception ex) {
            logger.warn("获取用户Session发生异常", ex);
            return null;
        }
        return Strings.isBlank(ticket) ? null : (session == null ? null : session.getAttribute(SESSION_USER));
    }

    @Override
    public void logout(String ticket) {
        sessionRepository.delete(ticket);
        wolfSecurityOperations.logout();
    }

    @Override
    public boolean validate(String ticket) {
        Session session = sessionRepository.getSession(ticket);
        if (session instanceof ExpiringSession) {
            ExpiringSession expiringSession = (ExpiringSession) session;
            logger.info("session过期时间{},{},{}", expiringSession.getCreationTime(),
                    expiringSession.getLastAccessedTime(),
                    expiringSession.getMaxInactiveIntervalInSeconds());
        }
        return session != null;
    }

    @Override
    public Landholder resolveByPrincipal(String principal) {
        SecurityUser securityUser = null;
        try {
            securityUser = SecurityUser.of(UserDTO.of(userService.findByUsername(principal)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return securityUser;
    }

    @Override
    public Landholder resolveById(String id) {
        SecurityUser securityUser = null;
        try {
            securityUser = SecurityUser.of(UserDTO.of(userService.findByUsername(id)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return securityUser;
    }


}
