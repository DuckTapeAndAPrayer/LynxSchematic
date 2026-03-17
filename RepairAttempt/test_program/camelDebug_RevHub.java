package org.firstinspires.ftc.teamcode.camel;

import com.qualcomm.hardware.adafruit.AdafruitI2cColorSensor;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp(name = "CamelDebug_RevHub", group = "CoralTech")

public class camelDebug_RevHub extends LinearOpMode {
    private DcMotorEx m0,m1,m2,m3;
    private Servo servo0,servo1,servo2,servo3,servo4,servo5;
    private DigitalChannel d0,d1,d2,d3,d4,d5,d6,d7;
    private AnalogInput a0,a1,a2,a3;
    private IMU imu;
    //private AdafruitI2cColorSensor color1,color2,color3;
    GoBildaPinpointDriver pp0,pp1,pp2,pp3;
    camelDSMenu mainmenu = new camelDSMenu();
    public static final int DEVICE_TYPE_NONE = 0;
    public static final int DEVICE_TYPE_MOTOR = 1;
    public static final int DEVICE_TYPE_SERVO = 2;
    public static final int DEVICE_TYPE_DIGITAL = 3;
    public static final int DEVICE_TYPE_ANALOG = 4;
    public static final int DEVICE_TYPE_IMU = 5;
    public static final int DEVICE_TYPE_I2C = 6;
    public static final int DEVICE_TYPE_DIGITAL_IN = 7;
    int test_device_type=DEVICE_TYPE_NONE;
    public camelRemoteLog rlog=new camelRemoteLog("192.168.43.140");


