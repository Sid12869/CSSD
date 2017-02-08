/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment2;

import java.util.ArrayList;

/**
 *
 * @author Andy
 */
public class FieldList extends ArrayList<Field>
{    
    public FieldList()
    {
        super();
    }
    
    public void addField(Field aField)
    {
        super.add(aField);
    }
    
    public void removeField(Field aField)
    {
        super.remove(aField);
    }
}
