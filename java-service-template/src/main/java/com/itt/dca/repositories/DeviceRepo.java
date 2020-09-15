package com.itt.dca.repositories;

import com.itt.dca.model.Device;
import org.springframework.data.repository.CrudRepository;


/**
 * DeviceRepostory interface, declares the methods exposed by the repository.
 * Following default methods are provided by CrudRepository and can be used as
 * is without requiring any implementation. save, findOne, exists, findAll,
 * count, delete and deleteAll. Please refer to the javadocs for more details.
 */
public interface DeviceRepo extends CrudRepository<Device, String> {
}
