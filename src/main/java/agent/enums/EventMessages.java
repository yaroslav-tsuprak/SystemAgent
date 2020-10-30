package agent.enums;

import java.util.List;

/**
 * @author Yaroslav
 */

public enum EventMessages {

    MESSAGE_HASH_ID (1, "hash_id"),
    MESSAGE_OS_FULL_NAME (2, "os_full_name"),
    MESSAGE_BIOS_DESCRIPTION (3, "bios_description"),
    MESSAGE_BIOS_MANUFACTURER (4, "bios_manufacturer"),
    MESSAGE_BIOS_NAME (5, "bios_name"),
    MESSAGE_BIOS_RELEASE_DATE (6, "bios_release_date"),
    MESSAGE_BIOS_VERSION (7, "bios_version"),
    MESSAGE_CPU_ID (8, "cpu_id"),
    MESSAGE_CPU_IDENTIFIER (9, "cpu_identifier"),
    MESSAGE_CPU_NAME (10, "cpu_name"),
    MESSAGE_CPU_VENDOR (11, "cpu_vendor"),
    MESSAGE_LOGICAL_CPU_COUNT (12, "logical_cpu_count"),
    MESSAGE_PHYSICAL_CPU_COUNT (13, "physical_cpu_count"),
    MESSAGE_MOTHERBOARD_MANUFACTURER (14, "motherboard_manufacturer"),
    MESSAGE_MOTHERBOARD_MODEL (15, "motherboard_model"),
    MESSAGE_MOTHERBOARD_SERIAL (16, "motherboard_serial"),
    MESSAGE_MOTHERBOARD_VERSION (17, "motherboard_version"),
    MESSAGE_DISK_MODEL (18, "disk_model"),
    MESSAGE_DISK_NAME (19, "disk_name"),
    MESSAGE_DISK_SERIAL (20, "disk_serial"),
    MESSAGE_DISK_SIZE (21, "disk_size"),
    MESSAGE_USB_NAME (22, "usb_name"),
    MESSAGE_USB_UNIQUE_DEVICE_ID (23, "usb_unique_device_id"),
    MESSAGE_USB_VENDOR (24, "usb_vendor"),
    MESSAGE_GRAPHICS_CARD_NAME (25, "graphics_card_name"),
    MESSAGE_GRAPHICS_CARD_VENDOR (26, "graphics_card_vendor"),
    MESSAGE_GRAPHICS_CARD_VRAM (27, "graphics_card_vram"),
    MESSAGE_MEMORY_BANK_LABEL (28, "memory_bank_label"),
    MESSAGE_MEMORY_CAPACITY (29, "memory_capacity"),
    MESSAGE_MEMORY_MANUFACTURER (30, "memory_manufacturer"),
    MESSAGE_MEMORY_TOTAL (31, "memory_total"),
    MESSAGE_MEMORY_TYPE (32, "memory_type"),
    MESSAGE_ADAPTERS_NAMES (33, "adapters_names"),
    MESSAGE_IP_ADDRESS (34, "ip_address"),
    MESSAGE_MAC_ADDRESS (35, "mac_address"),
    MESSAGE_NETBIOS_FULL_NAME (36, "netbios_full_name");

    public static final List<EventMessages> VALUES = List.of(values());
    private final int _messageId;
    private final String _message;

    EventMessages(int messageId, String message) {
        _messageId = messageId;
        _message = message;
    }

    /**
     * @return the field id.
     */
    public int getMessageId()
    {
        return _messageId;
    }

    /**
     * Finds the {@code Message} by its messageId
     * @param messageId the messageId
     * @return the {@code Message} if its found, {@code null} otherwise.
     */
    public static EventMessages findMessageById(int messageId)
    {
        for (EventMessages messages : VALUES)
        {
            if (messages.getMessageId() == messageId)
            {
                return messages;
            }
        }
        return null;
    }
}
