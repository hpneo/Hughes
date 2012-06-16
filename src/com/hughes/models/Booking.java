package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bookings database table.
 * 
 */
@Entity
@Table(name="bookings")
public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="passage_type")
	private String passageType;

	@Column(name="passenger_name")
	private String passengerName;

	//bi-directional many-to-one association to Flight
    @ManyToOne
	@JoinColumn(name="flight_id")
	private Flight flight;

	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="user_id")
	private User user;

    public Booking() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassageType() {
		return this.passageType;
	}

	public void setPassageType(String passageType) {
		this.passageType = passageType;
	}

	public String getPassengerName() {
		return this.passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public Flight getFlight() {
		return this.flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}