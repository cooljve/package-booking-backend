package com.oocl.packagebooking.service;

import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    if (status.equals("notTake")) {
      status = "未取件";
    } else if (status.equals("take")) {
      status = "已取件";
    } else if (status.equals("booked")) {
      status = "已预约";
    }
    return packageRepository.findByStatus(status);
  }

  public void confirmReceived(int id, Package pack) {
    Optional<Package> aPackage = packageRepository.findById(id);
    if (aPackage.isPresent()) {
      pack.setStatus("已取件");
      packageRepository.save(pack);
    }
  }

  public void updatePackageWithBookDate(String orderNumber, Date bootDate) {
    Package aPackage = packageRepository.findByOrderNumber(orderNumber);
    aPackage.setBookDate(bootDate);
    packageRepository.save(aPackage);
  }
}
