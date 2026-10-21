package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Intake {
    // define the intake motor
    public DcMotorEx intakeMotor;
    public CRServo intakeServo;

    @Config("Intake Speeds")
    public static class IntakeConsts {
        // speed mod
        public static double motorSpeed = 1;
        public static double servoSpeed = 1;

    }

    public Intake(Robot robot) {
        // define intake motor
        intakeMotor = robot.hardware.intakeMotor;
        intakeServo = robot.hardware.intakeServo;
    }

    /**
     * Runs the intake in
     * @author Brantley
     */

    public void intake() {
        intakeMotor.setPower(Math.abs(IntakeConsts.motorSpeed));
        intakeServo.setPower(Math.abs(IntakeConsts.servoSpeed));
    }

    /**
     * Runs the intake out
     * @author Brantley
     */

    public void outtake() {
        intakeMotor.setPower(-Math.abs(IntakeConsts.motorSpeed));
        intakeServo.setPower(-Math.abs(IntakeConsts.servoSpeed));
    }

    /**
     * Stops the intake motor. Note it does not brake.
     * @author Brantley
     */

    public void stop() {
        intakeMotor.setPower(0);
        intakeServo.setPower(0);
    }
}
