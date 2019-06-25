package com.trillion.service;

import com.trillion.model.IPAddress;
import com.trillion.model.Status;
import com.trillion.repository.IPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class IpService {

    @Autowired
    private IPRepository ipRepository;

    public void createIpAddress(String cidr){
        ipRepository.save(new IPAddress(cidr, Status.AVAILABLE));
    }

    public List<IPAddress> getAllIpAddress(){
        List<IPAddress> allIpAddress = new ArrayList<>();
        ipRepository.findAll().forEach(allIpAddress::add);
        return allIpAddress;
    }

    @Transactional
    public void acquireIpAddress(List<String> ipaddress){
        ipaddress.stream().forEach(ip -> {
            ipRepository.setIpAddressStatus(ip, Status.ACQUIRED);
        });
    }

    @Transactional
    public void releaseIpAddress(List<String> ipaddress){
        ipaddress.stream().forEach(ip -> {
            ipRepository.setIpAddressStatus(ip, Status.AVAILABLE);
        });
    }

}
