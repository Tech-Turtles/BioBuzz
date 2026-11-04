package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

public class MeepMeep {
    public static void main(String[] args) throws IOException {
        com.noahbres.meepmeep.MeepMeep meepMeep = new com.noahbres.meepmeep.MeepMeep(800);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(
                        120,
                        120,
                        Math.toRadians(180),
                        Math.toRadians(180),
                        15
                )
                .build();

        bot.runAction(bot.getDrive().actionBuilder(new Pose2d(-23+9, -70+9, Math.toRadians(-90)))
                .strafeToConstantHeading(new Vector2d(-40, -38))
                .strafeToConstantHeading(new Vector2d(-56, 32))
                .build()
        );

        BufferedImage fieldImage = ImageIO.read(
                Objects.requireNonNull(MeepMeep.class.getResourceAsStream("/biobuzz_field.png"))
        );

        meepMeep.setBackground(fieldImage)
                .setDarkMode(true)
                .setBackgroundAlpha(1.00f)
                .addEntity(bot)
                .start();
    }
}