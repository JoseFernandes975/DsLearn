package com.devsuperior.dslearnbds.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.devsuperior.dslearnbds.entities.Offer;
import com.devsuperior.dslearnbds.entities.User;

@Embeddable
public class EnrollmentPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "offer_id")
	private Offer offer;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public EnrollmentPK() {
	}

	public EnrollmentPK(Offer offer, User user) {
		super();
		this.offer = offer;
		this.user = user;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(offer, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnrollmentPK other = (EnrollmentPK) obj;
		return Objects.equals(offer, other.offer) && Objects.equals(user, other.user);
	}

}
