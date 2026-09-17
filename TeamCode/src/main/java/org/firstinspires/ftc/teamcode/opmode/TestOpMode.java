package org.firstinspires.ftc.teamcode.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Test OpMode")
public class TestOpMode extends OpModeEx {

    @Override
    public void init() {
        super.init();
        telemetry.addData("Status", "Initialized!");
        telemetry.update();
    }

    @Override
    public void start() {
        super.start();
        telemetry.addData("Status", "Started!");
        telemetry.update();
    }

    @Override
    public void loop() {
        super.loop();
        telemetry.addData("Odometry", pinpointReadout.toString());
    }
}
