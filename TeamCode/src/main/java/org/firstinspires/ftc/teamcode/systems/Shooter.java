package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Shooter {

    DcMotorEx shooterMotor;
    // define shooter motor
    @Config("Shooter Speeds")
    public static class ShooterConsts {
        // speed in rotations per minute
        public static double speed = 3300;
    }

    @Config("Shooter PID")
    public static class ShooterPIDConst {
        // PID values
        public static double kP = 100;
        public static double kI = 0;
        public static double kD = 3.5;
        public static double kF = 13;
    }

    /**
     * Creates a shooter
     * @author Brantley
     * @param robot
     */

    public Shooter(Robot robot) {
        // define shooter motor
        shooterMotor = robot.hardware.shooterMotor;
        // stop and reset encoder
        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void run() {
        // set the PID
        setPID();
        // run shooter motor
        shooterMotor.setVelocity(ShooterConsts.speed * 6.0, AngleUnit.DEGREES);
    }

    public void setPID() {
        // set up pid
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(ShooterPIDConst.kP, ShooterPIDConst.kI, ShooterPIDConst.kD, ShooterPIDConst.kF);
        shooterMotor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public double getRPM() {
        // return rpm
        return (shooterMotor.getVelocity(AngleUnit.DEGREES) / 6.0);
    }

    public void stop() {
        // stop shooter motor
        shooterMotor.setVelocity(0, AngleUnit.DEGREES);
    }
}
