package com.br.library.Service;


import com.br.library.Model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@FeignClient("user-server")
public interface ServiceFeign {
    @RequestMapping(method = RequestMethod.GET, value = "api/auth/{id}", consumes="application/json")
    User getUserById(@PathVariable UUID id);

    @RequestMapping(method = RequestMethod.POST, value = "api/auth/{id}", consumes="application/json")
    User registryUser(@RequestBody UserService.UserRegister userRegister);
}



