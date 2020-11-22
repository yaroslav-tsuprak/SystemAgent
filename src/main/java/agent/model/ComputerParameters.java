package agent.model;

import agent.utils.ParamsSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yaroslav
 */

public class ComputerParameters {

    ParamsSet set = new ParamsSet();

    public ComputerParameters(ResultSet resultSet) throws SQLException {
        set.set("computer_hash_id", resultSet.getInt("computer_hash_id"));
        set.set("os_full_name", resultSet.getString("os_full_name"));
        set.set("bios_description", resultSet.getString("bios_description"));
        set.set("bios_manufacturer", resultSet.getString("bios_manufacturer"));
        set.set("bios_name", resultSet.getString("bios_name"));
        set.set("bios_release_date", resultSet.getString("bios_release_date"));
        set.set("bios_version", resultSet.getString("bios_version"));
        set.set("cpu_id", resultSet.getString("cpu_id"));
        set.set("cpu_identifier", resultSet.getString("cpu_identifier"));
        set.set("cpu_name", resultSet.getString("cpu_name"));
        set.set("cpu_vendor", resultSet.getString("cpu_vendor"));
        set.set("logical_cpu_count", resultSet.getInt("logical_cpu_count"));
        set.set("physical_cpu_count", resultSet.getInt("physical_cpu_count"));
        set.set("motherboard_manufacturer", resultSet.getString("motherboard_manufacturer"));
        set.set("motherboard_model", resultSet.getString("motherboard_model"));
        set.set("motherboard_serial", resultSet.getString("motherboard_serial"));
        set.set("motherboard_version", resultSet.getString("motherboard_version"));
        set.set("disk_model", resultSet.getString("disk_model"));
        set.set("disk_name", resultSet.getString("disk_name"));
        set.set("disk_serial", resultSet.getString("disk_serial"));
        set.set("disk_size", resultSet.getLong("disk_size"));
        set.set("usb_name", resultSet.getString("usb_name"));
        set.set("usb_unique_device_id", resultSet.getString("usb_unique_device_id"));
        set.set("usb_vendor", resultSet.getString("usb_vendor"));
        set.set("graphics_card_name", resultSet.getString("graphics_card_name"));
        set.set("graphics_card_vendor", resultSet.getString("graphics_card_vendor"));
        set.set("graphics_card_vram", resultSet.getLong("graphics_card_vram"));
        set.set("memory_bank_label", resultSet.getString("memory_bank_label"));
        set.set("memory_capacity", resultSet.getLong("memory_capacity"));
        set.set("memory_manufacturer", resultSet.getString("memory_manufacturer"));
        set.set("memory_total", resultSet.getLong("memory_total"));
        set.set("memory_type", resultSet.getString("memory_type"));
        set.set("adapters_names", resultSet.getString("adapters_names"));
        set.set("ip_address", resultSet.getString("ip_address"));
        set.set("mac_address", resultSet.getString("mac_address"));
        set.set("netbios_full_name", resultSet.getString("netbios_full_name"));
    }

    public ComputerParameters(Computer pc) {
        set.set("computer_hash_id", pc.getComputerHashId());
        set.set("os_full_name", pc.getOperationSystemInfo().getOSFullName());
        set.set("bios_description", pc.getBiosInfo().getBiosDescription());
        set.set("bios_manufacturer", pc.getBiosInfo().getBiosManufacturer());
        set.set("bios_name", pc.getBiosInfo().getBiosName());
        set.set("bios_release_date", pc.getBiosInfo().getBiosReleaseDate());
        set.set("bios_version", pc.getBiosInfo().getBiosVersion());
        set.set("cpu_id", pc.getCpuInfo().getCpuId());
        set.set("cpu_identifier", pc.getCpuInfo().getCpuIdentifier());
        set.set("cpu_name", pc.getCpuInfo().getCpuName());
        set.set("cpu_vendor", pc.getCpuInfo().getCpuVendor());
        set.set("logical_cpu_count", pc.getCpuInfo().getLogicalCpuCount());
        set.set("physical_cpu_count", pc.getCpuInfo().getPhysicalCpuCount());
        set.set("motherboard_manufacturer", pc.getMotherboardInfo().getMotherBoardManufacturer());
        set.set("motherboard_model", pc.getMotherboardInfo().getMotherBoardModel());
        set.set("motherboard_serial", pc.getMotherboardInfo().getMotherBoardSerial());
        set.set("motherboard_version", pc.getMotherboardInfo().getMotherBoardVersion());
        set.set("disk_model", pc.getDiskInfo().getDiskModel());
        set.set("disk_name", pc.getDiskInfo().getDiskName());
        set.set("disk_serial", pc.getDiskInfo().getDiskSerial());
        set.set("disk_size", pc.getDiskInfo().getDiskSize());
        set.set("usb_name", pc.getUsbInfo().getUsbName());
        set.set("usb_unique_device_id", pc.getUsbInfo().getUsbUniqueDeviceId());
        set.set("usb_vendor", pc.getUsbInfo().getUsbVendor());
        set.set("graphics_card_name", pc.getGraphicCardInfo().getGraphicsCardName());
        set.set("graphics_card_vendor", pc.getGraphicCardInfo().getGraphicsCardVendor());
        set.set("graphics_card_vram", pc.getGraphicCardInfo().getGraphicsCardVRam());
        set.set("memory_bank_label", pc.getMemoryInfo().getMemoryBankLabel());
        set.set("memory_capacity", pc.getMemoryInfo().getMemoryCapacity());
        set.set("memory_manufacturer", pc.getMemoryInfo().getMemoryManufacturer());
        set.set("memory_total", pc.getMemoryInfo().getMemoryTotal());
        set.set("memory_type", pc.getMemoryInfo().getMemoryType());
        set.set("adapters_names", pc.getNetworkInfo().getAdaptersNames());
        set.set("ip_address", pc.getNetworkInfo().getIpAddress());
        set.set("mac_address", pc.getNetworkInfo().getMacAddress());
        set.set("netbios_full_name", pc.getNetworkInfo().getDomainName());
    }

    public ParamsSet getParamSet() {
        return set;
    }
}
