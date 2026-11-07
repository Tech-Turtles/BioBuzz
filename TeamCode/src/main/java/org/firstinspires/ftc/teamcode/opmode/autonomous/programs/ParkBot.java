package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.firstinspires.ftc.teamcode.utility.commands.DriveCommand;


@Autonomous(name = "ParkBot", group = "Bot")
public class ParkBot extends AutonomousEx {

    public enum MatchState {
        PARK,
        OVER
    }


    @Override
    public Pose2D setStart() {
        return (new Pose2D(DistanceUnit.INCH, -36, 59, AngleUnit.DEGREES, 90));
    }

    public static class Constants {
        @Config("Park Autonomous - Park Constants")
        public static class Park {
            public static double x = -60;
            public static double y = 30;
        }
    }

    double coeff;

    public MatchState matchState;

    @Override
    public void init() {
        // run parent
        super.init();
        // set matchstate
        matchState = MatchState.PARK;

        coeff = 1;
        if (alliance == Alliance.RED) {
            coeff = -1;
        }
    }

    @Override
    public void stateMachine() {
        switch (matchState) {
            case PARK:
                park();
                break;

            case OVER:
                stop();
                break;
        }
    }




    @Override
    public void nextState() {
        // state machine
        switch (matchState) {
            case PARK:
                // update matchstate
                matchState = MatchState.OVER;
                // brk code
                break;
        }
    }

    public void park() {
        dashboard();

        if (robot.drive.straightToXY(robot.position, new Pose2D(DistanceUnit.INCH, coeff * ShootBot.Constants.Position.x, coeff * ShootBot.Constants.Position.y, AngleUnit.RADIANS, 0))) {
            nextState();
        }
    }
}
