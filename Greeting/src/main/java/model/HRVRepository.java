package model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "model/HRV", path = "hrv")

public interface HRVRepository extends PagingAndSortingRepository<HRV, Long> {

}