    @Override
    public void runOpMode() {
        rlog.start();
        rlog.printf("\n\n\n\n");
        rlog.printf("===================================================================================\n");
        rlog.printf("Starting RevHub Test program :)\n");
        rlog.printf("===================================================================================\n");
        try {
            m0 = hardwareMap.get(DcMotorEx.class, "motor0");
        } catch (Exception e) {
        }
        try {
            m1 = hardwareMap.get(DcMotorEx.class, "motor1");
        } catch (Exception e) {
        }
        try {
            m2 = hardwareMap.get(DcMotorEx.class, "motor2");
        } catch (Exception e) {
        }
        try {
            m3 = hardwareMap.get(DcMotorEx.class, "motor3");
        } catch (Exception e) {
        }
        try {
            servo0 = hardwareMap.get(Servo.class, "servo0");
        } catch (Exception e) {
        }
        try {
            servo1 = hardwareMap.get(Servo.class, "servo1");
        } catch (Exception e) {
        }
        try {
            servo2 = hardwareMap.get(Servo.class, "servo2");
        } catch (Exception e) {
        }
        try {
            servo3 = hardwareMap.get(Servo.class, "servo3");
        } catch (Exception e) {
        }
        try {
            servo4 = hardwareMap.get(Servo.class, "servo4");
        } catch (Exception e) {
        }
        try {
            servo5 = hardwareMap.get(Servo.class, "servo5");
        } catch (Exception e) {
        }
        try {
            d0 = hardwareMap.get(DigitalChannel.class, "d0");
        } catch (Exception e) {
        }
        try {
            d1 = hardwareMap.get(DigitalChannel.class, "d1");
        } catch (Exception e) {
        }
        try {
            d2 = hardwareMap.get(DigitalChannel.class, "d2");
        } catch (Exception e) {
        }
        try {
            d3 = hardwareMap.get(DigitalChannel.class, "d3");
        } catch (Exception e) {
        }
        try {
            d4 = hardwareMap.get(DigitalChannel.class, "d4");
        } catch (Exception e) {
        }
        try {
            d5 = hardwareMap.get(DigitalChannel.class, "d5");
        } catch (Exception e) {
        }
        try {
            d6 = hardwareMap.get(DigitalChannel.class, "d6");
        } catch (Exception e) {
        }
        try {
            d7 = hardwareMap.get(DigitalChannel.class, "d7");
        } catch (Exception e) {
        }
        try {
            a0 = hardwareMap.get(AnalogInput.class, "a0");
        } catch (Exception e) {
        }
        try {
            a1 = hardwareMap.get(AnalogInput.class, "a1");
        } catch (Exception e) {
        }
        try {
            a2 = hardwareMap.get(AnalogInput.class, "a2");
        } catch (Exception e) {
        }
        try {
            a3 = hardwareMap.get(AnalogInput.class, "a3");
        } catch (Exception e) {
        }
        try {
            imu = hardwareMap.get(IMU.class, "imu");
            imu.initialize(
                    new IMU.Parameters(
                            new RevHubOrientationOnRobot(
                                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                                    RevHubOrientationOnRobot.UsbFacingDirection.LEFT
                            )
                    )
            );
        } catch (Exception e) {
        }
        try {
            pp0 = hardwareMap.get(GoBildaPinpointDriver.class, "i2c0");
        } catch (Exception e) {
        }
        try {
            pp1 = hardwareMap.get(GoBildaPinpointDriver.class, "i2c1");
        } catch (Exception e) {
        }
        try {
            pp2 = hardwareMap.get(GoBildaPinpointDriver.class, "i2c2");
        } catch (Exception e) {
        }
        try {
            pp3 = hardwareMap.get(GoBildaPinpointDriver.class, "i2c3");
        } catch (Exception e) {
        }
        if(m0==null) telemetry.addData("ERR:","Motor 0 not found");
        if(m1==null) telemetry.addData("ERR:","Motor 1 not found");
        if(m2==null) telemetry.addData("ERR:","Motor 2 not found");
        if(m3==null) telemetry.addData("ERR:","Motor 3 not found");
        if(servo0==null) telemetry.addData("ERR:","Servo 0 not found");
        if(servo1==null) telemetry.addData("ERR:","Servo 1 not found");
        if(servo2==null) telemetry.addData("ERR:","Servo 2 not found");
        if(servo3==null) telemetry.addData("ERR:","Servo 3 not found");
        if(servo4==null) telemetry.addData("ERR:","Servo 4 not found");
        if(servo5==null) telemetry.addData("ERR:","Servo 5 not found");
        if(d0==null) telemetry.addData("ERR:","Digital 0 not found");
        if(d1==null) telemetry.addData("ERR:","Digital 1 not found");
        if(d2==null) telemetry.addData("ERR:","Digital 2 not found");
        if(d3==null) telemetry.addData("ERR:","Digital 3 not found");
        if(d4==null) telemetry.addData("ERR:","Digital 4 not found");
        if(d5==null) telemetry.addData("ERR:","Digital 5 not found");
        if(d6==null) telemetry.addData("ERR:","Digital 6 not found");
        if(d7==null) telemetry.addData("ERR:","Digital 7 not found");
        if(a0==null) telemetry.addData("ERR:","Analog 0 not found");
        if(a1==null) telemetry.addData("ERR:","Analog 1 not found");
        if(a2==null) telemetry.addData("ERR:","Analog 2 not found");
        if(a3==null) telemetry.addData("ERR:","Analog 3 not found");
        if(imu==null) telemetry.addData("ERR:","Internal IMU not found");
        if(pp0==null) telemetry.addData("ERR:","I2C bus 0 sensor not found");
        if(pp1==null) telemetry.addData("ERR:","I2C bus 1 sensor not found");
        if(pp2==null) telemetry.addData("ERR:","I2C bus 2 sensor not found");
        if(pp3==null) telemetry.addData("ERR:","I2C bus 3 sensor not found");
        telemetry.update();

        waitForStart();
        //d0.setMode(DigitalChannel.Mode.OUTPUT);d1.setMode(DigitalChannel.Mode.OUTPUT);d0.setState(false);d1.setState(false);
        menuLoad(0);
        while (opModeIsActive()) {
            updateTestMenu();
             if(test_device_type==DEVICE_TYPE_MOTOR) {
                DcMotorEx test_motor=null;
                switch(mainmenu.getValueInt(1)) {
                    case 0: test_motor=m0;break;
                    case 1: test_motor=m1;break;
                    case 2: test_motor=m2;break;
                    case 3: test_motor=m3;break;
                }
                if(test_motor!=null) {
                    if(Math.abs(gamepad1.left_stick_y)>0.05) {
                        double stick_pos=gamepad1.left_stick_y*3000;
                        test_motor.setVelocity(stick_pos);
                        telemetry.addData("Motor stick","%d speed %d",mainmenu.getValueInt(1),(int)test_motor.getVelocity());
                    } else {
                        test_motor.setVelocity(mainmenu.getValueInt(2));
                        telemetry.addData("Motor","%d speed %d",mainmenu.getValueInt(1),(int)test_motor.getVelocity());
                    }
                    //test_motor.setVelocity(mainmenu.getValueInt(2));
                    //telemetry.addData("Motor","%d speed %d",mainmenu.getValueInt(1),(int)test_motor.getVelocity());
                    telemetry.addData("Encoder:",test_motor.getCurrentPosition());
                }
                else telemetry.addData("ERROR:","motor %d is missing!",mainmenu.getValueInt(1));
            } //end if(motor)
            if(test_device_type==DEVICE_TYPE_SERVO) {
                Servo test_motor=null;
                switch(mainmenu.getValueInt(1)) {
                    case 0: test_motor=servo0;break;
                    case 1: test_motor=servo1;break;
                    case 2: test_motor=servo2;break;
                    case 3: test_motor=servo3;break;
                    case 4: test_motor=servo4;break;
                    case 5: test_motor=servo5;break;
                }
                if(test_motor!=null) {
                    telemetry.addData("stick position","%.3f",gamepad1.left_stick_y);
                    if(Math.abs(gamepad1.left_stick_y)>0.05) {
                        double stick_pos=(gamepad1.left_stick_y+1)/2;
                        test_motor.setDirection(Servo.Direction.FORWARD);
                        test_motor.setPosition(stick_pos);
                        telemetry.addData("Servo stick", "%d pos %.3f", mainmenu.getValueInt(1), test_motor.getPosition());
                    } else {
                        test_motor.setDirection(Servo.Direction.FORWARD);
                        test_motor.setPosition(mainmenu.getValueDouble(2));
                        telemetry.addData("Servo dpad", "%d pos %.3f", mainmenu.getValueInt(1), test_motor.getPosition());
                    }
                }
                else telemetry.addData("ERROR:","servo %d is missing!",mainmenu.getValueInt(1));
            } //end if(servo)
            if(test_device_type==DEVICE_TYPE_DIGITAL) {
                DigitalChannel digital_port=null;
                switch(mainmenu.getValueInt(1)) {
                    case 0: digital_port=d0;break;
                    case 1: digital_port=d1;break;
                    case 2: digital_port=d2;break;
                    case 3: digital_port=d3;break;
                    case 4: digital_port=d4;break;
                    case 5: digital_port=d5;break;
                    case 6: digital_port=d6;break;
                    case 7: digital_port=d7;break;
                }
                if(digital_port!=null) {
                    if(digital_port.getMode()!= DigitalChannel.Mode.OUTPUT) digital_port.setMode(DigitalChannel.Mode.OUTPUT);
                    boolean new_state=mainmenu.getValueInt(2)!=0;
                    digital_port.setState(new_state);
                    telemetry.addData("Digital out", "%d val %b", mainmenu.getValueInt(1), new_state);
                }
                else telemetry.addData("ERROR:","digital out %d is missing!",mainmenu.getValueInt(1));
            } //end if(digital)
            if(test_device_type==DEVICE_TYPE_ANALOG) {
                if(a0!=null) telemetry.addData("Analog", "0: %.2f", a0.getVoltage());
                else telemetry.addData("ERROR:","analog in 0 is missing!");
                if(a1!=null) telemetry.addData("Analog", "1: %.2f", a1.getVoltage());
                else telemetry.addData("ERROR:","analog in 1 is missing!");
                if(a2!=null) telemetry.addData("Analog", "2: %.2f", a2.getVoltage());
                else telemetry.addData("ERROR:","analog in 2 is missing!");
                if(a3!=null) telemetry.addData("Analog", "3: %.2f", a3.getVoltage());
                else telemetry.addData("ERROR:","analog in 3 is missing!");
            } //end if(analog)
            if(test_device_type==DEVICE_TYPE_I2C) {
                if(pp0!=null) {
                    pp0.initialize();
                    pp0.update();
                    telemetry.addData("I2C 0",pp0.getDeviceStatus());
                } else telemetry.addData("I2C 0","device missing in config");
                if(pp1!=null) {
                    pp1.initialize();
                    pp1.update();
                    telemetry.addData("I2C 1",pp1.getDeviceStatus());
                } else telemetry.addData("I2C 1","device missing in config");
                if(pp2!=null) {
                    pp2.initialize();
                    pp2.update();
                    telemetry.addData("I2C 2",pp2.getDeviceStatus());
                } else telemetry.addData("I2C 2","device missing in config");
                if(pp3!=null) {
                    pp3.initialize();
                    pp3.update();
                    telemetry.addData("I2C 3",pp3.getDeviceStatus());
                } else telemetry.addData("I2C 3","device missing in config");
            } //end if(i2c)
            if(test_device_type==DEVICE_TYPE_IMU) {
                if(imu!=null)
                    telemetry.addData("IMU","%.2f %.2f %.2f",imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle,imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle,imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle);
                else telemetry.addData("IMU","not initialized");
            } //end if(imu)
            if(test_device_type==DEVICE_TYPE_DIGITAL_IN) {
                char c0='?',c1='?',c2='?',c3='?',c4='?',c5='?',c6='?',c7='?';
                if(d0!=null) {
                    if(d0.getMode()!= DigitalChannel.Mode.INPUT) d0.setMode(DigitalChannel.Mode.INPUT);
                    if(d0.getState()) c0='+'; else c0='-';
                }
                if(d1!=null) {
                    if(d1.getMode()!= DigitalChannel.Mode.INPUT) d1.setMode(DigitalChannel.Mode.INPUT);
                    if(d1.getState()) c1='+'; else c1='-';
                }
                if(d2!=null) {
                    if(d2.getMode()!= DigitalChannel.Mode.INPUT) d2.setMode(DigitalChannel.Mode.INPUT);
                    if(d2.getState()) c2='+'; else c2='-';
                }
                if(d3!=null) {
                    if(d3.getMode()!= DigitalChannel.Mode.INPUT) d3.setMode(DigitalChannel.Mode.INPUT);
                    if(d3.getState()) c3='+'; else c3='-';
                }
                if(d4!=null) {
                    if(d4.getMode()!= DigitalChannel.Mode.INPUT) d4.setMode(DigitalChannel.Mode.INPUT);
                    if(d4.getState()) c4='+'; else c4='-';
                }
                if(d5!=null) {
                    if(d5.getMode()!= DigitalChannel.Mode.INPUT) d5.setMode(DigitalChannel.Mode.INPUT);
                    if(d5.getState()) c5='+'; else c5='-';
                }
                if(d6!=null) {
                    if(d6.getMode()!= DigitalChannel.Mode.INPUT) d6.setMode(DigitalChannel.Mode.INPUT);
                    if(d6.getState()) c6='+'; else c6='-';
                }
                if(d7!=null) {
                    if(d7.getMode()!= DigitalChannel.Mode.INPUT) d7.setMode(DigitalChannel.Mode.INPUT);
                    if(d7.getState()) c7='+'; else c7='-';
                }
                    telemetry.addData("Digital in","%c%c %c%c %c%c %c%c",c0,c1,c2,c3,c4,c5,c6,c7);
            } //end if(digital in)
            telemetry.addData("Rlog",rlog.getStatus());
            telemetry.update();
        } //end while(opmode_active)
        rlog.finish();
    }

