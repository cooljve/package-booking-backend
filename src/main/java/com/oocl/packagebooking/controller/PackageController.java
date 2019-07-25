package com.oocl.packagebooking.controller;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "manage/packages")
public class PackageController {

  @Autowired
  private PackageService service;

  @GetMapping
  public List<Package> getAllPackages(){
    return service.getAll();
  }

  @GetMapping(params = "status")
  public List<Package> getPackagesByStatus(@RequestParam String status){
    return service.getPackagesByStatus(status);
  }

  @PostMapping
  public Package addPackage(@RequestBody Package pack){
    return service.add(pack);
  }
  @PutMapping("{id}")
  public void updatePackage(@PathVariable int id,@RequestBody Package pack) {
    service.updatePackage(id, pack);
  }
}
