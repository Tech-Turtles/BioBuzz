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

    double coeff;

    public static class Constants {
        @Config("Park Autonomous - Park Constants")
        public static class Park {
            public static double x = -60;
            public static double y = 35;
        }
    }

    public MatchState matchState;

    @Override
    public void init() {
        // run parent
        super.init();
        // set matchstate
        matchState = MatchState.PARK;
    }

    @Override
    public void init_loop() {
        super.init_loop();

        if (alliance == Alliance.RED) {
            coeff = -1;
        } else if (alliance == Alliance.BLUE) {
            coeff = 1;
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

    public void park() {
        //

        dashboard();

        if (robot.drive.straightToXY(robot.position, new Pose2D(DistanceUnit.INCH, coeff * Constants.Park.x, coeff * Constants.Park.y, AngleUnit.RADIANS, 0))) {
            nextState();
        }
    }

    @Override
    public void nextState() {
        // reset timer
        timer.reset();
        // state machine
        switch (matchState) {
            case PARK:
                // update matchstate
                matchState = MatchState.OVER;
                // brk code
                break;
        }
    }
}
