package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class AutonomousEx extends OpModeEx {
    // create timer object
    public ElapsedTime timer;
    // create object for time of autonomous match
    public double autonomousLength = 30;

    @Override
    public void init() {
        // run parent logic
        super.init();
        // create timer
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        // reset timer
        timer.reset();
    }

    @Override
    public void loop() {
        // state machine
        stateMachine();
        // brake if time is over
        if (timer.seconds() >= 30) {
            robot.drive.brake();
        }
    }

    public void stateMachine() {
        double time = timer.seconds();
        // pass for now
    }
}
