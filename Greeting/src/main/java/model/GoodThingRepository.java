package model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "model/GoodThing", path = "GoodThing")

public interface GoodThingRepository extends PagingAndSortingRepository<GoodThing, Long> {

}