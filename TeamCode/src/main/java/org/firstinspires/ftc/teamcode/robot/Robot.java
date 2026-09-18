package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Odometry;

public class Robot {
    // robot variable declarations
    public Hardware hardware;

    // system variable declarations
    public Drive drive;
    public Odometry odometry;
    public Robot(HardwareMap map, Pose2D start) {
        // robot variable definitions
        hardware = new Hardware(map);
        // system variable declarations
        odometry = new Odometry(hardware, start);
        drive = new Drive(hardware, odometry);
    }
}
