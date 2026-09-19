package org.firstinspires.ftc.teamcode.opmode.autonomous;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class AutonomousEx extends OpModeEx {

    @Override
    public void init() {
        // run parent logic
        super.init();
    }

    @Override
    public void start() {
        // parent start
        super.start();
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
        robot.drive.brake();
    }

    public void stateMachine() {
        // pass for now
    }
}
