package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class AutonomousEx extends OpModeEx {

    // def timer
    public ElapsedTime timer;

    @Override
    public void init() {
        // run parent logic
        super.init();
        // def timer
        timer = new ElapsedTime();
    }

    @Override
    public void start() {
        // parent start
        super.start();
        // start the timer
        timer.reset();
    }

    @Override
    public void loop() {
        super.loop();
        // state machine
        stateMachine();
    }

    @Override
    public void stop() {
        super.stop();
        // Stop motors
        robot.stopAll();
    }

    public void stateMachine() {
        // pass for now
    }
}
