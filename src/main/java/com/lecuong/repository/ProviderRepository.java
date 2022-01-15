package com.lecuong.repository;

import com.lecuong.entity.Provider;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends BaseRepository<Provider, Long> {

    Provider findByName(String name);
}
