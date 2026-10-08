package com.P1.Gaser.Services;

import com.P1.Gaser.Entity.ServiceCenter;
import com.P1.Gaser.Exception.ResourceNotFoundException;
import com.P1.Gaser.Repositories.ServiceCenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCenterService {

    private final ServiceCenterRepository serviceCenterRepository;

    public ServiceCenterService(ServiceCenterRepository serviceCenterRepository) {
        this.serviceCenterRepository = serviceCenterRepository;
    }

    public ServiceCenter addServiceCenter(ServiceCenter serviceCenter) {
        return serviceCenterRepository.save(serviceCenter);
    }

    public List<ServiceCenter> getAllServiceCenters() {
        return serviceCenterRepository.findAll();
    }

    public ServiceCenter getServiceCenterById(Long id) {
        return serviceCenterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Service center not found with id: " + id));
    }

    public ServiceCenter updateServiceCenter(
            Long id,
            ServiceCenter serviceCenter) {

        ServiceCenter existingServiceCenter =
                serviceCenterRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Service center not found with id: " + id));

        existingServiceCenter.setServiceCenterName(
                serviceCenter.getServiceCenterName());

        existingServiceCenter.setAddress(
                serviceCenter.getAddress());

        return serviceCenterRepository.save(existingServiceCenter);
    }

    public void deleteServiceCenter(Long id) {

        if (!serviceCenterRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Service center not found with id: " + id);
        }

        serviceCenterRepository.deleteById(id);
    }
}