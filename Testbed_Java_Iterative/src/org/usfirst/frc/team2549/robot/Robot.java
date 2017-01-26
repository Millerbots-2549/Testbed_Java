package org.usfirst.frc.team2549.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {

	Joystick ctrl;
	
	Talon motor1;
	Servo servo1;
	AnalogInput ultrasonic;

	CameraServer cameras;

	double us_value;
	double us_range;
	double us_ratio;
	
    public Robot() {
    	ctrl = new Joystick(0);
    	motor1 = new Talon(0);
    	ultrasonic = new AnalogInput(0);
    	
        cameras = CameraServer.getInstance();
        cameras.setQuality(50);
        cameras.startAutomaticCapture("cam1");
        
    	us_ratio = 250;
    }

    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {        	
        	motor1.set(ctrl.getRawAxis(1));
        	
        	if(ctrl.getRawButton(5))
        		servo1.set(.35);
        	if(ctrl.getRawButton(6))
        		servo1.set(1);
        	
        	us_value = ultrasonic.getValue();
        	us_range = us_value / us_ratio;
        	
        	SmartDashboard.putNumber("Ultrasonic", us_range);
        }
    }
}
