package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Set;


/**
 * The persistent class for the frequencies database table.
 * 
 */
@Entity
@Table(name="frequencies")
public class Frequency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Time arrival;

	private int duration;

	private Time output;

	//bi-directional many-to-one association to Flight
	@OneToMany(mappedBy="frequency")
	private Set<Flight> flights;

	//bi-directional one-to-one association to Aircraft
	@OneToOne
	@JoinColumn(name="aircraft_id")
	private Aircraft aircraft;

	//bi-directional many-to-one association to Route
    @ManyToOne
	@JoinColumn(name="route_id")
	private Route route;

    public Frequency() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getArrival() {
		return this.arrival;
	}

	public void setArrival(Time arrival) {
		this.arrival = arrival;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Time getOutput() {
		return this.output;
	}

	public void setOutput(Time output) {
		this.output = output;
	}

	public Set<Flight> getFlights() {
		return this.flights;
	}

	public void setFlights(Set<Flight> flights) {
		this.flights = flights;
	}
	
	public Aircraft getAircraft() {
		return this.aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}
	
	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}
	
}