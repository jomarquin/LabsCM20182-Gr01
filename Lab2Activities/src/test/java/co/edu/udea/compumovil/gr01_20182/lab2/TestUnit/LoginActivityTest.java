package co.edu.udea.compumovil.gr01_20182.lab2.TestUnit;

import android.widget.EditText;

import org.junit.Test;

import co.edu.udea.compumovil.gr01_20182.lab2.LoginActivity;
import co.edu.udea.compumovil.gr01_20182.lab2.R;

import static org.junit.Assert.*;

public class LoginActivityTest {

    EditText fieldEmail;
    @Test
    public void loginTest() throws Exception{
        fieldEmail.setText("jomarquin@gmail.com");
        LoginActivity loginUser = new LoginActivity();
        //assertArrayEquals(true, );
    }

}