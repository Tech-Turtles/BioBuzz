package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.control.ControllerOutput;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.control.ControllerInput;

public class OpModeEx extends OpMode {

    // OPMODE DECLARATIONS
    // Declare hardware
    public Robot robot;
    // Declare controller handling sys
    public ControllerInput input;
    public ControllerOutput output;

    // var for storing odometry to avoid having to call robot.odometry.getPosition() multiple times
    public Pose2D position;

    // Declare dashboard
    public FtcDashboard dashboard = FtcDashboard.getInstance();

    // declare start pose
    public Pose2D setStart() {
        // return the start position
        return (new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.RADIANS, 0));
    }
    @Override
    public void init() {
        // Define robot
        robot = new Robot(hardwareMap, setStart());
        input = new ControllerInput(gamepad1, gamepad2);
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
        // Define position
        position = robot.odometry.position();
    }
}
