package agent.enums;

import java.util.List;

/**
 * @author Yaroslav
 */

public enum EventMessages {

    MESSAGE_HASH_ID (1, "Computer hash id is changed"),
    MESSAGE_OS_FULL_NAME (2, "Operating system is changed"),
    MESSAGE_BIOS_DESCRIPTION (3, "BIOS is changes"),
    MESSAGE_BIOS_MANUFACTURER (4, "BIOS manufacturer is changed"),
    MESSAGE_BIOS_NAME (5, "BIOS name changed"),
    MESSAGE_BIOS_RELEASE_DATE (6, "BIOS release date is changed"),
    MESSAGE_BIOS_VERSION (7, "BIOS version is changed"),
    MESSAGE_CPU_ID (8, "CPU id is changed"),
    MESSAGE_CPU_IDENTIFIER (9, "CPU identifier is changed"),
    MESSAGE_CPU_NAME (10, "CPU name is changed"),
    MESSAGE_CPU_VENDOR (11, "CPU vendor is changed"),
    MESSAGE_LOGICAL_CPU_COUNT (12, "Logical CPU count is changed"),
    MESSAGE_PHYSICAL_CPU_COUNT (13, "Physical CPU count is changed"),
    MESSAGE_MOTHERBOARD_MANUFACTURER (14, "Motherboard manufacturer is changed"),
    MESSAGE_MOTHERBOARD_MODEL (15, "Motherboard model is changed"),
    MESSAGE_MOTHERBOARD_SERIAL (16, "Motherboard serial number is changed"),
    MESSAGE_MOTHERBOARD_VERSION (17, "Motherboard version is changed"),
    MESSAGE_DISK_MODEL (18, "Disk model is changed"),
    MESSAGE_DISK_NAME (19, "Disk name is changed"),
    MESSAGE_DISK_SERIAL (20, "Disk serial number is changed"),
    MESSAGE_DISK_SIZE (21, "Disk size is changed"),
    MESSAGE_USB_NAME (22, "USB name is changed"),
    MESSAGE_USB_UNIQUE_DEVICE_ID (23, "USB unique device id is changed"),
    MESSAGE_USB_VENDOR (24, "USB vendor is changed"),
    MESSAGE_GRAPHICS_CARD_NAME (25, "Graphics card name is changed"),
    MESSAGE_GRAPHICS_CARD_VENDOR (26, "Graphics card vendor is changed"),
    MESSAGE_GRAPHICS_CARD_VRAM (27, "Graphics card vram is changed"),
    MESSAGE_MEMORY_BANK_LABEL (28, "Memory bank label is changed"),
    MESSAGE_MEMORY_CAPACITY (29, "Memory capacity is changed"),
    MESSAGE_MEMORY_MANUFACTURER (30, "Memory manufacturer is changed"),
    MESSAGE_MEMORY_TOTAL (31, "Memory total is changed"),
    MESSAGE_MEMORY_TYPE (32, "Memory type is changed"),
    MESSAGE_ADAPTERS_NAMES (33, "Adapters names is changed"),
    MESSAGE_IP_ADDRESS (34, "IP address is changed"),
    MESSAGE_MAC_ADDRESS (35, "MAC address is changed"),
    MESSAGE_NETBIOS_FULL_NAME (36, "NETBIOS full name is changed");

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
