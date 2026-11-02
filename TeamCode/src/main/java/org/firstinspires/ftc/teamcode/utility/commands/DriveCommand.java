package org.firstinspires.ftc.teamcode.utility.commands;

public class DriveCommand {

    // f s t
    public double forward;
    public double strafe;
    public double twist;
    // speed
    public double speed;

    public DriveCommand(double forward, double strafe, double twist, double speed) {
        // def everything
        this.forward = forward;
        this.strafe = strafe;
        this.twist = twist;
        this.speed = speed;
    }

    public double getForward() {
        return forward * speed;
    }
    public double getStrafe() {
        return strafe * speed;
    }
    public double getTwist() {
        return twist * speed;
    }
}
