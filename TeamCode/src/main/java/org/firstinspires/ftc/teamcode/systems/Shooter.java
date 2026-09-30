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
        public static double speed = 4000;
    }

    @Config("Shooter PID")
    public static class ShooterPIDConst {
        // PID values
        public static double kP = 0;
        public static double kI = 0;
        public static double kD = 0;
    }

    public Shooter(Robot robot) {
        // define shooter motor
        shooterMotor = robot.hardware.shooterMotor;
        // stop and reset encoder
        shooterMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shooterMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void run() {
        // run shooter motor
        shooterMotor.setVelocity(ShooterConsts.speed * 6, AngleUnit.DEGREES);
    }

    public void setPID() {
        // set up pid
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(ShooterPIDConst.kP, ShooterPIDConst.kI, ShooterPIDConst.kD, 0);
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
