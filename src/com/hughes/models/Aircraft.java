package com.hughes.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the aircrafts database table.
 * 
 */
@Entity
@Table(name="aircrafts")
public class Aircraft implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int capacity;

	private String model;

	//bi-directional one-to-one association to Frequency
	@OneToOne(mappedBy="aircraft")
	private Frequency frequency;

    public Aircraft() {
    }

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Frequency getFrequency() {
		return this.frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}
	
}