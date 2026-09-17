package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Drive {

    // Declare hardware
    public Hardware hardware;

    // Set x movement speed
    private static final double xSpeed = 1.0;
    // Set y movement speed
    private static final double ySpeed = 1.0;
    // Set rotation speed (slower often feels more natural)
    private static final double rotSpeed = 0.4;

    public Drive(Hardware hardware) {
        // Define hardware
        this.hardware = hardware;
    }

    public void robotCentric(double y, double x, double rx) {

    }
}
