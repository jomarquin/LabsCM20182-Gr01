package co.edu.udea.compumovil.gr01_20182.lab4.TestUnit;

import android.widget.EditText;

import org.junit.Test;

import co.edu.udea.compumovil.gr01_20182.lab4.LoginActivity;

public class LoginActivityTest {

    EditText fieldEmail;
    @Test
    public void loginTest() throws Exception{
        fieldEmail.setText("jomarquin@gmail.com");
        LoginActivity loginUser = new LoginActivity();
        //assertArrayEquals(true, );
    }

}