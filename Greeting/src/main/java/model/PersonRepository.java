package model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestMapping;


@RepositoryRestResource(collectionResourceRel = "model/people", path = "people")
//@RequestMapping("/people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
//	public interface PersonRepository extends JpaRepository<Person, Long> {

	//List<Person> findByLastName(@Param("name") String name);
	//List<Person> persons = findall();

}