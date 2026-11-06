package org.firstinspires.ftc.teamcode.opmode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.control.ControllerOutput;
import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.control.ControllerInput;

import java.io.IOException;

public class OpModeEx extends OpMode {

    // Declare hardware
    public Robot robot;

    // Declare controller handling sys
    public ControllerInput input;
    public ControllerOutput output;

    // Declare dashboard
    public FtcDashboard dashboard = FtcDashboard.getInstance();

    // enum for alliance
    public static enum Alliance {
        RED,
        BLUE
    }
    // var for storing alliance
    public Alliance alliance;

    // declare start pose
    public Pose2D setStart() throws IOException {
        // return the start position
        return (new Pose2D(DistanceUnit.MM, 0, 0, AngleUnit.RADIANS, 0));
    }

    @Override
    public void init() {
        // Define robot
        try {
            robot = new Robot(hardwareMap, setStart());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        input = new ControllerInput(gamepad1, gamepad2);
        output = new ControllerOutput(gamepad1, gamepad2);
        // def alliance to be blue
        alliance = Alliance.BLUE;
    }

    @Override
    public void init_loop() {
        // now alliance selecting block
        if (input.getRedAlliance(input.gamepad1)||input.getRedAlliance(input.gamepad2)) {
            // set alliance
            alliance = Alliance.RED;
            // rumble controllers
            output.rumbleAll(1);
        }
        if (input.getBlueAlliance(input.gamepad1)||input.getBlueAlliance(input.gamepad2)) {
            alliance = Alliance.BLUE;
            // rumble controllers
            output.rumbleAll(1);
        }
    }

    @Override
    public void start() {
        // pass
    }

    @Override
    public void loop() {
        // Define position
        robot.position = robot.odometry.getPosition(robot.position);
        // Call dashboard method
        dashboard();
    }

    // dashboard function, now it just displays the robot's position
    public void dashboard() {
        // create the stuff
        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();

        // get the odometry data
        double x = robot.position.getX(DistanceUnit.INCH);
        double y = robot.position.getY(DistanceUnit.INCH);
        double heading = robot.position.getHeading(AngleUnit.RADIANS);

        // get arrow x and y
        double robotRadius = 9;
        double arrowX = x + robotRadius * Math.cos(heading);
        double arrowY = y + robotRadius * Math.sin(heading);

        // draw the robot's vector on the field
        fieldOverlay.setStrokeWidth(1);
        fieldOverlay.setStroke("#3F51B5");
        fieldOverlay.strokeCircle(x, y, robotRadius);
        fieldOverlay.strokeLine(x, y, arrowX, arrowY);

        // send backet
        dashboard.sendTelemetryPacket(packet);
    }
}