    void menuLoad(int idx)
    {
        mainmenu.setMenuNumber(idx);
        mainmenu.clear();
        if(idx==0) {
            mainmenu.addItem("Motors"); //1
            mainmenu.addItem("Servos"); //2
            mainmenu.addItem("Digital"); //3
            mainmenu.addItem("Analog"); //4
            mainmenu.addItem("IMU"); //5
            mainmenu.addItem("I2C"); //6
            mainmenu.addItem("Digital IN"); //7
            test_device_type=DEVICE_TYPE_NONE;
        } else mainmenu.addItem("Go back");
        if(idx==1) {
            mainmenu.addItem("Motor number",0,1,0,3);
            mainmenu.addItem("Velocity",0,250,-4000,4000);
            test_device_type=DEVICE_TYPE_MOTOR;
        }
        if(idx==2) {
            mainmenu.addItem("Servo number",0,1,0,5);
            mainmenu.addItem("Position",0.5,0.1,0,1);
            test_device_type=DEVICE_TYPE_SERVO;
        }
        if(idx==3) {
            mainmenu.addItem("Digital port number",0,1,0,7);
            mainmenu.addItem("Out value",0,1,0,1);
            test_device_type=DEVICE_TYPE_DIGITAL;
        }
        if(idx==4) {
            //mainmenu.addItem("Analog port number",0,1,0,3);
            test_device_type=DEVICE_TYPE_ANALOG;
        }
        if(idx==5) {
            mainmenu.addItem("Internal IMU");
            test_device_type=DEVICE_TYPE_IMU;
        }
        if(idx==6) {
            mainmenu.addItem("I2C bus",1,1,1,3);
            test_device_type=DEVICE_TYPE_I2C;
        }
        if(idx==7) {
            //mainmenu.addItem("Digital in",1,1,1,3);
            test_device_type=DEVICE_TYPE_DIGITAL_IN;
        }
    }

