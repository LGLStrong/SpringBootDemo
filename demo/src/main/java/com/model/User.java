package com.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import com.dao.UserDao;

@Entity
@Table(name="ZZ_DY_GZGWDMB")
public class User {
	@Id
	@Column(name="dm")
	private String dm;
	@Column(name="mc")
	private String mc;
	@Column(name="bz")
	private String bz;
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	@Override
	public String toString() {
		return "Gzgwdmb [dm=" + dm + ", mc=" + mc + ", bz=" + bz + "]";
	}
	
	/*
	 * The DDD(domain-driven design) ,v_v ....
	 */
	@DomainEvents
	Collection<Object> domainEvents() {
	    List<Object> result = new ArrayList<Object>();
	    result.add("Here should be an Event not a String, but, anyway");
	    return result;
//	    return Arrays.asList("");
	}

	@AfterDomainEventPublication 
	void callbackMethod() {
	    System.out.println("DATA SAVED! WELL DONE");
	}
	//
	
}
