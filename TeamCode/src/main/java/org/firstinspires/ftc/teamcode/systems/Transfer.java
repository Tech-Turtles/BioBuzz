package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Transfer {

    public CRServo firstTransferServo;
    public CRServo secondTransferServo;
    @Config("Transfer Speeds")
    public static class TransferSpeeds {
        // transfer speed here
        public static double speed = 1;
    }


    public Transfer(Robot robot) {
        // define transfer servo
        this.firstTransferServo = robot.hardware.firstTransferServo;
        this.secondTransferServo = robot.hardware.secondTransferServo;
    }

    public void runFirst() {
        // run the servo
        //firstTransferServo.setPower(-TransferSpeeds.speed);
    }

    public void runSecond() {
        // run the servo
        secondTransferServo.setPower(TransferSpeeds.speed);
    }

    public void stop() {
        // stop the servo
        firstTransferServo.setPower(0);
        secondTransferServo.setPower(0);
    }
}
