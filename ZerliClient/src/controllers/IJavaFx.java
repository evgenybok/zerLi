package controllers;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public interface IJavaFx {

	public void javaFxmlCustomer(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlWorker(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlDelivery(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlManager(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlCEO(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlSpecialist(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlService(MouseEvent event, FXMLLoader loader) throws IOException;

	public void javaFxmlMarketing(MouseEvent event, FXMLLoader loader) throws IOException;

	// public void javaFxmlUser(MouseEvent event, FXMLLoader loader) throws
	// IOException;
	public void javaFxmlNotFound();

	void javaFxmlAlreadyLogged();

	void javaFxmlEmptyFields();

	public void setDetails();
}
