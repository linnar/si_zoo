/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *Trida obsahujici prikazy/casti prikazu pro vkladani/vybirani dat do/z databaze.
 * @author Petr
 */
public class SQLBuilder {
    
    public static String buildAnimalSelect(int id,String chip,String birth,String specie,String name){
        
        String cId;
        String cChip;
        String cBirth;
        String cSpecie;
        String cName;
        
        if(id<0){
            cId = PreparedStatements.TABLE_ANIMAL_ID;
        }else{
            cId = ""+id;
        }
        
        if(chip==null || chip.equals("")){
            cChip = PreparedStatements.TABLE_ANIMAL_CHIP;
        }else{
            cChip = "'"+chip+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = PreparedStatements.TABLE_ANIMAL_BIRTH;
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(specie==null || specie.equals("")){
            cSpecie = PreparedStatements.TABLE_ANIMAL_SPECIE;
        }else{
            cSpecie = "'"+specie+"'";
        }
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_ANIMAL_NAME;
        }else{
            cName = "'"+name+"'";
        }
        return String.format(PreparedStatements.SELECT_ANIMAL,cId,cChip,cBirth,cSpecie,cName);
    }
    public static String buildAnimalDelete(int id){
        
        String cId;
        
        if(id<0){
            cId = PreparedStatements.TABLE_ANIMAL_ID;
        }else{
            cId = ""+id;
        }
        return String.format(PreparedStatements.DELETE_ANIMAL,cId);
    }
    public static String buildAnimalInsert(int id,String chip,String birth,String specie,String name){
        String cId;
        String cChip;
        String cBirth;
        String cSpecie;
        String cName;
        
        cId=""+id;
        
        if(chip==null || chip.equals("")){
            cChip = "''";
        }else{
            cChip = "'"+chip+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = "''";
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(specie==null || specie.equals("")){
            cSpecie = "''";
        }else{
            cSpecie = "'"+specie+"'";
        }
        
        if(name==null || name.equals("")){
            cName = "''";
        }else{
            cName = "'"+name+"'";
        }
        
        return String.format(PreparedStatements.INSERT_ANIMAL,cId,cChip,cBirth,cSpecie,cName);
    }
    public static String buildAnimalUpdate(int id,String chip,String birth,String specie,String name){
        
        String cId;
        String cChip;
        String cBirth;
        String cSpecie;
        String cName;
            
        cId = ""+id;
        
        if(chip==null || chip.equals("")){
            cChip = PreparedStatements.TABLE_ANIMAL_CHIP;
        }else{
            cChip = "'"+chip+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = PreparedStatements.TABLE_ANIMAL_BIRTH;
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(specie==null || specie.equals("")){
            cSpecie = PreparedStatements.TABLE_ANIMAL_SPECIE;
        }else{
            cSpecie = "'"+specie+"'";
        }
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_ANIMAL_NAME;
        }else{
            cName = "'"+name+"'";
        }
        
        return String.format(PreparedStatements.UPDATE_ANIMAL,cChip,cBirth,cSpecie,cName,cId);
    }
    
    public static String buildStaffSelect(int id,String personalId,String name,String lastname,String birth,String contact){
        String cId;
        String cPersonalId;
        String cName;
        String cLastname;
        String cBirth;
        String cContact;
        
        if(id<0){
            cId = PreparedStatements.TABLE_STAFF_ID;
        }else{
            cId = ""+id;
        }
        
        if(personalId==null || personalId.equals("")){
            cPersonalId = PreparedStatements.TABLE_STAFF_PERSONAL_ID;
        }else{
            cPersonalId = "'"+personalId+"'";
        }
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_STAFF_NAME;
        }else{
            cName = "'"+name+"'";
        }
        
        if(lastname==null || lastname.equals("")){
            cLastname = PreparedStatements.TABLE_STAFF_LASTNAME;
        }else{
            cLastname = "'"+lastname+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = PreparedStatements.TABLE_STAFF_BIRTH;
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(contact==null || contact.equals("")){
            cContact = PreparedStatements.TABLE_STAFF_CONTACT;
        }else{
            cContact = "'"+contact+"'";
        }
        return String.format(PreparedStatements.SELECT_STAFF,cId,cPersonalId,cName,cLastname,cBirth,cContact);
    }
    public static String buildStaffDelete(int id){
        
        String cId;
        
        if(id<0){
            cId = PreparedStatements.TABLE_STAFF_ID;
        }else{
            cId = ""+id;
        }
        return String.format(PreparedStatements.DELETE_STAFF,cId);
    }
    public static String buildStaffInsert(int id,String personalId,String name,String lastname,String birth,String contact){
        String cId;
        String cPersonalId;
        String cName;
        String cLastname;
        String cBirth;
        String cContact;
        
        cId=""+id;
        
        if(personalId==null || personalId.equals("")){
            cPersonalId = "''";
        }else{
            cPersonalId = "'"+personalId+"'";
        }
        
        if(name==null || name.equals("")){
            cName = "''";
        }else{
            cName = "'"+name+"'";
        }
        
        if(lastname==null || lastname.equals("")){
            cLastname = "''";
        }else{
            cLastname = "'"+lastname+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = "''";
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(contact==null || contact.equals("")){
            cContact = "''";
        }else{
            cContact = "'"+contact+"'";
        }
        return String.format(PreparedStatements.INSERT_STAFF,cId,cPersonalId,cName,cLastname,cBirth, cContact);
    }
    public static String buildStaffUpdate(int id,String personalId,String name,String lastname,String birth,String contact){
        
        String cId;
        String cPersonalId;
        String cName;
        String cLastname;
        String cBirth;
        String cContact;
        
        
        cId = ""+id;
        
        if(personalId==null || personalId.equals("")){
            cPersonalId = PreparedStatements.TABLE_STAFF_PERSONAL_ID;
        }else{
            cPersonalId = "'"+personalId+"'";
        }
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_STAFF_NAME;
        }else{
            cName = "'"+name+"'";
        }
        
        if(lastname==null || lastname.equals("")){
            cLastname = PreparedStatements.TABLE_STAFF_LASTNAME;
        }else{
            cLastname = "'"+lastname+"'";
        }
        
        if(birth==null || birth.equals("")){
            cBirth = PreparedStatements.TABLE_STAFF_BIRTH;
        }else{
            cBirth = "'"+birth+"'";
        }
        
        if(contact==null || contact.equals("")){
            cContact = PreparedStatements.TABLE_STAFF_CONTACT;
        }else{
            cContact = "'"+contact+"'";
        }
        
        return String.format(PreparedStatements.UPDATE_STAFF,cPersonalId,cName,cLastname,cBirth,cContact,cId);
    }
    
    public static String buildFeedSelect(int id,String name,String amount,String minimum){
        
        String cId;
        String cName;
        String cAmount;
        String cMinimum;
        
        if(id<0){
            cId = PreparedStatements.TABLE_FEED_ID;
        }else{
            cId = ""+id;
        }
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_FEED_NAME;
        }else{
            cName = "'"+name+"'";
        }
        
        if(amount==null || amount.equals("") || Integer.valueOf(amount)<0){
            cAmount = PreparedStatements.TABLE_FEED_AMOUNT;
        }else{
            cAmount = ""+amount;
        }
        
        if(minimum==null || minimum.equals("") || Integer.valueOf(minimum)<0){
            cMinimum = PreparedStatements.TABLE_FEED_MINIMUM;
        }else{
            cMinimum = ""+minimum;
        }
        return String.format(PreparedStatements.SELECT_FEED,cId,cName,cAmount,cMinimum);
    }
    public static String buildFeedDelete(int id){
        
        String cId;
        
        if(id<0){
            cId = PreparedStatements.TABLE_FEED_ID;
        }else{
            cId = ""+id;
        }
        return String.format(PreparedStatements.DELETE_FEED,cId);
    }
    public static String buildFeedInsert(int id,String name,String amount,String minimum){
        
        String cId;
        String cName;
        String cAmount;
        String cMinimum;
        
        
        cId = ""+id;
        
        if(name==null || name.equals("")){
            cName = "''";
        }else{
            cName = "'"+name+"'";
        }
        
        if(amount==null || amount.equals("") || Integer.valueOf(amount)<0){
            cAmount = ""+0;
        }else{
            cAmount = ""+amount;
        }
        
        if(minimum==null || minimum.equals("") || Integer.valueOf(minimum)<0){
            cMinimum = ""+0;
        }else{
            cMinimum = ""+minimum;
        }
        return String.format(PreparedStatements.INSERT_FEED,cId,cName,cAmount,cMinimum);
    }
    public static String buildFeedUpdate(int id,String name,String amount,String minimum){
        
        String cId;
        String cName;
        String cAmount;
        String cMinimum;
        
        cId = ""+id;
        
        if(name==null || name.equals("")){
            cName = PreparedStatements.TABLE_FEED_NAME;
        }else{
            cName = "'"+name+"'";
        }
        
        if(amount==null || amount.equals("") || Integer.valueOf(amount)<0){
            cAmount = PreparedStatements.TABLE_FEED_AMOUNT;
        }else{
            cAmount = ""+amount;
        }
        
        if(minimum==null || minimum.equals("") || Integer.valueOf(minimum)<0){
            cMinimum = PreparedStatements.TABLE_FEED_MINIMUM;
        }else{
            cMinimum = ""+minimum;
        }
        return String.format(PreparedStatements.UPDATE_FEED,cName,cAmount,cMinimum,cId);
    }

}
