package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.Robot;

public class Kicker {

    public Servo kickerServo;

    @Config("Servo Positions")
    public static class Constants {
        // speed mod
        public static double out = 120;
        public static double in = 0;

    }

    public Kicker(Robot robot) {
        // define intake motor
        kickerServo = robot.hardware.kickerServo;
        kickerServo.setDirection(Servo.Direction.FORWARD);
    }

    /**
     * Runs the servo in
     * @author Brantley
     */

    public void setPositionIn() {
        kickerServo.setPosition(Constants.in);
    }

    public void setPositionOut() {
        kickerServo.setPosition(Constants.out);
    }
}
