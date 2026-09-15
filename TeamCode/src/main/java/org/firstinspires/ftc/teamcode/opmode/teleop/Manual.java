package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotHardware;

@TeleOp
@Config
public class Manual extends RobotHardware {

    public static bool fieldCentric = false;

    @Override
    public void init() {
        super.init();

    }

    @Override
    public void init_loop() {
        super.init_loop();

    }

    @Override
    public void start() {
        super.start();

    }

    @Override
    public void loop() {
        super.loop();

        // Toggle between robot and field centric
        if (driver1.YOnce()) {
            fieldCentric = !fieldCentric;
        }

        // Reset yaw (heading)
        if (driver1.XOnce()) {
            imu.resetYaw();
        }

        // Cubic joystick inputs for smoother driving
        // +X = forward, +Y = left, +rotation = CCW
        double x = Math.pow(-driver1.left_stick_y, 3);
        double y = Math.pow(-driver1.left_stick_x, 3);
        double rx = Math.pow(driver1.right_stick_x, 3);

        double rotX = x;
        double rotY = y;

        // Update X & Y powers if field centric is enabled
        if (fieldCentric) {
            double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            rotX = x * Math.cos(-heading) - y * Math.sin(-heading);
            rotY = x * Math.sin(-heading) + y * Math.cos(-heading);
        }

        rotY *= 1.1; // Strafe correction

        // Get the maximum value of the combined X, Y, and rotation powers
        // Then scale by that value so that the wheels move at the proper ratio
        double denominator =
                Math.max(Math.abs(rotX) + Math.abs(rotY) + Math.abs(rx), 1);

        frontLeft.setPower((rotX + rotY + rx) / denominator);
        rearLeft.setPower((rotX - rotY + rx) / denominator);
        frontRight.setPower((rotX - rotY - rx) / denominator);
        rearRight.setPower((rotX + rotY - rx) / denominator);
    }
}
