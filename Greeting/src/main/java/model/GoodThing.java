package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GoodThing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	@ManyToOne
	private Cycle cycle;
	
	private String goodThing;
	private String causality;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Cycle getCycle() {
		return cycle;
	}
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	public String getGoodThing() {
		return goodThing;
	}
	public void setGoodThing(String goodThing) {
		this.goodThing = goodThing;
	}
	public String getCausality() {
		return causality;
	}
	public void setCausality(String causality) {
		this.causality = causality;
	}		

}