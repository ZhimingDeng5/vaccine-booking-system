package com.example.Flying_Tiger.Class;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class UnitOfWorkOfRecipient {
    private static ThreadLocal current=new ThreadLocal();
    private List<Recipient> newRecipients=new ArrayList<Recipient> ();
    private List<Recipient> dirtyRecipients=new ArrayList<Recipient> ();
    private Set<Long> deletedRecipients=new HashSet<Long>();
    public void registerNew(Recipient recipient) {
        if(!dirtyRecipients.contains(recipient)&&!deletedRecipients.contains(recipient)
        &&!newRecipients.contains(recipient))
         newRecipients.add(recipient);
         }
    public void registerDirty(Recipient recipient){
        if (!deletedRecipients.contains(recipient.getID())&&
                !dirtyRecipients.contains(recipient) && !newRecipients.contains(recipient)) {
             dirtyRecipients.add(recipient);
            }
    }
    public void registerDeleted(Long id) {
        if (!deletedRecipients.contains(id)) {
             deletedRecipients.add(id);
             }
     }

    public void commit() {
         for (Recipient recipient : newRecipients) {
             try {
                 Recipient.getMapper().insert(recipient.getID(),recipient.getPassword(),
                         recipient.getName(),recipient.getBirth());
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         for (Recipient recipient : dirtyRecipients) {
             try {
                 Recipient.getMapper().update(recipient);
             } catch (SQLException e) {
                 e.printStackTrace();
             }
         }
         for (long id : deletedRecipients) {
             Recipient.getMapper().delete(id);
             }
         }
}
