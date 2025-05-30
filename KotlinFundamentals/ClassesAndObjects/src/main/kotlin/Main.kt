import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*open class SmartDevice protected constructor(val name: String, val category: String) {*/
open class SmartDevice(val name: String, val category: String) {

    var deviceStatus = "online"
        protected set

    protected open val deviceType = "unknown"

    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

/* IS-A relationship with SmartDevice */
class SmartTvDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    /*private var speakerVolume = 2
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
     */
    private var speakerVolume by RangeRegulator(
        initialValue = 2,
        minValue = 0,
        maxValue = 100
    )

    /*private var channelNumber = 1
        set(value) {
            if (value in 0..200) {
                field = value
            }
        }
     */
    private var channelNumber by RangeRegulator(
        initialValue = 1,
        minValue = 0,
        maxValue = 200
    )

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume")
    }

    fun decreaseSpeakerVolume() {
        speakerVolume--
        println("Speaker volume decreased to $speakerVolume")
    }

    fun nextChannel() {
        channelNumber++
        println("Channel number increased to $channelNumber")
    }

    fun previousChannel() {
        channelNumber--
        println("Channel number decreased to $channelNumber")
    }

    override fun turnOn() {
        super.turnOn()
        println("$name turned on. Speaker volume is set to $speakerVolume and channel number " +
                    "is set to $channelNumber"
        )
    }

    override fun turnOff() {
        super.turnOff()
        println("$name turned off")
    }
}

/* IS-A relationship with `SmartDevice' */
class SmartLightDevice(deviceName: String, deviceCategory: String) :
        SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart Light"

    /*private var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }
     */
    private var brightnessLevel by RangeRegulator(
        initialValue = 0,
        minValue = 0,
        maxValue = 100
    )

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel")
    }

    fun decreaseBrightness() {
        brightnessLevel--
        println("Brightness decreased to $brightnessLevel")
    }


    override fun turnOn() {
        super.turnOn()
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel")
    }

    override fun turnOff() {
        super.turnOff()
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

/* HAS-A relationship with `SmartTvDevice' and `SmartLightDevice' */
class SmartHome(
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
) {

    var deviceTurnOnCount = 0
        private set

    fun turnOnTv() {
        smartTvDevice.turnOn()
        deviceTurnOnCount++
    }

    fun turnOffTv() {
        smartTvDevice.turnOff()
        deviceTurnOnCount--
    }

    fun increaseTvVolume() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.increaseSpeakerVolume()
        }
    }

    fun decreaseTvVolume() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.decreaseSpeakerVolume()
        }
    }

    fun changeTvChannelToNext() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.nextChannel()
        }
    }

    fun changeTvChannelToPrevious() {
        if (smartTvDevice.deviceStatus == "on") {
            smartTvDevice.previousChannel()
        }
    }

    fun printSmartTvInfo() {
        smartTvDevice.printDeviceInfo()
    }

    fun turnOnLight() {
        smartLightDevice.turnOn()
        deviceTurnOnCount++
    }

    fun turnOffLight() {
        smartLightDevice.turnOff()
        deviceTurnOnCount--
    }

    fun increaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.increaseBrightness()
        }
    }

    fun decreaseLightBrightness() {
        if (smartLightDevice.deviceStatus == "on") {
            smartLightDevice.decreaseBrightness()
        }
    }

    fun printSmartLightInfo() {
        smartLightDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    val initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    var smartTvDevice = SmartTvDevice(
        "Android TV",
        "Entertainment"
    )
    smartTvDevice.turnOn()

    var smartLightDevice = SmartLightDevice(
        "Google Light",
        "Utility"
    )
    smartLightDevice.turnOn()

    var smartHome = SmartHome(smartTvDevice, smartLightDevice)
    smartHome.turnOnTv()
    smartHome.increaseTvVolume()
    smartHome.decreaseTvVolume()
    smartHome.changeTvChannelToNext()
    smartHome.changeTvChannelToPrevious()
    smartHome.printSmartTvInfo()

    smartHome.turnOnLight()
    smartHome.increaseLightBrightness()
    smartHome.decreaseLightBrightness()
    smartHome.printSmartLightInfo()

    val livingroomSmartTvDevice = SmartDevice(name = "Living-room TV", category = "Entertainment")
    println("\n\nLiving-room device name is: ${livingroomSmartTvDevice.name}")
    livingroomSmartTvDevice.turnOn()
    println("Living-room device turned on; status is: ${livingroomSmartTvDevice.deviceStatus}")
    livingroomSmartTvDevice.turnOff()
    println("Living-room device turned off; status is: ${livingroomSmartTvDevice.deviceStatus}")
    livingroomSmartTvDevice.printDeviceInfo()
}