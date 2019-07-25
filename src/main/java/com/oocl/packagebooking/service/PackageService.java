package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
  @Autowired
  private PackageRepository packageRepository;

  public List<Package> getAll() {
    return packageRepository.findAll();
  }

  public Package add(Package pack) {
    return packageRepository.save(pack);
  }

  public List<Package> getPackagesByStatus(String status) {
    return packageRepository.findByStatus(status);
  }

  public void updatePackage(int id, Package pack) {
    Optional<Package> aPackage = packageRepository.findById(id);
    if (aPackage.isPresent()) {
      packageRepository.save(pack);
    }
  }
}
