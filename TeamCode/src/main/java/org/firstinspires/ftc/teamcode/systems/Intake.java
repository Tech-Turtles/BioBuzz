package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Intake {

    public DcMotorEx intakeMotor;
    public double speedModifier = 1.0;

    public Intake(Hardware hardware) {
        // define intake motor
        intakeMotor = hardware.intakeMotor;
        // adjust intake direction
        intakeMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake() {
        intakeMotor.setPower(speedModifier);
    }

    public void outtake() {
        intakeMotor.setPower(-speedModifier);
    }

    public void stop() {
        intakeMotor.setPower(0);
    }
}
