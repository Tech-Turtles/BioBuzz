package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Shooter {

    // declare hardware
    public Hardware hardware;

    public DcMotorEx shootMotor;

    public Shooter(Hardware hardware) {
        // Define hardware
        this.hardware = hardware;

        // define shoot motors
        this.shootMotor = hardware.shootMotor;
    }

    public void setSpeed(double speed) {
        // Set the velocity
        shootMotor.setVelocity(speed, AngleUnit.DEGREES);
    }

    public void stop() {
        // Set the velocity to 0
        shootMotor.setVelocity(0);
    }

}
