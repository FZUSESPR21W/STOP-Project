package com.fzu.stop.pojo;

import java.util.Objects;

/**
 * @author 林浩然
 */
public class DeviceInfoDTO {
	DeviceDO deviceDO;
	DeviceDTO deviceDTO;
	Boolean online;

	public DeviceInfoDTO(DeviceDO deviceDO) {
		this.deviceDO = deviceDO;
		this.deviceDTO=null;
		online=false;
	}

	public DeviceInfoDTO(DeviceDTO deviceDTO) {
		this.deviceDO=null;
		this.deviceDTO = deviceDTO;
		online=true;
	}

	public DeviceInfoDTO(DeviceDO deviceDO, DeviceDTO deviceDTO) {
		this.deviceDO = deviceDO;
		this.deviceDTO = deviceDTO;
		online=true;
	}

	public DeviceInfoDTO(DeviceDO deviceDO, DeviceDTO deviceDTO, Boolean online) {
		this.deviceDO = deviceDO;
		this.deviceDTO = deviceDTO;
		this.online = online;
	}

	public DeviceDO getDeviceDO() {
		return deviceDO;
	}

	public void setDeviceDO(DeviceDO deviceDO) {
		this.deviceDO = deviceDO;
	}

	public DeviceDTO getDeviceDTO() {
		return deviceDTO;
	}

	public void setDeviceDTO(DeviceDTO deviceDTO) {
		this.deviceDTO = deviceDTO;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DeviceInfoDTO that = (DeviceInfoDTO) o;
		return Objects.equals(deviceDO, that.deviceDO) && Objects.equals(deviceDTO, that.deviceDTO) && online.equals(that.online);
	}

	@Override
	public int hashCode() {
		return Objects.hash(deviceDO, deviceDTO, online);
	}

	@Override
	public String toString() {
		return "DeviceInfoDTO{" +
				"deviceDO=" + deviceDO +
				", deviceDTO=" + deviceDTO +
				", online=" + online +
				'}';
	}
}
