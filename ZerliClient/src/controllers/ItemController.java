package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Item;

import java.util.Objects;

public class ItemController {


    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private  Label priceLabel;

    @FXML
    private Label serial;


    public void setData(Item item) {

        priceLabel.setText("\u20AA"+item.getPrice());
        nameLabel.setText(item.getName());
        Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        img.setImage(image);
       // img.setFitHeight(150);
       // img.setFitWidth(150);
        serial.setText(Integer.toString(item.getID()));
    }


}
