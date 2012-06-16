package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the routes database table.
 * 
 */
@Entity
@Table(name="routes")
public class Route implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Frequency
	@OneToMany(mappedBy="route")
	private Set<Frequency> frequencies;

	//bi-directional many-to-one association to Destination
    @ManyToOne
	@JoinColumn(name="destination_id")
	private Destination destination;

	//bi-directional many-to-one association to Origin
    @ManyToOne
    @JoinColumn(name="origin_id")
	private Origin origin;

    public Route() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Frequency> getFrequencies() {
		return this.frequencies;
	}

	public void setFrequencies(Set<Frequency> frequencies) {
		this.frequencies = frequencies;
	}
	
	public Destination getDestination() {
		return this.destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	public Origin getOrigin() {
		return this.origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	
}