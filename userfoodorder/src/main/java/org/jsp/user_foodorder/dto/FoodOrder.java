package org.jsp.user_foodorder.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String foodItem;
	@Column(nullable = false)
	private double cost;
	@Column(nullable = false)
	@CreationTimestamp
	private LocalDateTime ordered_Time;
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime delivery_Time;
	@Column(nullable = false)
	private String address;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User u;

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodItem() {
		return foodItem;
	}

	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public LocalDateTime getOrdered_Time() {
		return ordered_Time;
	}

	public void setOrdered_Time(LocalDateTime ordered_Time) {
		this.ordered_Time = ordered_Time;
	}

	public LocalDateTime getDelivery_Time() {
		return delivery_Time;
	}

	public void setDelivery_Time(LocalDateTime delivery_Time) {
		this.delivery_Time = delivery_Time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
