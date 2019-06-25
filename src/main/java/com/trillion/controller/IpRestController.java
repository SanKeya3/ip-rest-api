package com.trillion.controller;


import com.github.jgonian.ipmath.Ipv4;
import com.github.jgonian.ipmath.Ipv4Range;
import com.trillion.model.IPAddress;
import com.trillion.service.IpService;
import org.apache.commons.net.util.SubnetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class IpRestController {

    @Autowired
    private IpService ipService;

    @GetMapping("/getAllIp")
    public List<IPAddress> getAllIpAddress(){
        return ipService.getAllIpAddress();

    }

    @PostMapping("/addIp/{cidr}")
    public void addIpAddress(@PathVariable String cidr){
        for (Ipv4 ipv4 : Ipv4Range.parse("127.0.0.0/"+cidr)) {
            ipService.createIpAddress(ipv4.toString());
        }
    }

    @PutMapping("/acquireIpStatus/{ip}")
    public void acquireIpAddress(@PathVariable String ip){
        if(ip.contains(","))
            ipService.acquireIpAddress(Arrays.asList(ip.split(",")));
        else
            ipService.acquireIpAddress(Arrays.asList(ip));
    }

    @PutMapping("/releaseIpStatus/{ip}")
    public void releaseIpAddress(@PathVariable String ip){
        if(ip.contains(","))
            ipService.releaseIpAddress(Arrays.asList(ip.split(",")));
        else
            ipService.releaseIpAddress(Arrays.asList(ip));
    }

}
