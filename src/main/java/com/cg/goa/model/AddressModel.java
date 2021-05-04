package com.cg.goa.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
public class AddressModel {
	/*
	 * Private Members Validation
	 */
	@NotNull(message = "address Id cannot be null")
	@NotBlank(message = "address Id cannot be blank")
	private String addressId;

	@NotNull(message = "buildingNo cannot be null")
	@NotBlank(message = "buildingNo cannot be blank")
	private String buildingName;

	@NotNull(message = "streetName cannot be null")
	@NotBlank(message = "streetName cannot be blank")
	private String streetNo;

	@NotNull(message = "area cannot be null")
	@NotBlank(message = "area cannot be blank")
	private String area;

	@NotBlank(message = "city cannot be blank")
	private String city;

	@NotNull(message = "state cannot be null")
	@NotBlank(message = "state cannot be blank")
	private String state;

	@Min(value = 6, message = "pincode should be of 6 digits")
	@Max(value = 6, message = "pincode should be of 6 digits")
	private int zip;
	/*
	 * A default Constructor with no implementation
	 */

	public AddressModel() {
		// no implementation
	}

	/*
	 * A Parameterized Constructor for assigning the values to private members
	 */
	public AddressModel(String addressId, String buildingName, String streetNo, String area, String city, String state,
			int zip) {
		super();
		this.addressId = addressId;
		this.buildingName = buildingName;
		this.streetNo = streetNo;
		this.area = area;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return String.format(
				"AddressModel [addressId=%s, buildingName=%s, streetNo=%s, area=%s, city=%s, state=%s, zip=%s]",
				addressId, buildingName, streetNo, area, city, state, zip);
	}



}
