package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.robot.Hardware;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.control.Input;

public class OpModeEx extends OpMode {

    // OPMODE DECLARATIONS
    // Declare hardware
    public Robot robot;
    // Declare input handling sys
    public Input input;

    // Declare units used for starting the robot
    public DistanceUnit unit = DistanceUnit.MM;
    public AngleUnit angleUnit = AngleUnit.RADIANS;

    // declare start pose
    public Pose2D setStart() {
        Pose2D start = new Pose2D(unit, 0, 0, angleUnit, 0);
        return (start);
    }
    @Override
    public void init() {
        // Define robot
        robot = new Robot(hardwareMap, setStart());
        input = new Input(gamepad1, gamepad2);
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
