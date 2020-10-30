package ywm.foundation.user.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.foundation.user.model.Tenant;

/**
 * Created by Herbert Yu on 2019-11-17 18:05
 */
public interface TenantRepository extends WolfMongoRepository<Tenant, String> {

    @Override
    Tenant save(Tenant tenant);

    @Override
    Tenant findOne(String id);

    @Override
    void delete(String id);
}
