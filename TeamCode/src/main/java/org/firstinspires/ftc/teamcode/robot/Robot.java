package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.systems.Drive;
import org.firstinspires.ftc.teamcode.systems.Intake;
import org.firstinspires.ftc.teamcode.systems.Odometry;
import org.firstinspires.ftc.teamcode.systems.Shooter;

public class Robot {
    // robot variable declarations
    public Hardware hardware;

    // system variable declarations
    public Drive drive;
    public Odometry odometry;
    public Intake intake;
    public Shooter shooter;

    // declare Pose2D for start
    public Pose2D start;

    public Robot(HardwareMap map, Pose2D start) {
        // define start
        this.start = start;

        // robot variable definitions
        this.hardware = new Hardware(map);
        // system variable declarations
        this.odometry = new Odometry(this);
        this.drive = new Drive(this);
        this.intake = new Intake(this);
        this.shooter = new Shooter(this);
    }

    /**
     * @author Brantley
     * Stops the wheels and the intake. To be run repeatedly.
     */
    public void stopAll() {
        drive.stop();
        intake.stop();
        shooter.stop();
    }
}
