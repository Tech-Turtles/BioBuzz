package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Transfer {
    public CRServo transferServo;
    public Robot robot;
    @Config("Transfer Speeds")
    public static class TransferSpeeds {
        // transfer speed here
        public static double speed = 1;
    }


    public Transfer(Robot robot) {
        // define transfer servo
        this.transferServo = robot.hardware.transferServo;
        // store robot
        this.robot = robot;
    }

    public void run() {
        // run the servo
        transferServo.setPower(TransferSpeeds.speed);
    }

    /**
     * Runs only if the shooter motor is close enough to it's target speed
     * @author Brantley
     */

    public void smartRun() {
        if (robot.shooter.getRPM() >= 0.99 * Shooter.ShooterConsts.speed) {
            run();
        } else {
            stop();
        }
    }

    public void stop() {
        // stop the servo
        transferServo.setPower(0);
    }
}
