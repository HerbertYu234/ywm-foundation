package ywm.foundation.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ywm.foundation.user.model.Tenant;
import ywm.foundation.user.repository.TenantRepository;
import ywm.foundation.user.service.TenantService;

/**
 * Created by Herbert Yu on 2019-11-17 18:06
 */
@Service
public class TenantServiceImpl implements TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public Tenant findById(String id) {
        return tenantRepository.findOne(id);
    }

    @Override
    public Tenant save(Tenant tenant) {
        return tenantRepository.save(tenant);
    }

    @Override
    public boolean delete(String id) {
        tenantRepository.delete(id);
        return true;
    }
}
