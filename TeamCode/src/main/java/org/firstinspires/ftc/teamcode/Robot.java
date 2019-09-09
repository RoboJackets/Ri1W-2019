package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Robot.java - contains all subsystems, hardware, and vision software
 */

public class Robot {
    //Motors
    public DcMotor rf, rb, lf, lb, elbow, lift;
    public DcMotor lIntake, rIntake;
    public DcMotor roller;


    //Servos
    public Servo gripper; //out for roller
    public Servo keystone;
            //lDragger, rDragger, keystone;

    //Sensors
    public BNO055IMU imu;

    //public VoltageSensor voltageSensor;
    //Subsystems
    public OpMode opMode;
    public LinearOpMode LinearOpMode;
    public HardwareMap hardwareMap;

    public Robot(LinearOpMode l) {
        LinearOpMode = l;
        this.hardwareMap = LinearOpMode.hardwareMap;
        init();
    }

    public Robot(OpMode o) {
        opMode = o;
        this.hardwareMap = opMode.hardwareMap;
        init();
    }

    /**
     * Initializes all hardware and subsystems
     */
    private void init() {

        //Drive Motors
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        lIntake = hardwareMap.dcMotor.get("lIntake"); //out for roller
        rIntake = hardwareMap.dcMotor.get("rIntake"); //out for roller
        //roller = hardwareMap.dcMotor.get("roller"); //in for roller
        elbow = hardwareMap.dcMotor.get("elbow");
        lift = hardwareMap.dcMotor.get("lift");

        //Motor Presets
        lf.setDirection(DcMotor.Direction.REVERSE);
        lb.setDirection(DcMotor.Direction.REVERSE);
        elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Servos
        gripper = hardwareMap.servo.get("gripper"); // out for roller
        keystone = hardwareMap.servo.get("keystone");

        //Sensors (IMU + External)
        //voltageSensor = hardwareMap.voltageSensor.get("Motor Controller 1");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        initImu();

    }

    private void initImu() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters(); //parameters object
        parameters.mode = BNO055IMU.SensorMode.IMU; //IMU sensormode, see documentation
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES; //Degrees as the unit for angle
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC; //M/s^2 as the unit for acceleration
        parameters.loggingEnabled = false;
        imu.initialize(parameters);
    }
}