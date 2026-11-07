package org.firstinspires.ftc.teamcode.opmode.autonomous.programs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.opmode.autonomous.AutonomousEx;
import org.firstinspires.ftc.teamcode.utility.commands.DriveCommand;


@Autonomous(name = "LastMinBot", group = "Bot")
public class LastMinBot extends AutonomousEx {

    public enum MatchState {
        PARK,
        OVER
    }

    public static class Constants {
        @Config("LastMin Autonomous - Park Constants")
        public static class Park {
            public static double time = 0.5;
            public static double forward = 0.07;
            public static double strafe = -0.2;
            public static double twist = 0.0;
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
    public void stateMachine() {
        switch (matchState) {
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
    }

    public void park() {
        DriveCommand command = new DriveCommand(Constants.Park.forward, Constants.Park.strafe, Constants.Park.twist, 1.0);
        robot.drive.robotCentric(command);
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
