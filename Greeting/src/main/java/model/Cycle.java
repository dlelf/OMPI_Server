package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cycle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long date;
	
	
	
	@OneToMany(mappedBy="cycle")	
	private List<GoodThing> goodThings;
	
	@OneToMany(mappedBy="cycle")	
	private List<HRV> HRV;
	
	@ManyToOne
	private Person person;
	
	
	private String childMemory;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getDate() {
		return date;
	}


	public void setDate(long date) {
		this.date = date;
	}


	public List<GoodThing> getGoodThings() {
		return goodThings;
	}


	public void setGoodThings(List<GoodThing> goodThings) {
		this.goodThings = goodThings;
	}


	public List<HRV> getHRV() {
		return HRV;
	}


	public void setHRV(List<HRV> hRV) {
		HRV = hRV;
	}


	public Person getPerson() {
		return person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}


	public String getChildMemory() {
		return childMemory;
	}


	public void setChildMemory(String childMemory) {
		this.childMemory = childMemory;
	}

}