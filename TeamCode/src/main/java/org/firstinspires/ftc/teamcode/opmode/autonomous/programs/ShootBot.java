package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.firstinspires.ftc.teamcode.systems.Shooter;
import org.opencv.core.Mat;

import java.util.Arrays;
import java.util.List;


@Autonomous(name = "ShootBot", group = "Bot")
public class ShootBot extends AutonomousEx {


    // all matchstates for this auto
    public enum MatchState {
        SHOOT,
        PURGE,
        POSITION,
        PARK,
        OVER
    }

    public float timeAllowed = 30;


    // all constants for this auto, pay attention to naming convention "Auto name - Auto state"
    public static class Constants {
        @Config("Shoot Autonomous - Position Constants")
        public static class Position {
            public static double time = 2.4;
            public static double forward = 0;
            public static double strafe = -0.3;
            public static double twist = -0.2;
        }

        @Config("Shoot Autonomous - Shoot Constants")
        public static class Shoot {
            public static double time = 12;
        }

        @Config("Shoot Autonomous - Purge Constants")
        public static class Purge {
            public static double time = 2;
        }

        // TODO: Rearrange update auto states for ShootBot

        @Config("Shoot Autonomous - Park Constants")
        public static class Park {
            public static double time = 2;
            public static double forward = 0.4;
            public static double strafe = 0;
            public static double twist = 0;
        }
    }

    // to hold matchstate
    public MatchState matchState;

    // setup
    @Override
    public void init() {
        // run parent
        super.init();
        // assuming blue alliance
        // set matchstate
        matchState = MatchState.SHOOT;
    }

    // state handling
    @Override
    public void stateMachine() {
        switch (matchState) {
            case POSITION:
                if (timer.seconds() <= Constants.Position.time) {
                    position();
                } else {
                    nextState();
                }
                break;

            case PURGE:
                if (timer.seconds() <= Constants.Purge.time) {
                    purge();
                } else {
                    nextState();
                }

            case SHOOT:
                if (timer.seconds() <= Constants.Shoot.time) {
                    shoot();
                } else {
                    nextState();
                }
                break;

            case PARK:
                if (timer.seconds() <= Constants.Park.time) {
                    park();
                } else {
                    nextState();
                }
                break;

            case OVER:
                stop();
                break;
        }
        if (matchTimer.seconds() >= timeAllowed) {
            matchState = MatchState.OVER;
        }
    }

    @Override
    public void nextState() {
        // reset timer
        timer.reset();
        // stop all systems
        robot.stopAll();
        // state machine
        switch (matchState) {
            case POSITION:
                // set matchstate to next
                matchState = MatchState.PARK;
                // break code
                break;

            case SHOOT:
                // update matchstate
                matchState = MatchState.PURGE;
                // break code
                break;

            case PURGE:
                // up
                matchState = MatchState.POSITION;
                break;

            case PARK:
                // update matchstate
                matchState = MatchState.OVER;
                // brk code
                break;
        }
    }

    // actual action logic, not just states or values
    public void position() {
        robot.drive.robotCentric(Constants.Position.forward, Constants.Position.strafe, Constants.Position.twist);
        // dashboard
        dashboard();
    }

    // TODO: Make dashboard() a method of OpModeEx and have children override

    public void shoot() {
        // run intake
        robot.intake.intake();
        // run shooter
        robot.shooter.run();
        // smart run the transfer (only if shooter is up to speed)
        robot.transfer.smartRun();
        // dashboard
        dashboard();
    }

    public void park() {
        robot.drive.robotCentric(Constants.Park.forward, Constants.Park.strafe, Constants.Park.twist);
        // ds
        dashboard();
    }

    public void purge() {
        robot.intake.outtake();
    }

    // dashboard method
    public void dashboard() {
        // create the stuff
        TelemetryPacket packet = new TelemetryPacket();
        Canvas fieldOverlay = packet.fieldOverlay();

        // get the odometry data
        double x = position.getX(DistanceUnit.INCH);
        double y = position.getY(DistanceUnit.INCH);
        double heading = position.getHeading(AngleUnit.RADIANS);

        // get arrow x and y
        double robotRadius = 9;
        double arrowX = x + robotRadius * Math.cos(heading);
        double arrowY = y + robotRadius * Math.sin(heading);

        // draw the robot's vector on the field
        fieldOverlay.setStrokeWidth(1);
        fieldOverlay.setStroke("#3F51B5");
        fieldOverlay.strokeCircle(x, y, robotRadius);
        fieldOverlay.strokeLine(x, y, arrowX, arrowY);

        // numerical telemetry
        packet.put("x (in)", x);
        packet.put("y (in)", y);
        packet.put("heading (deg)", Math.toDegrees(heading));

        // shooter data
        packet.put("Shooter RPM", robot.shooter.getRPM());
        packet.put("Shooter Target", Shooter.ShooterConsts.speed);
        packet.put("Max RPM", robot.hardware.shooterMotor.getMotorType().getMaxRPM());

        telemetry.addData("Shooter RPM", robot.shooter.getRPM());
        telemetry.addData("Shooter Target", Shooter.ShooterConsts.speed);

        telemetry.addData("State", matchState);
        telemetry.addData("Timer", matchTimer);
        telemetry.addData("Alliance", alliance);

        packet.put("State", matchState);
        packet.put("Alliance", alliance);

        // send the telemetry packet
        dashboard.sendTelemetryPacket(packet);
    }
}


