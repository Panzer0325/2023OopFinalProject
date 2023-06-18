/**
 * 
 */
/**
 * @author linchia-ho
 *
 */
module FinalProject {
	//requires json.simple;
	requires com.fasterxml.jackson.databind;
	opens evRentingPlatform to com.fasterxml.jackson.databind;
	exports TestingPackage;
	exports evRentingPlatform;
	exports evRentingPlatformGUI;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.web;
	requires javafx.swing;
}