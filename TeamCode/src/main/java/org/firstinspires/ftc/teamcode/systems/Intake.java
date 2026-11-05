package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.utility.commands.IntakeCommand;

public class Intake {
    // define the intake motor
    public DcMotorEx intakeMotor;
    public CRServo intakeServo;

    public Robot robot;

    @Config("Intake Speeds")
    public static class Constants {
        // speed mod
        public static double motorSpeed = 1;
        public static double servoSpeed = 1;

    }

    public Intake(Robot robot) {
        // define intake motor
        intakeMotor = robot.hardware.intakeMotor;
        intakeServo = robot.hardware.intakeServo;
    }

    public void smartIntake(IntakeCommand command) {
        if (command.getValidity()) {
            if (command.getPositivity()) {
                intake(command.getValue());
            } else {
                outtake(command.getValue());
            }
        } else {
            stop();
        }
    }

    /**
     * Runs the intake in
     * @author Brantley
     */

    public void intake(double speed) {
        intakeMotor.setPower(Constants.motorSpeed * speed);
        intakeServo.setPower(Constants.servoSpeed * speed);
    }

    /**
     * Runs the intake out
     * @author Brantley
     */

    public void outtake(double speed) {
        intakeMotor.setPower(-Constants.motorSpeed * speed);
        intakeServo.setPower(-Constants.servoSpeed * speed);
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
