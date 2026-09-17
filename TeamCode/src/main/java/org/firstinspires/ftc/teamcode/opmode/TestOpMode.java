package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test OpMode")
public class TestOpMode extends OpModeEx {

    @Override
    public void init() {
        super.init();
        // Let users know as soon as the robot is initialized!
        telemetry.addData("Status", "Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        // Let users know as soon as the robot starts!
        telemetry.addData("Status", "Started!");
        telemetry.update();
    }

    @Override
    public void loop() {
        super.loop();
        // Print human-friendly odometry readout to telemetry
        telemetry.addData("Odometry", pinpointReadout.toString());
    }
}
