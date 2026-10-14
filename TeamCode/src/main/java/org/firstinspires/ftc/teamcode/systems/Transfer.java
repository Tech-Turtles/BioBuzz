package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Transfer {

    public CRServo transferServo;

    @Config("Transfer Speeds")
    public static class TransferSpeeds {
        // transfer speed here
        public static double speed = 1;
    }


    public Transfer(Robot robot) {
        // define transfer servo
        transferServo = robot.hardware.transferServo;
    }

    public void run() {
        // run the servo
        transferServo.setPower(TransferSpeeds.speed);
    }

    public void stop() {
        // stop the servo
        transferServo.setPower(TransferSpeeds.speed);
    }
}