    boolean updateTestMenu()
    {
        //rlog.printf("Main menu getMenuNumber()=%d, getSelected()=%d\n", mainmenu.getMenuNumber(), mainmenu.getSelected());
        telemetry.addData("mm","getmenunum=%d selected=%d",mainmenu.getMenuNumber(), mainmenu.getSelected());
        String mm=mainmenu.getString();
        telemetry.addLine(mm);
        if(gamepad1.dpadDownWasPressed()) {mainmenu.selectNext();rlog.printf("menu next\n");}
        if(gamepad1.dpadUpWasPressed()) {mainmenu.selectPrev();rlog.printf("menu prev\n");}
        boolean drp=gamepad1.dpadRightWasPressed();
        if(drp && mainmenu.getMenuNumber()!=0)
        {
            if(mainmenu.getSelected()>0) mainmenu.increment();
            rlog.printf("Menu increment value\n");
        }
        if(drp && mainmenu.getMenuNumber()==0)
        {
            rlog.printf("Menu load sumbenu %d\n",mainmenu.getSelected());
            menuLoad(mainmenu.getSelected()+1);
        }
        if(gamepad1.dpadLeftWasPressed() && mainmenu.getMenuNumber()!=0)
        {
            rlog.printf("Menu decrement value\n");
            if(mainmenu.getSelected()==0) menuLoad(0);
            else mainmenu.decrement();
        }
        return false;
    } //end function updateTestMenu()
}
