package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentScreenController {

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField Adress;

	    @FXML
	    private ComboBox<?> Area;

	    @FXML
	    private Button Back;

	    @FXML
	    private TextField CVV;

	    @FXML
	    private TextField CardDate;

	    @FXML
	    private TextField CardNum;

	    @FXML
	    private DatePicker Date;

	    @FXML
	    private Button Pay;

	    @FXML
	    private TextField Phone;

	    @FXML
	    private TextField ReciverName;

	    @FXML
	    private ComboBox<?> StoreName;

		@FXML
		private CheckBox DeliveryBox;

		@FXML
		private CheckBox PickUpBox;
		@FXML
		private ImageView BoxImg;

		@FXML
		private ImageView DeliveryImg;

		@FXML
		private ImageView MailImg;

		@FXML
		private ImageView Card1Img;

		@FXML
		private ImageView Card2Img;

		@FXML
		private ImageView BitCoinImg;
		@FXML
		private ImageView CheckoutImg;


	@FXML
    void btnBack(MouseEvent event) throws IOException {
		((Node) event.getSource()).getScene().getWindow().hide();
		Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/CartScreen.fxml")));
		Scene scene = new Scene(parent);
		Stage deliveryDetailsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		deliveryDetailsStage.setTitle("Shopping Cart");
		deliveryDetailsStage.setScene(scene);
		deliveryDetailsStage.show();
		deliveryDetailsStage.centerOnScreen();
    }

    @FXML
    void btnPay(MouseEvent event) {

    }

	@FXML
	void DeliveryBtn(MouseEvent event) {
		if(DeliveryBox.isSelected())
			PickUpBox.setDisable(true);
		else
			PickUpBox.setDisable(false);
	}

	@FXML
	void PickBtn(MouseEvent event) {

		if(PickUpBox.isSelected()) {
			DeliveryBox.setDisable(true);
			Adress.setDisable(true);
		}
		else{

			DeliveryBox.setDisable(false);
			Adress.setDisable(false);
		}
	}


    @FXML
    void initialize() {

		Image checkoutImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-card-payment-50.png")));
		CheckoutImg.setImage(checkoutImg);
		Image boxImg  = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-packing-50.png")));
		BoxImg.setImage(boxImg);
		Image mailImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-letterbox-50 (1).png")));
		MailImg.setImage(mailImg);
		Image car1Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-mastercard-48.png")));
		Card1Img.setImage(car1Img);
		Image card2Img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-visa-48.png")));
		Card2Img.setImage(card2Img);
		Image bitcoinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-bitcoin-48.png")));
		BitCoinImg.setImage(bitcoinImg);
		Image deliveryImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/icons8-deliver-food-50 (1).png")));
		DeliveryImg.setImage(deliveryImg);

    	assert Adress != null : "fx:id=\"Adress\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Area != null : "fx:id=\"Area\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Back != null : "fx:id=\"Back\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CVV != null : "fx:id=\"CVV\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CardDate != null : "fx:id=\"CardDate\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert CardNum != null : "fx:id=\"CardNum\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Date != null : "fx:id=\"Date\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Pay != null : "fx:id=\"Pay\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert Phone != null : "fx:id=\"Phone\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert ReciverName != null : "fx:id=\"ReciverName\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";
        assert StoreName != null : "fx:id=\"StoreName\" was not injected: check your FXML file 'PaymentScreenNew.fxml'.";

    }
}
