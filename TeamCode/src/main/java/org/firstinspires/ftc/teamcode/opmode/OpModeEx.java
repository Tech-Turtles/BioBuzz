package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;

public class OpModeEx extends OpMode {

    // OPMODE DECLARATIONS
    // Declare hardware
    public Robot robot;

    // declare start pose
    public Pose2D start = new Pose2D(DistanceUnit.METER, 0, 0, AngleUnit.RADIANS, 0);
    @Override
    public void init() {
        // Define robot
        robot = new Robot(hardwareMap, start);
    }

    @Override
    public void init_loop() {
        // pass
    }

    @Override
    public void start() {
        // pass
    }

    @Override
    public void loop() {

    }
}
