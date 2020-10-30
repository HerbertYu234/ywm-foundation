package ywm.foundation.user.service;


import ywm.foundation.user.model.Tenant;

/**
 * Created by Herbert Yu on 2019-11-17 18:06
 */
public interface TenantService {

    Tenant findById(String id);

    Tenant save(Tenant tenant);

    boolean delete(String id);
}
