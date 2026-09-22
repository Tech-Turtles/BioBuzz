package org.firstinspires.ftc.teamcode.opmode.teleop;

import org.firstinspires.ftc.teamcode.opmode.OpModeEx;

public class TeleOpPractice extends OpModeEx {

    @Override
    public void init() {
        // Run parent init logic
        super.init();
        practice_init();
        // Initialize match timer
        // speak to user
        telemetry.addLine("Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        // speak to user
        telemetry.addLine("Started!");
        telemetry.update();
        // Run the teleop init code
        practice_start();
    }

    @Override
    public void loop() {
        super.loop();
        practice_loop();
    }

    // The below methods are meant to be overridden by
    // the children of this class so that they don't
    // have to mess with stuff like loop()

    public void practice_init() {
        // currently, do nothing
    }

    public void practice_start() {
        // nothing
    }

    public void practice_loop() {
        // currently, do nothing
    }
}
