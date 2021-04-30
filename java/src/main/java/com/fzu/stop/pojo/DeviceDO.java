package com.fzu.stop.pojo;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 数据库中的device信息
 * @author 林浩然
 */
public class DeviceDO {
	Integer id;
	String name;
	BigDecimal longitude;
	BigDecimal latitude;
	Integer maxCarsNumber;
	String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public Integer getMaxCarsNumber() {
		return maxCarsNumber;
	}

	public void setMaxCarsNumber(Integer maxCarsNumber) {
		this.maxCarsNumber = maxCarsNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "DeviceDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", maxCarsNumber=" + maxCarsNumber +
				", url='" + url + '\'' +
				'}';
	}
}
