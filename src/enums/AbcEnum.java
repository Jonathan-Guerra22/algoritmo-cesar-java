/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enums;

/**
 *
 * @author JonathanPortatil
 */
public enum AbcEnum {
    
    MAYUSC("ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"),
    MINUSC("abcdefghijklmnñopqrstuvwxyz")
    ;

    private AbcEnum(String valueEnum) {
        this.valueEnum = valueEnum;
    }
    
    private String valueEnum;

    public String getValueEnum() {
        return valueEnum;
    }

}
