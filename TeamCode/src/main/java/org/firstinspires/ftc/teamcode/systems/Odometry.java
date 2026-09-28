package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class Odometry {
    // Define pinpoint
    public GoBildaPinpointDriver pinpoint;
    public Odometry(Robot robot) {
        // Define the pinpoint
        pinpoint = robot.hardware.pinpoint;
        // Set the position of the pinpoint
        pinpoint.setPosition(robot.start);
    }

    public Pose2D position() {
        // Update the pinpoint
        pinpoint.update();
        // Send back the pinpoint's position
        return (pinpoint.getPosition());
    }
}
