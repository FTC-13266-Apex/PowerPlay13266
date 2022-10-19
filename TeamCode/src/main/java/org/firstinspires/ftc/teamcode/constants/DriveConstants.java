package org.firstinspires.ftc.teamcode.constants;


import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

/**
 * Constants shared between multiple drive types.
 *
 * Constants generated by LearnRoadRunner.com/drive-constants
 *
 * TODO: Tune or adjust the following constants to fit your robot. Note that the non-final
 * fields may also be edited through the dashboard (connect to the robot's WiFi network and
 * navigate to https://192.168.49.1:8080/dash). Make sure to save the values here after you
 * adjust them in the dashboard; **config variable changes don't persist between app restarts**.
 *
 * These are not the only parameters; some are located in the localizer classes, drive base classes,
 * and op modes themselves.
 */
//@Config
public class DriveConstants {
    public static Drivetrain drivetrain;
    public static Imu imu;
    public static Controller controller;
    public static Follower follower;
    // TODO: make all of thse non static pls pls pls
    public static class Drivetrain {
        public static LeftFront leftFront;
        public static LeftRear leftRear;
        public static RightFront rightFront;
        public static RightRear rightRear;
        public static Value value;

        public static class LeftFront {
            public static Hardware hardware = new Hardware();

            public static class Hardware {
                public String ID            = "leftFront";
                public boolean REVERSED     = true;
            }
        }
        public static class LeftRear {
            public static Hardware hardware = new Hardware();

            public static class Hardware {
                public String ID            = "leftRear";
                public boolean REVERSED     = true;
            }
        }
        public static class RightFront {
            public static Hardware hardware = new Hardware();

            public static class Hardware {
                public String ID            = "rightFront";
                public boolean REVERSED     = false;
            }
        }
        public static class RightRear {
            public static Hardware hardware = new Hardware();

            public static class Hardware {
                public String ID            = "rightRear";
                public boolean REVERSED     = false;

            }
        }
        public static class Value {
            /**
             * These are physical constants that can be determined from your robot (including the track
             * width; it will be tune empirically later although a rough estimate is important). Users are
             * free to chose whichever linear distance unit they would like so long as it is consistently
             * used. The default values were selected with inches in mind. Road runner uses radians for
             * angular distances although most angular parameters are wrapped in Math.toRadians() for
             * convenience. Make sure to exclude any gear ratio included in MOTOR_CONFIG from GEAR_RATIO.
             */
            public static double WHEEL_RADIUS = 1.8898; // in
            public static double GEAR_RATIO   = 1; // output (wheel) speed / input (motor) speed
            public static double TRACK_WIDTH  = 8.3; // in

            /**
             * These are motor constants that should be listed online for your motors.
             */
            public static double TICKS_PER_REV = 384.539792388;
            public static double MAX_RPM       = 435;

            /**
             * These are Speeds that can be used to control the drivetrain when NOT using Localization,
             * Trajectory generation, and Path following
             */
            public static double TELEOP_SLOWER = 0.5;
            public static double TELEOP_NORMAL = .7;
            public static double TELEOP_SLOW = 0.5;
            public static boolean FINE_CONTROL = true;
            public static boolean FIELD_CENTRIC = true;
        }
    }
    public static class Imu {
        public static class Hardware {
            public static String ID            = "imu";
        }

    }
    public static class Controller {
        /**
         * Set RUN_USING_ENCODER to true to enable built-in hub velocity control using drive encoders.
         * Set this flag to false if drive encoders are not present and an alternative localization
         * method is in use (e.g., tracking wheels).
         *
         * If using the built-in motor velocity PID, update MOTOR_VELO_PID with the tuned coefficients
         * from DriveVelocityPIDTuner.
         */
        public static boolean RUN_USING_BUILT_IN_CONTROLLER = false;
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(0, 0, 0,
                getMotorVelocityF(Drivetrain.Value.MAX_RPM / 60 * Drivetrain.Value.TICKS_PER_REV));
        /**
         * These are the feedforward parameters used to model the drive motor behavior. If you are using
         * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
         * motor encoders or have elected not to use them for velocity control, these values should be
         * empirically tuned.
         */
        public static double kV      = 0.0165;
        public static double kA      = 0.003;
        public static double kStatic = 0.02;

        /**
         * <p>
         * These values are used to generate the trajectories for you robot. To ensure proper operation,
         * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
         * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
         * small and gradually increase them later after everything is working. All distance units are
         * inches.
         * </p>
         * <p>
         * <b>Note from LearnRoadRunner.com:</b>
         * </p>
         * The velocity and acceleration constraints were calculated based on the following equation:<br>
         * ((MAX_RPM / 60) * GEAR_RATIO * WHEEL_RADIUS * 2 * Math.PI) * 0.85<br>
         * Resulting in 52.48291908330528 in/s.<br>
         * </p>
         * <p>
         * This is only 85% of the theoretical maximum velocity of the bot, following the recommendation above.<br>
         * This is capped at 85% because there are a number of variables that will prevent your bot from actually
         * reaching this maximum velocity: voltage dropping over the game, bot weight, general mechanical inefficiencies, etc.
         * However, you can push this higher yourself if you'd like. Perhaps raise it to 90-95% of the theoretically
         * max velocity. The theoretically maximum velocity is 61.74461068624151 in/s.
         * Just make sure that your bot can actually reach this maximum velocity. Path following will be detrimentally
         * affected if it is aiming for a velocity not actually possible.
         * </p>
         * <p>
         * The maximum acceleration is somewhat arbitrary and it is recommended that you tweak this yourself based on
         * actual testing. Just set it at a reasonable value and keep increasing until your path following starts
         * to degrade. As of now, it simply mirrors the velocity, resulting in 52.48291908330528 in/s/s
         *
         * Maximum Angular Velocity is calculated as: maximum velocity / trackWidth * (180 / Math.PI) but capped at 360°/s.
         * You are free to raise this on your own if you would like. It is best determined through experimentation.
         * </p>
         */
        public static double MAX_VEL       = 40;
        public static double MAX_ACCEL     = 50; // at 70, bot lifts up when decelerating so it cant go much higher than this this is about as high as it can be
        public static double MAX_ANG_VEL   = Math.toRadians(200);
        public static double MAX_ANG_ACCEL = Math.toRadians(200);


    }
    public static class Follower {
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(8, 0, 0);

        public static double LATERAL_MULTIPLIER = 1; // this is used for odometry things

        // TODO: figure out what weigfhted power in Tank/Mechanum drive for RR is used for
        // When using set weighted power, these doubles are used
        public static double VX_WEIGHT = 1; // used with drivePower.getX
        public static double VY_WEIGHT = 1; // used with drivePower.getX
        public static double OMEGA_WEIGHT = 1; // used with drivePower.getHeading

        public static double TIMEOUT = 0.5;

        // TODO: Find pit of tjos actually works
        public static Pose2d INITIAL_POS_MAYBE = new Pose2d(0.5, 0.5, Math.toRadians(5.0));
        public static double TURN_TIMEOUT = 0.5;
    }












    public static double encoderTicksToInches(double ticks) {
        return Drivetrain.Value.WHEEL_RADIUS * 2 * Math.PI * Drivetrain.Value.GEAR_RATIO * ticks / Drivetrain.Value.TICKS_PER_REV;
    }

    public static double rpmToVelocity(double rpm) {
        return rpm * Drivetrain.Value.GEAR_RATIO * 2 * Math.PI * Drivetrain.Value.WHEEL_RADIUS / 60.0;
    }

    public static double getMotorVelocityF(double ticksPerSecond) {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / ticksPerSecond;
    }
}