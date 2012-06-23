package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the flights database table.
 * 
 */
@Entity
@Table(name="flights")
public class Flight implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="available_seats")
	private int availableSeats;

	private String code;

	@Column(name="flight_class")
	private String flightClass;

    @Temporal( TemporalType.DATE)
	@Column(name="output_date")
	private Date outputDate;

	private BigDecimal price;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="flight")
	private Set<Booking> bookings;

	//bi-directional many-to-one association to Frequency
    @ManyToOne
	@JoinColumn(name="frequency_id")
	private Frequency frequency;

    public Flight() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailableSeats() {
		return this.availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlightClass() {
		return this.flightClass;
	}

	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}

	public Date getOutputDate() {
		return this.outputDate;
	}

	public void setOutputDate(Date outputDate) {
		this.outputDate = outputDate;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Set<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(Set<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public Frequency getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
}