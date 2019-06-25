package com.trillion.repository;

import com.trillion.model.IPAddress;
import com.trillion.model.Status;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPRepository extends CrudRepository<IPAddress, Long> {

    @Modifying
    @Query("UPDATE IPAddress i set i.status = ?2 where i.ip = ?1")
    void setIpAddressStatus(String ip, Status status);
}
