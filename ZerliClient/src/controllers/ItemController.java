package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.Item;

import java.util.Objects;

public class ItemController {

    private Item item;

    @FXML
    private ImageView img;

    @FXML
    private Label nameLabel;

    @FXML
    private  Label priceLabel;




    public void setData(Item item) {

        this.item=item;
        priceLabel.setText("$"+item.getPrice());
        nameLabel.setText(item.getName());
        Image image = new Image((Objects.requireNonNull(getClass().getResourceAsStream(item.getImgSrc()))));
        img.setImage(image);

    }


}
