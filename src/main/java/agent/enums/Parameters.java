package agent.enums;

import java.util.List;

/**
 * @author Yaroslav
 */

public enum Parameters {

    FIELD_HASH_ID (1, "computer_hash_id"),
    FIELD_OS_FULL_NAME (2, "os_full_name"),
    FIELD_BIOS_DESCRIPTION (3, "bios_description"),
    FIELD_BIOS_MANUFACTURER (4, "bios_manufacturer"),
    FIELD_BIOS_NAME (5, "bios_name"),
    FIELD_BIOS_RELEASE_DATE (6, "bios_release_date"),
    FIELD_BIOS_VERSION (7, "bios_version"),
    FIELD_CPU_ID (8, "cpu_id"),
    FIELD_CPU_IDENTIFIER (9, "cpu_identifier"),
    FIELD_CPU_NAME (10, "cpu_name"),
    FIELD_CPU_VENDOR (11, "cpu_vendor"),
    FIELD_LOGICAL_CPU_COUNT (12, "logical_cpu_count"),
    FIELD_PHYSICAL_CPU_COUNT (13, "physical_cpu_count"),
    FIELD_MOTHERBOARD_MANUFACTURER (14, "motherboard_manufacturer"),
    FIELD_MOTHERBOARD_MODEL (15, "motherboard_model"),
    FIELD_MOTHERBOARD_SERIAL (16, "motherboard_serial"),
    FIELD_MOTHERBOARD_VERSION (17, "motherboard_version"),
    FIELD_DISK_MODEL (18, "disk_model"),
    FIELD_DISK_NAME (19, "disk_name"),
    FIELD_DISK_SERIAL (20, "disk_serial"),
    FIELD_DISK_SIZE (21, "disk_size"),
    FIELD_USB_NAME (22, "usb_name"),
    FIELD_USB_UNIQUE_DEVICE_ID (23, "usb_unique_device_id"),
    FIELD_USB_VENDOR (24, "usb_vendor"),
    FIELD_GRAPHICS_CARD_NAME (25, "graphics_card_name"),
    FIELD_GRAPHICS_CARD_VENDOR (26, "graphics_card_vendor"),
    FIELD_GRAPHICS_CARD_VRAM (27, "graphics_card_vram"),
    FIELD_MEMORY_BANK_LABEL (28, "memory_bank_label"),
    FIELD_MEMORY_CAPACITY (29, "memory_capacity"),
    FIELD_MEMORY_MANUFACTURER (30, "memory_manufacturer"),
    FIELD_MEMORY_TOTAL (31, "memory_total"),
    FIELD_MEMORY_TYPE (32, "memory_type"),
    FIELD_ADAPTERS_NAMES (33, "adapters_names"),
    FIELD_IP_ADDRESS (34, "ip_address"),
    FIELD_MAC_ADDRESS (35, "mac_address"),
    FIELD_NETBIOS_FULL_NAME (36, "netbios_full_name");

    public static final List<Parameters> VALUES = List.of(values());
    private final int _fieldId;
    private final String _fieldName;

    Parameters(int fieldId, String fieldName) {
        _fieldId = fieldId;
        _fieldName = fieldName;
    }

    /**
     * @return the field id.
     */
    public int getFieldId()
    {
        return _fieldId;
    }

    /**
     * @return the field name.
     */
    public String getFieldName()
    {
        return _fieldName;
    }

    /**
     * Finds the {@code FieldName} by its fieldId
     * @param fieldId the fieldId
     * @return the {@code FieldName} if its found, {@code null} otherwise.
     */
    public static String findFieldNameById(int fieldId)
    {
        for (Parameters parameters : VALUES)
        {
            if (parameters.getFieldId() == fieldId)
            {
                return parameters.getFieldName();
            }
        }
        return null;
    }
}
