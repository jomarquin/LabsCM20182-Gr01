package co.edu.udea.compumovil.gr01_20182.lab2.Utilities;

public class Utilities {

    /**Constantes de la tabla usuarios*/
    public static final String TABLE_USERS ="usuarios";
    public static final String FIELD_EMAIL="email";
    public static final String FIELD_NAME="Name";
    public static final String FIELD_PASSWORD="password";

    /**Constantes de la tabla platos*/
    public static final String TABLE_PLATES ="platos";
    public static final String FIELD_P_NAME="nombre";
    public static final String FIELD_P_TYPE="tipo";
    public static final String FIELD_P_PRICE="precio";
    public static final String FIELD_P_TIME="tiempo_preparacion";

    /**Constantes de la tabla bebidas*/
    public static final String TABLE_DRINKS ="bebidas";
    public static final String FIELD_D_NAME="nombre";
    public static final String FIELD_D_PRICE="precio";

    public static final String CREATE_TABLE_USER="CREATE TABLE "+ TABLE_USERS +" ("+FIELD_EMAIL+" TEXT, "+FIELD_NAME+" TEXT, "+FIELD_PASSWORD+" TEXT)";
    public static final String CREATE_TABLE_PLATES="CREATE TABLE "+ TABLE_PLATES +" ("+FIELD_P_NAME+" TEXT, "+FIELD_P_TYPE+" TEXT, "+FIELD_P_PRICE+" TEXT, "+FIELD_P_TIME+" TEXT)";
    public static final String CREATE_TABLE_DRINKS="CREATE TABLE "+ TABLE_DRINKS +" ("+FIELD_D_NAME+" TEXT, "+FIELD_D_PRICE+" TEXT)";

}
