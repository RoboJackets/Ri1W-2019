package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MainTele")
public class TeleOp1 extends OpMode {

    private Robot robot;
    double startPos;

    @Override
    public void init() {
        robot = new Robot(this);
        telemetry.addLine("Ready!");
        telemetry.update();

        startPos = robot.elbow.getCurrentPosition();
    }

    @Override
    public void loop() {

        double left = -gamepad1.left_stick_y;
        double right = -gamepad1.right_stick_y;

        drive(left,right);

        //intake
        //out for roller
        double intakePower = 1.0;
        if(gamepad2.a) {
            setIntake(1);
        } else {
            setIntake(0);
        }
        //gripper
        //out for roller
        if(gamepad2.right_bumper){
            //tune this value to adjust peak control
            setGripper(1);
        }
        else if(gamepad2.left_bumper){
            setGripper(0.0);
        }
        /* add back in for roller
        if(gamepad2.b){
            setRoller(-1);
        } else if(gamepad2.y){
            setRoller(1);
        } else {
            setRoller(0);
        }
         */

        //elevator
        double elevate = -gamepad2.left_stick_y;
        setLift(elevate);

        //elbow
        if(gamepad2.dpad_up && robot.elbow.getCurrentPosition() > -400) {
            setElbow(-1);
        } else if(gamepad2.dpad_down && robot.elbow.getCurrentPosition() < 10) {
            setElbow(1);
        } else {
            if(gamepad2.left_trigger > 0) {
                robot.elbow.setPower(gamepad2.left_trigger);
            }

            if(gamepad2.right_trigger > 0) {
                robot.elbow.setPower(-gamepad2.right_trigger);
            }
        }



        telemetry.addData("elbow", startPos - robot.elbow.getCurrentPosition());
        telemetry.update();
    }

    public void drive(double powerLeft, double powerRight) {
        robot.lf.setPower(powerLeft);
        robot.lb.setPower(powerLeft);
        robot.rf.setPower(powerRight);
        robot.rb.setPower(powerRight);
    }
    public void setGripper(double position) {
        robot.gripper.setPosition(position);
    }

    public void setIntake(double power) {
        robot.rIntake.setPower(power);
        robot.lIntake.setPower(power);
    }

    public void setLift(double power) {
        robot.lift.setPower(power);
    }

    public void setRoller(double power){
        robot.roller.setPower(power);
    }

    public void setElbow(double power){
        robot.elbow.setPower(power*(Math.min(.35, Math.abs(Math.cos((Math.PI/180)*(robot.elbow.getCurrentPosition()- startPos) * 3/5)))));
        telemetry.addData("POWER", power*Math.abs(Math.cos((Math.PI/180)*(robot.elbow.getCurrentPosition()- startPos) * 3/5)));
    }

}