package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class HRV {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long date;
	
	
	@ManyToOne
	private Cycle cycle;
	
	private long rrInterval;
	
	private boolean beforeReading;

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

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public long getRrInterval() {
		return rrInterval;
	}

	public void setRrInterval(long rrInterval) {
		this.rrInterval = rrInterval;
	}

	public boolean isBeforeReading() {
		return beforeReading;
	}

	public void setBeforeReading(boolean beforeReading) {
		this.beforeReading = beforeReading;
	}	

}