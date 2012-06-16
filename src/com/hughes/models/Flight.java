package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


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

	private String code;

	private BigDecimal price;

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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Frequency getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
}