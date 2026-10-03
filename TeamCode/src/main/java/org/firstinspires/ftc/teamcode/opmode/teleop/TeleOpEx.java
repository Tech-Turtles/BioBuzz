package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

import java.util.ArrayList;
import java.util.List;

public class TeleOpEx extends OpModeEx {
    // setup match timer
    public ElapsedTime timer;

    // setup match state enum
    public enum MatchState {
        TELEOP,
        ENDGAME,
        OVER
    }

    // Define match state machine
    public MatchState matchState;

    // Times for each game stage
    protected final double teleopTime = 60;
    protected final double endgameTime = 60;

    // the time the match has been running
    protected double time = 0;

    @Override
    public void init() {
        // Run parent init logic
        super.init();
        // Initialize match timer
        timer = new ElapsedTime();
        // speak to user
        telemetry.addLine("Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        // run parent code
        super.start();
        // Now that match is starting, reset the timer
        timer.reset();
        // We are in the teleop section of the match
        matchState = MatchState.TELEOP;
        // speak to user
        telemetry.addLine("Started!");
        telemetry.update();
        // Run the teleop init code
        teleop();
    }

    @Override
    public void loop() {
        // run parent code
        super.loop();
        // Big state machine
        switch (matchState) {
            case TELEOP:
                // Make the timer a bit handier
                time = timer.seconds();
                // Run the logic for the teleop
                teleop_loop();
                // check if we get to go to endgame yet
                if (time >= teleopTime) {
                    matchState = MatchState.ENDGAME;
                    endgame();
                }
                // we don't want to start an infinite loop:
                break;

            case ENDGAME:
                // Set up time
                time = timer.seconds();
                // Run the endgame loop
                endgame_loop();
                // don't update to over
                // this is very important
                break;

            case OVER:
                // run the over loop now
                over_loop();
                // now break code
                break;
        }
        // Just in case robot program needs to be restarted during match, immediately set to endgame
        if (input.getEndgame()) {
            // set the match state to endgame
            matchState = MatchState.ENDGAME;
            // reset the timer
            timer.reset();
            // call endgame setup method
            endgame();
        }

        if (input.getResetPinpoint(gamepad1)) {
            robot.odometry.pinpoint.resetPosAndIMU();
            telemetry.addLine("Pinpoint reset!");
            telemetry.update();
        }
    }

    // The below methods are meant to be overridden by
    // the children of this class so that they don't
    // have to mess with stuff like loop()

    public void teleop() {
        // currently, do nothing
    }

    public void teleop_loop() {
        //
    }

    public void endgame() {
        // we can rumble the controllers here
        output.rumbleAll(1000);
    }

    public void endgame_loop() {
        //
    }

    public void over() {
        // do even more nothingness
    }

    public void over_loop() {
        robot.stopAll();
    }
}
