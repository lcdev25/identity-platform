package com.lcdev25.idp.authzservice.adapter.inbound;

import com.lcdev25.idp.authzservice.usecase.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/test")
public class TestController {

  @Autowired private PermissionService permissionService;

  @GetMapping
  public String getMapping() {
    permissionService.checkPermissions("my token");
    return "Working";
  }
}
