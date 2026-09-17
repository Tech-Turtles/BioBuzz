package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Drive {

    // Declare hardware
    public Hardware hardware;

    // Set target velocity
    private static final double targetVelocity = 100;

    public Drive(Hardware hardware) {
        // Define hardware
        this.hardware = hardware;
    }

    public void mecanum(double drive, double strafe, double twist) {

    }
}
