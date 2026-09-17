package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;

public class Odometry {

    // Define pinpoint
    public GoBildaPinpointDriver pinpoint;
    public Odometry(Hardware hardware, Pose2D start) {
        // Define the pinpoint
        pinpoint = hardware.pinpoint;
        // Set the position of the pinpoint
        pinpoint.setPosition(start);
    }
}
