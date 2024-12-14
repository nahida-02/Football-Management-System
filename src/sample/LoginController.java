package sample;
import Network.*;
import dto.*;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.SocketException;


public class LoginController {
    private NetworkUtil networkUtil;
    private Main main;

    @FXML
    private TextField UserText;

    @FXML
    private TextField passwordText;



    @FXML
    private Button loginButton;



    @FXML
    void loginAction(ActionEvent event) {

   new Thread(()->{
        String userName = UserText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        main.setCl(userName);
        try {

            main.getNetworkUtil().write(loginDTO);
            GetListMessage getListMessage = new GetListMessage();
            getListMessage.setName(userName);
            networkUtil.write(getListMessage);

        }catch(SocketException e){}
        catch (IOException e) {
            e.printStackTrace();
        }}).start();
    }


    void setMain(Main main) {
        this.main = main;
    }

    public void setNetworkutil(NetworkUtil networkUtil) {
        this.networkUtil=networkUtil;
    }
}
