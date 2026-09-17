package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test OpMode")
public class TestOpMode extends OpModeEx {

    @Override
    public void init() {
        super.init();
        // Let users know as soon as the robot is initialized!
        telemetry.addLine("Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        // Let users know as soon as the robot starts!
        telemetry.addLine("Started!");
        telemetry.update();
    }
}
