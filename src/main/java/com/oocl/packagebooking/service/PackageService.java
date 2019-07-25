package com.oocl.packagebooking.service;

import com.oocl.packagebooking.exception.PackageHasAlreadyBeenTakenException;
import com.oocl.packagebooking.exception.PackageNotExistedException;
import com.oocl.packagebooking.exception.TimeIsNotAllowException;
import com.oocl.packagebooking.model.Package;
import com.oocl.packagebooking.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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

  public void updatePackageWithBookDate(String orderNumber, Date bookDate) {
    if (!isValidTime(bookDate)) {
      throw new TimeIsNotAllowException();
    }
    Package aPackage = packageRepository.findByOrderNumber(orderNumber);
    if (aPackage == null) {
      throw new PackageNotExistedException();
    }
    if (aPackage.getStatus().equals("已取件")) {
      throw new PackageHasAlreadyBeenTakenException();
    }
    aPackage.setStatus("已预约");
    aPackage.setBookDate(bookDate);
    packageRepository.save(aPackage);
  }

  private boolean isValidTime(Date bookDate) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(bookDate);
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    if (!(hour >= 9 && hour <= 20)) {
      return false;
    }
    return true;
  }
}
