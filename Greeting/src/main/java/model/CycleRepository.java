package model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "model/Cycle", path = "cycle")

public interface CycleRepository extends PagingAndSortingRepository<Cycle, Long> {

}