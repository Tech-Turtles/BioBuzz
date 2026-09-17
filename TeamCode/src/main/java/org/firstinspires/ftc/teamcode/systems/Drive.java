package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Drive {

    // Declare hardware
    public Hardware hardware;

    public Drive(Hardware hardware) {
        // Define hardware
        this.hardware = hardware;
    }

    public void robotCentric(double y, double x, double rx) {

    }

    public void mecanum(double forward, double strafe, double twist) {
        // Get the raw power for each wheel
        double fl = forward + strafe + twist;
        double fr = forward - strafe - twist;
        double rl = forward - strafe + twist;
        double rr = forward + strafe - twist;

        // Get the scale factor
        double scale = Math.max(Math.abs(forward) + Math.abs(strafe) + Math.abs(twist), 1.0);

        // Set motor powers
        hardware.frontLeftDrive.setPower(fl / scale);
        hardware.frontRightDrive.setPower(fr / scale);
        hardware.rearLeftDrive.setPower(rl / scale);
        hardware.rearRightDrive.setPower(rr / scale);
    }
}
